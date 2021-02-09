/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Equipos;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */

@Slf4j
@Controller
public class EquipoController {

	private static final String	VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM	= "equipos/createOrUpdateEquiposForm";

	private final EquipoService	equipoService;
	private final CompeticionService competicionService;

	@Autowired
	public EquipoController(final EquipoService equipoService,final CompeticionService competicionService, final UserService userService, final AuthoritiesService authoritiesService) {
		this.equipoService = equipoService;
		this.competicionService= competicionService;
	}

	@InitBinder
	public void setAllowedFields(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/competiciones/{competicionId}/equipos/new")
	public String initCreationForm(final Map<String, Object> model, @PathVariable("competicionId") final int competicionId) {
		log.info("Se ha iniciado la creación de un equipo");
		Equipo equipo = new Equipo();
		equipo.setCompeticion(competicionService.findCompeticionById(competicionId));
		model.put("equipo", equipo);
		return EquipoController.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/competiciones/{competicionId}/equipos/new")
	public String processCreationForm(@Valid final Equipo equipo, final BindingResult result,final ModelMap model ,@PathVariable("competicionId") final int competicionId) {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la creación de un equipo");
			model.put("equipo", equipo);
			return EquipoController.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la creación de un equipo");
			Competicion e = competicionService.findCompeticionById(competicionId);
			e.addEquipo(equipo);
			equipoService.saveEquipo(equipo);
			competicionService.saveCompeticion(e);

			return "redirect:/competiciones/{competicionId}";
		}
	}


	@GetMapping(value = "/equipos/find")
	public String initFindForm(final Map<String, Object> model) {
		log.info("Se ha iniciado la visualización de los equipos");
		model.put("equipo", new Equipo());
		return "equipos/findEquipos";
	}

	@GetMapping(value = "/equipos")
	public String processFindForm(Equipo equipo, final BindingResult result, final Map<String, Object> model) {
		log.info("Se ha iniciado la vista de mostrar los equipos buscados");
		// allow parameterless GET request for /Equipos to return all records
		if (equipo.getNombre() == null)
			equipo.setNombre(""); // empty string signifies broadest possible search

		// find Equipos by nombre
		Collection<Equipo> results = equipoService.findEquipoByNombre(equipo.getNombre());
		if (results.isEmpty()) {
			// no Equipos found
			result.rejectValue("nombre", "notFound", "not found");
			return "equipos/findEquipos";
		} else if (results.size() == 1) {
			// 1 Equipo found
			equipo = results.iterator().next();
			return "redirect:/equipos/" + equipo.getId();
		} else {
			// multiple Equipos found
			model.put("selections", results);
			return "equipos/equiposList";
		}
	}

	@GetMapping(value = "/competiciones/{competicionId}/equipos/{equipoId}/edit")
	public String initUpdateEquipoForm(@PathVariable("equipoId") final int equipoId,@PathVariable("competicionId") final int competicionId, final Model model) {
		log.info("Se ha iniciado la edición de un equipo");
		Equipo Equipo = equipoService.findEquipoById(equipoId);
		model.addAttribute(Equipo);
		return EquipoController.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/competiciones/{competicionId}/equipos/{equipoId}/edit")
	public String processUpdateEquipoForm(@Valid final Equipo equipo, final BindingResult result, @PathVariable("equipoId") final int equipoId, @PathVariable("competicionId") final int competicionId) {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la edición de un equipo");
			return EquipoController.VIEWS_EQUIPO_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la edición de un equipo");
			Competicion e = competicionService.findCompeticionById(competicionId);
			equipo.setId(equipoId);
			equipo.setCompeticion(e);
			equipoService.saveEquipo(equipo);

			return "redirect:/equipos/{equipoId}";
		}
	}

	@GetMapping("/equipos/{equipoId}")
	public ModelAndView showEquipo(@PathVariable("equipoId") final int equipoId) {
		log.info("Se ha iniciado la muestra de detalles de un equipo");
		ModelAndView mav = new ModelAndView("equipos/equipoDetails");
		mav.addObject(equipoService.findEquipoById(equipoId));
		return mav;
	}

	@GetMapping("/equipos/list")
	public String showEquipoList(final Map<String, Object> model) {
		log.info("Se ha iniciado la vista de mostrar la lista de equipos");
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Equipos equipos = new Equipos();
		equipos.getEquipoList().addAll(equipoService.findEquipos());
		model.put("equipos", equipos);
		return "equipos/equiposList";
	}

	@GetMapping(value = "/competiciones/{competicionId}/equipos/{equipoId}/delete")
	public String processDeleteEquipo(@PathVariable("equipoId") final int equipoId, @PathVariable("competicionId") final int competicionId) {
		log.info("Se ha iniciado la eliminación de un equipo");
		Equipo equipo = equipoService.findEquipoById(equipoId);
		Competicion competicion= competicionService.findCompeticionById(competicionId);
		competicion.removeEquipo(equipo);
		equipoService.deleteEquipo(equipo);
		return "redirect:/competiciones/{competicionId}";
	}

	//	@GetMapping(value = "/equipos/{equipoId}/delete")
	//	public String processDeleteEquipo(@PathVariable("partidoId") final int partidoId, @PathVariable("equipoId") final int equipoId) {
	//		//		Partido partido = this.partidoService.findById(partidoId);
	//		Equipo equipo = this.equipoService.findEquipoById(equipoId);
	//		//		equipo.removeAllPartidos(partido);
	//		this.equipoService.deleteEquipo(equipo);
	//		return "redirect:/equipos/find";
	//	}

}
