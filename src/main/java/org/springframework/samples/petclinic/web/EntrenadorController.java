package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Genre;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedException;
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class EntrenadorController {

	private static final String VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM = "entrenadores/createOrUpdateEntrenadorForm";
	private static final String VIEWS_ENTRENADOR_SHOW = "entrenadores/entrenadorDetails";
	@Autowired
	private final EntrenadorService entrenadorService;
	@Autowired
	private final UserService userService;
	@Autowired
	private final AuthoritiesService authoritiesService;

	private final EquipoService equipoService;

	@Autowired
	public EntrenadorController(EntrenadorService entrenadorService, UserService userService,
		AuthoritiesService authoritiesService, EquipoService equipoService) {
		this.entrenadorService = entrenadorService;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
		this.equipoService = equipoService;
	}

	@ModelAttribute("genres")
	public Collection<Genre> populateGenres() {
		return userService.findGenres();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("username");
	}

	@GetMapping(value = "/equipos/{equipoId}/entrenadores/new")
	public String initCreationForm(Map<String, Object> model, @PathVariable("equipoId") final int equipoId) {
		log.info("Se ha iniciado la creación de un entrenador");
		model.put("equipo", equipoService.findEquipoById(equipoId));
		Entrenador entrenador = new Entrenador();
		model.put("entrenador", entrenador);
		return VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
	}

	@GetMapping(value = { "/entrenadores/list" })
	public String showEntrenadorList(final Map<String, Object> model) {
		log.info("Se ha iniciado la visualización de los entrenadores");
		model.put("entrenadores", entrenadorService.findAll());
		return "entrenadores/entrenadoresList";
	}

	@PostMapping(value = "/equipos/{equipoId}/entrenadores/new")

	public String processCreationForm(@Valid Entrenador entrenador, BindingResult result,@PathVariable("equipoId") final int equipoId, final ModelMap model) throws DataAccessException, DuplicatedException {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la creación de un entrenador");
			return EntrenadorController.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la creación de un entrenador");
			Equipo e = equipoService.findEquipoById(equipoId);
			entrenador.getUser().setEnabled(true);
			e.setEntrenador(entrenador);
			entrenadorService.saveEntrenador(entrenador);
			userService.saveUser(entrenador.getUser());
			equipoService.saveEquipo(e);
			authoritiesService.saveAuthorities(entrenador.getUser().getUsername(), "entrenador");

			return "redirect:/equipos/{equipoId}";
			// return "redirect:/entrenadores/" + entrenador.getId();
		}
	}

	@GetMapping(value = "/equipos/{equipoId}/entrenadores/{entrenadorId}/edit")
	public String initUpdateForm(@PathVariable("entrenadorId") final int entrenadorId, final ModelMap model,
		@PathVariable("equipoId") int equipoId) {
		log.info("Se ha iniciado la edición de un entrenador");
		model.put("equipo", equipoService.findEquipoById(equipoId));
		Entrenador entrenador = entrenadorService.findById(entrenadorId);
		model.put("entrenador", entrenador);
		return EntrenadorController.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
	}


	@PostMapping(value = "/equipos/{equipoId}/entrenadores/{entrenadorId}/edit")

	public String processUpdateForm(@Valid final Entrenador entrenador, final BindingResult result,
		@PathVariable("entrenadorId") final int entrenadorId, @PathVariable("equipoId") final int equipoId,
		final ModelMap model) throws DataAccessException, DuplicatedException {

		if (result.hasErrors()) {
			log.warn("Se ha abortado la edición de un entrenador");
			model.put("entrenador", entrenador);
			return EntrenadorController.VIEWS_ENTRENADOR_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la edición de un entrenador");
			Equipo e = equipoService.findEquipoById(equipoId);
			entrenador.setId(entrenadorId);
			e.setEntrenador(entrenador);
			entrenadorService.saveEntrenador(entrenador);
			return "redirect:/equipos/{equipoId}";
		}
	}

	//	@GetMapping(value = "/equipos/{equipoId}/entrenadores/{entrenadorId}/delete")
	//	public String processDeleteForm(@PathVariable("entrenadorId") final int entrenadorId,
	//			@PathVariable("equipoId") final int equipoId) {
	//
	//		Entrenador entrenador = this.entrenadorService.findById(entrenadorId);
	//
	//		this.entrenadorService.deleteEntrenador(entrenador);
	//		return "redirect:/equipos/{equipoId}";
	//	}

	@GetMapping("/entrenadores/{id}")
	public ModelAndView showEntrenador(@PathVariable("id") int id) {
		log.info("Se ha iniciado la muestra de detalles de un entrenador");
		ModelAndView mav = new ModelAndView(VIEWS_ENTRENADOR_SHOW);
		mav.addObject(entrenadorService.findById(id));
		return mav;
	}

}
