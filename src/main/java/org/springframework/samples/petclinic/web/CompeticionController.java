package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.model.Competiciones;
import org.springframework.samples.petclinic.model.Entrenador;

import org.springframework.samples.petclinic.model.Equipo;

import org.springframework.samples.petclinic.model.Equipos;

import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CompeticionController {

	private static final String VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM = "competiciones/createOrUpdateCompeticionForm";
	private static final String VIEWS_COMPETICION_SHOW = "competiciones/competicionDetails";
	@Autowired
	private final CompeticionService competicionService;

	@Autowired
	public CompeticionController(CompeticionService competicionService) {
		this.competicionService = competicionService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}




	
	@GetMapping(value = { "/competiciones/list" })

	public String showCompeticionList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Competiciones competiciones = new Competiciones();
		competiciones.getCompeticionList().addAll(this.competicionService.findAll());
		model.put("competiciones", competiciones);
		return "competiciones/competicionesList";
	}


	@PostMapping(value = "/competiciones/new")
	public String processCreationForm(@Valid Competicion competicion, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
		} else {
			competicionService.saveCompeticion(competicion);


			return "redirect:/competiciones/" + competicion.getId();

		}
	}
	
	
	@GetMapping(value = "/competiciones/{competicionId}/edit")
	public String initUpdateForm(@PathVariable("competicionId") final int competicionId, final ModelMap model) {

		Competicion competicion = this.competicionService.findCompeticionById(competicionId);

		model.put("competicion", competicion);
		return CompeticionController.VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/competiciones/{competicionId}/edit")
	public String processUpdateForm(@Valid final Competicion competicion, final BindingResult result, @PathVariable("competicionId") final int competicionId, final ModelMap model) {

		if (result.hasErrors()) {
			model.put("competicion", competicion);
			return CompeticionController.VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
		} else {
			competicion.setId(competicionId);
			this.competicionService.saveCompeticion(competicion);
			return "redirect:/competiciones/{competicionId}";
		}
	}
	
	@GetMapping(value = "/competiciones/{competicionId}/delete")
	public String processDeleteForm(@PathVariable("competicionId") final int competicionId) {
		Competicion competicion= this.competicionService.findCompeticionById(competicionId);
		this.competicionService.deleteCompeticion(competicion);
		return "redirect:/competiciones/list";
	}

	@GetMapping("/competiciones/{id}")
	public ModelAndView showCompeticion(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(VIEWS_COMPETICION_SHOW);
		mav.addObject(competicionService.findCompeticionById(id));

		return mav;
	}

}
