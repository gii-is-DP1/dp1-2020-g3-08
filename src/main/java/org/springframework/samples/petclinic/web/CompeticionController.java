
package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.model.Competiciones;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CompeticionController {

	private static final String			VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM	= "competiciones/createOrUpdateCompeticionForm";
	private static final String			VIEWS_COMPETICION_SHOW					= "competiciones/competicionDetails";
	@Autowired
	private final CompeticionService	competicionService;


	@Autowired
	public CompeticionController(final CompeticionService competicionService) {
		this.competicionService = competicionService;
	}

	@InitBinder
	public void setAllowedFields(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/competiciones/list")
	public String showCompeticionList(final Map<String, Object> model) {
		log.info("Se ha iniciado la visualización de competiciones");
		Competiciones competiciones = new Competiciones();
		competiciones.getCompeticionList().addAll(competicionService.findAll());
		model.put("competiciones", competiciones);
		return "competiciones/competicionesList";
	}

	@GetMapping(value = "/competiciones/new")
	public String initCreationForm(final Map<String, Object> model) {
		log.info("Se ha iniciado la creación de una competición");
		Competicion competicion = new Competicion();
		model.put("competicion", competicion);
		return CompeticionController.VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
	}
	@PostMapping(value = "/competiciones/new")
	public String processCreationForm(@Valid final Competicion competicion, final BindingResult result) {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la creación de una competición");
			return CompeticionController.VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la creación de una competición");
			competicionService.saveCompeticion(competicion);

			return "redirect:/competiciones/" + competicion.getId();

		}
	}

	@GetMapping(value = "/competiciones/{competicionId}/edit")
	public String initUpdateCompeticionForm(@PathVariable("competicionId") final int competicionId, final ModelMap model) {
		log.info("Se ha iniciado la edición de una competición");
		Competicion competicion = competicionService.findCompeticionById(competicionId);

		model.put("competicion", competicion);
		return CompeticionController.VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/competiciones/{competicionId}/edit")
	public String processUpdateCompeticionForm(@Valid final Competicion competicion, final BindingResult result, @PathVariable("competicionId") final int competicionId, final ModelMap model) {

		if (result.hasErrors()) {
			log.warn("Se ha abortado la edición de una competición");
			model.put("competicion", competicion);
			return CompeticionController.VIEWS_COMPETICION_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la edición de una competición");
			competicion.setId(competicionId);
			competicionService.saveCompeticion(competicion);
			return "redirect:/competiciones/{competicionId}";
		}
	}

	@GetMapping(value = "/competiciones/{competicionId}/delete")
	public String processDeleteForm(@PathVariable("competicionId") final int competicionId) {
		log.info("Se ha iniciado la eliminación de una competición");
		Competicion competicion = competicionService.findCompeticionById(competicionId);
		competicionService.deleteCompeticion(competicion);
		return "redirect:/competiciones/list";
	}

	@GetMapping("/competiciones/{id}")
	public ModelAndView showCompeticion(@PathVariable("id") final int id) {
		log.info("Se ha iniciado la muestra de detalles de una competición");
		ModelAndView mav = new ModelAndView(CompeticionController.VIEWS_COMPETICION_SHOW);
		mav.addObject(competicionService.findCompeticionById(id));

		return mav;
	}

}
