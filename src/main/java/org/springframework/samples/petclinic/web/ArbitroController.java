package org.springframework.samples.petclinic.web;


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
	private static final String				VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM= "arbitros/createOrUpdateArbitroForm";
	private static final String				VIEWS_ARBITRO_SHOW					= "arbitros/arbitroDetails";

	@Autowired
	private final ArbitroService arbitroService;
	@Autowired
	private final PartidoService partidoService;

	
	@Autowired
	public ArbitroController(ArbitroService arbitroService, PartidoService partidoService) {
		this.arbitroService = arbitroService;
		this.partidoService = partidoService;
	}
	
//	@ModelAttribute("partido")
//	public Partido findPartido(@PathVariable("partidoId") final int partidoId) {
//		return this.partidoService.findById(partidoId);
//	}
	
	@InitBinder("arbitro")
	public void initJugadorBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/arbitros/new")
	public String initCreationForm(Map<String, Object> model) {
		Arbitro arbitro = new Arbitro();

		model.put("arbitro", arbitro);
		return VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}
	@GetMapping(value = { "/arbitros" })
	public String showArbitroList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Arbitros arbitros= new Arbitros();
		arbitros.getArbitroList().addAll(this.arbitroService.findAll());
		model.put("arbitros", arbitros);
		return "arbitros/arbitrosList";
	}

	@PostMapping(value = "/arbitros/new")
	public String processCreationForm(@Valid Arbitro arbitro, BindingResult result,final ModelMap model) {
		if (result.hasErrors()) {
			model.put("arbitro", arbitro);
			return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.arbitroService.saveArbitro(arbitro);
			return "redirect:/arbitros/"+ arbitro.getId();
		}
	}

	
	
	@GetMapping(value = "/arbitros/{arbitroId}/edit")
	public String initUpdateForm(@PathVariable("arbitroId") final int arbitroId, final ModelMap model) {
		Arbitro arbitro = this.arbitroService.findById(arbitroId);
		model.put("arbitro", arbitro);
		return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}
	/**
	 *
	 * @param arbitro
	 * @param result
	 * @param arbitrorId
	 * @param model
	 * @param partido
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/arbitros/{arbitroId}/edit")
	public String processUpdateForm(@Valid final Arbitro arbitro, final BindingResult result, @PathVariable("arbitroId") final int arbitroId, final ModelMap model) {

		if (result.hasErrors()) {
			model.put("arbitro", arbitro);
			return ArbitroController.VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		} else {
			Arbitro a= arbitroService.findById(arbitroId);
			this.arbitroService.saveArbitro(a);
			return "redirect:/partidos/{partidoId}";
		}
	}
	@GetMapping(value = "/arbitros/{arbitroId}/delete")
	public String processDeleteForm(@PathVariable("arbitroId") final int arbitroId) {

		Arbitro arbitro= this.arbitroService.findById(arbitroId);
		
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
