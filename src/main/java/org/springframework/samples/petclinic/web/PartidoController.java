/*
 * Copyright Futvilla Team
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.ArbitroService;
import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;

import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.web.validators.PartidoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class PartidoController {

	
	private static final String VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM = "partidos/createOrUpdatePartidoForm";
	private static final String VIEWS_PARTIDO_SHOW = "partidos/partidoDetails";
	private static final String VIEWS_PARTIDO_ADMIN_JUGADORES_FORM = "partidos/adminJugadoresForm";

	private final PartidoService partidoService;
	private final EquipoService equipoService;
	private final ArbitroService arbitroService;
	private final CompeticionService competicionService;

	@Autowired
	public PartidoController(PartidoService partidoService, EquipoService equipoService,ArbitroService arbitroService,CompeticionService competicionService) {
		this.partidoService = partidoService;
		this.equipoService = equipoService;
		this.arbitroService=arbitroService;
		this.competicionService=competicionService;

	}

	@InitBinder
	public void setAllowedFields(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	@InitBinder("partido")
	public void initPartidoBinder(final WebDataBinder dataBinder) {
		dataBinder.setValidator(new PartidoValidator(partidoService));;
	}

	@ModelAttribute("equipos")
	public Collection<Equipo> populateEquipos() {

		return equipoService.findEquipos();
	}
	@ModelAttribute("arbitros")
	public Collection<Arbitro> populateArbitros() {
		return arbitroService.findAll();

	}
	@ModelAttribute("competiciones")
	public Collection<Competicion> populateCompeticiones() {
		return competicionService.findAll();

	}


	@GetMapping(value = "/partidos/new")
	public String initCreationForm(final Map<String, Object> model) {
		Partido partido = new Partido();
		model.put("partido", partido);
		return PartidoController.VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/partidos/new")
	public String processCreationForm(@Valid Partido partido, BindingResult result) {
		if (result.hasErrors())
			return VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
		else {
			partidoService.savePartido(partido);
			return "redirect:/partidos/" + partido.getId();
		}
	}


	@GetMapping("/partidos/{id}")
	public ModelAndView showPartido(@PathVariable("id") final int id) {
		ModelAndView mav = new ModelAndView(PartidoController.VIEWS_PARTIDO_SHOW);
		mav.addObject(this.partidoService.findById(id));
		return mav;
	}


	@GetMapping(value = "/partidos/{id}/administrarJugadores")
	public String initAdministrarJugadores(@PathVariable("id") final int id, final Map<String, Object> model) {
		Partido partido = this.partidoService.findById(id);
		List<Jugador> jugadores = new ArrayList<>();
		jugadores.addAll(partido.getEquipo1().getJugadores());
		jugadores.addAll(partido.getEquipo2().getJugadores());
		model.put("jugadores", jugadores);
		model.put("partido", partido);
		return PartidoController.VIEWS_PARTIDO_ADMIN_JUGADORES_FORM;
	}
	
	
	//creacion de un partido en una competicion
	
	@GetMapping(value = "/competiciones/{competicionId}/partidos/new")
	public String initCreationForm(final Map<String, Object> model, @PathVariable("competicionId") final int competicionId) {
		Partido partido= new Partido();
		partido.setCompeticion(this.competicionService.findCompeticionById(competicionId));
		model.put("partido", partido);
		return PartidoController.VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
	}
	@PostMapping(value = "/competiciones/{competicionId}/partidos/new")
	public String processCreationForm(@Valid final Partido partido, final BindingResult result,final ModelMap model ,@PathVariable("competicionId") final int competicionId) {
		if (result.hasErrors()) {
			model.put("partido", partido);
			return PartidoController.VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
		} else {
			Competicion e = this.competicionService.findCompeticionById(competicionId);
				e.addPartido(partido);
				this.partidoService.savePartido(partido);
				this.competicionService.saveCompeticion(e);
			
			return "redirect:/competiciones/{competicionId}";
		}
	}
	
	//editar un partido de una competicion
	
	@GetMapping(value = "/competiciones/{competicionId}/partidos/{partidoId}/edit")
	public String initUpdateEquipoForm(@PathVariable("partidoId") final int partidoId,@PathVariable("competicionId") final int competicionId, final Model model) {
		Partido partido= this.partidoService.findById(partidoId);
		model.addAttribute(partido);
		return PartidoController.VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/competiciones/{competicionId}/partidos/{partidoId}/edit")
	public String processUpdateEquipoForm(@Valid final Partido partido, final BindingResult result, @PathVariable("partidoId") final int partidoId, @PathVariable("competicionId") final int competicionId) {
		if (result.hasErrors()) {
			return PartidoController.VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
		} else {
			Competicion e = this.competicionService.findCompeticionById(competicionId);
			partido.setId(partidoId);
			partido.setCompeticion(e);
			this.partidoService.savePartido(partido);
			
			return "redirect:/partidos/{partidoId}";
		}
	}


	@PostMapping(value = "/partidos/{id}/administrarJugadores")
	public String processAdministrarJugadores(@PathVariable("id") final int id, @Valid final Partido partido, final BindingResult result) {
		if (result.hasErrors()) {
			return PartidoController.VIEWS_PARTIDO_ADMIN_JUGADORES_FORM;
		} else {
			partido.setId(id);
			this.partidoService.savePartido(partido);
			return "redirect:/partidos/" + partido.getId();
		}
	}
}
