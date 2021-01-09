package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.model.Arbitros;
import org.springframework.samples.petclinic.model.Entrenadores;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.ArbitroService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
		Arbitro arbitro = new Arbitro();

		model.put("arbitro", arbitro);
		return VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/arbitros/new")
	public String processCreationForm(@Valid Arbitro arbitro, BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("arbitro", arbitro);
			return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		} else {
			this.arbitroService.saveArbitro(arbitro);
			return "redirect:/arbitros/" + arbitro.getId();
		}
	}

	@GetMapping(value = "/arbitros/find")
	public String initFindForm(final Map<String, Object> model) {
		model.put("arbitro", new Arbitro());
		return "arbitros/findArbitros";
	}

	@GetMapping(value = { "/arbitros" })
	public String showArbitroList(Arbitro arbitro, final BindingResult result, final Map<String, Object> model) {
		// allow parameterless GET request for /Equipos to return all records
				if (arbitro.getNombre() == null) {
					arbitro.setNombre(""); // empty string signifies broadest possible search
				}

				// find Equipos by nombre
				Collection<Arbitro> results = this.arbitroService.findArbitroByNombre(arbitro.getNombre());
				if (results.isEmpty()) {
					// no Equipos found
					result.rejectValue("nombre", "notFound", "not found");
					return "arbitros/findArbitros";
				} else if (results.size() == 1) {
					// 1 Equipo found
					arbitro = results.iterator().next();
					return "redirect:/arbitros/" + arbitro.getId();
				} else {
					// multiple Equipos found
					model.put("selections", results);
					return "arbitros/arbitrosList";
				}
		
	}

	@GetMapping(value = "/arbitros/{arbitroId}/edit")
	public String initUpdateForm(@PathVariable("arbitroId") final int arbitroId, final ModelMap model) {
		Arbitro arbitro = this.arbitroService.findById(arbitroId);
		model.put("arbitro", arbitro);
		return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/arbitros/{arbitroId}/edit")
	public String processUpdateForm(@Valid final Arbitro arbitro, final BindingResult result,
			@PathVariable("arbitroId") final int arbitroId, final ModelMap model) {

		if (result.hasErrors()) {
			return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		} else {
			arbitro.setId(arbitroId);
			this.arbitroService.saveArbitro(arbitro);
			return "redirect:/arbitros/{arbitroId}";
		}
	}

	@GetMapping(value = "/arbitros/{arbitroId}/delete")
	public String processDeleteForm(@PathVariable("arbitroId") final int arbitroId) {

		Arbitro arbitro = this.arbitroService.findById(arbitroId);

		this.arbitroService.deleteArbitro(arbitro);
		return "redirect:/arbitros";
	}

	@GetMapping("/arbitros/{id}")
	public ModelAndView showArbitro(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(VIEWS_ARBITRO_SHOW);
		mav.addObject(arbitroService.findById(id));
		return mav;
	}

}
