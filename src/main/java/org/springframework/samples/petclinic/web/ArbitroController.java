package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.service.ArbitroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArbitroController {
	private static final String VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM = "arbitros/createOrUpdateArbitroForm";
	private static final String VIEWS_ARBITRO_SHOW = "arbitros/arbitroDetails";

	@Autowired
	private final ArbitroService arbitroService;

	@Autowired
	public ArbitroController(ArbitroService arbitroService) {
		this.arbitroService = arbitroService;

	}

	@InitBinder
	public void initJugadorBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/arbitros/new")
	public String initCreationForm(Map<String, Object> model) {
		log.info("Se ha iniciado la creación de un arbitro");
		Arbitro arbitro = new Arbitro();
		model.put("arbitro", arbitro);
		return VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/arbitros/new")
	public String processCreationForm(@Valid Arbitro arbitro, BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la creación de un arbitro");
			model.put("arbitro", arbitro);
			return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la creación de un arbitro");
			arbitroService.saveArbitro(arbitro);
			return "redirect:/arbitros/" + arbitro.getId();
		}
	}

	@GetMapping(value = "/arbitros/find")
	public String initFindForm(final Map<String, Object> model) {
		log.info("Se ha iniciado la visualización de los arbitros");
		model.put("arbitro", new Arbitro());
		return "arbitros/findArbitros";
	}

	@GetMapping(value = { "/arbitros" })
	public String showArbitroList(Arbitro arbitro, final BindingResult result, final Map<String, Object> model) {
		log.info("Se ha iniciado la vista de mostrar los arbitros buscados");
		// allow parameterless GET request for /Arbitro to return all records
		if (arbitro.getNombre() == null)
			arbitro.setNombre(""); // empty string signifies broadest possible search

		// find Arbitro by nombre
		Collection<Arbitro> results = arbitroService.findArbitroByNombre(arbitro.getNombre());
		if (results.isEmpty()) {
			// no Arbitro found
			result.rejectValue("nombre", "notFound", "not found");
			return "arbitros/findArbitros";
		} else if (results.size() == 1) {
			// 1 Arbitro found
			arbitro = results.iterator().next();
			return "redirect:/arbitros/" + arbitro.getId();
		} else {
			// multiple Arbitros found
			model.put("selections", results);
			return "arbitros/arbitrosList";
		}

	}

	@GetMapping(value = "/arbitros/{arbitroId}/edit")
	public String initUpdateForm(@PathVariable("arbitroId") final int arbitroId, final ModelMap model) {
		log.info("Se ha iniciado la edición de un arbitro");
		Arbitro arbitro = arbitroService.findById(arbitroId);
		model.put("arbitro", arbitro);
		return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/arbitros/{arbitroId}/edit")
	public String processUpdateForm(@Valid final Arbitro arbitro, final BindingResult result,
		@PathVariable("arbitroId") final int arbitroId, final ModelMap model) {

		if (result.hasErrors()) {
			log.warn("Se ha abortado la edición de un arbitro");
			return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la edición de un arbitro");
			arbitro.setId(arbitroId);
			arbitroService.saveArbitro(arbitro);
			return "redirect:/arbitros/{arbitroId}";
		}
	}

	@GetMapping(value = "/arbitros/{arbitroId}/delete")
	public String processDeleteForm(@PathVariable("arbitroId") final int arbitroId) {
		log.info("Se ha iniciado la eliminación de un arbitro");
		Arbitro arbitro = arbitroService.findById(arbitroId);

		arbitroService.deleteArbitro(arbitro);
		return "redirect:/arbitros";
	}

	@GetMapping("/arbitros/{id}")
	public ModelAndView showArbitro(@PathVariable("id") int id) {
		log.info("Se ha iniciado la muestra de detalles de un arbitro");
		ModelAndView mav = new ModelAndView(VIEWS_ARBITRO_SHOW);
		mav.addObject(arbitroService.findById(id));
		return mav;
	}

}
