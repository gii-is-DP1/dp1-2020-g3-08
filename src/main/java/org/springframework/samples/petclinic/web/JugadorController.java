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
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Jugadores;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JugadorController {

	private static final String		VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM	= "jugadores/createOrUpdateJugadorForm";

	private final JugadorService	jugadorService;
	private final EquipoService		equipoService;


	@Autowired
	public JugadorController(final JugadorService jugadorService, final EquipoService equipoService) {
		this.jugadorService = jugadorService;
		this.equipoService = equipoService;
	}

	//	@ModelAttribute("posicion")
	//	public Collection<Jugador> PosicionJugadores() {
	//		return this.jugadorService.findPosicionJugadores();
	//	}

	//	@ModelAttribute("equipo")
	//	public Equipo findEquipo(@PathVariable("equipoId") final int equipoId) {
	//		return this.equipoService.findEquipoById(equipoId);
	//	}

	@InitBinder("jugador")
	public void initJugadorBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/equipos/{equipoId}/jugadores/new")
	public String initCreationForm(final ModelMap model, @PathVariable("equipoId") final int equipoId) {
		log.info("Se ha iniciado la creación de un jugador");
		Jugador jugador = new Jugador();
		jugador.setEquipo(equipoService.findEquipoById(equipoId));
		model.put("jugador", jugador);
		return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/equipos/{equipoId}/jugadores/new")
	public String processCreationForm(@Valid final Jugador jugador, final BindingResult result, final ModelMap model, @PathVariable("equipoId") final int equipoId) {

		if (result.hasErrors()) {
			log.warn("Se ha abortado la creación de un jugador");
			model.put("jugador", jugador);
			return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la creación de un jugador");
			jugador.setTarjetaAmarilla(0);
			jugador.setTarjetaRoja(0);
			Equipo e = this.equipoService.findEquipoById(equipoId);
				
			e.addJugador(jugador);
			this.jugadorService.saveJugador(jugador);
			this.equipoService.saveEquipo(e);
			
			return "redirect:/equipos/{equipoId}";
		}
	}

	@GetMapping(value = "/jugadores/find")
	public String initFindForm(final Map<String, Object> model) {
		log.info("Se ha iniciado la visualización de los jugadores");
		model.put("jugador", new Jugador());
		return "jugadores/findJugadores";
	}

	@GetMapping(value = "/jugadores")
	public String processFindForm(final Jugador jugador, final BindingResult result, final Map<String, Object> model) {
		log.info("Se ha iniciado la vista de mostrar los jugadores buscados");
		// allow parameterless GET request for /Jugadores to return all records
		if (jugador.getNombre() == null)
			jugador.setNombre(""); // empty string signifies broadest possible search

		// find Jugadores by nombre
		Collection<Jugador> results = jugadorService.findJugadorByNombre(jugador.getNombre());
		if (results.isEmpty()) {
			// no Jugadores found
			result.rejectValue("nombre", "notFound", "not found");
			return "jugadores/findJugadores";
		} else {
			// multiple Jugadores found
			model.put("selections", results);
			return "jugadores/jugadoresList";
		}
	}

	@GetMapping(value = "/equipos/{equipoId}/jugadores/{jugadorId}/edit")
	public String initUpdateForm(@PathVariable("jugadorId") final int jugadorId, final ModelMap model) {
		log.info("Se ha iniciado la edición de un jugador");
		Jugador jugador = jugadorService.findJugadorById(jugadorId);
		model.put("jugador", jugador);
		return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}


	@PostMapping(value = "/equipos/{equipoId}/jugadores/{jugadorId}/edit")
	public String processUpdateForm(@Valid final Jugador jugador, final BindingResult result, @PathVariable("jugadorId") final int jugadorId, @PathVariable("equipoId") final int equipoId, final ModelMap model) {

		if (result.hasErrors()) {
			log.warn("Se ha abortado la edición de un jugador");
			model.put("jugador", jugador);
			return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		} else {
		
				Equipo e = this.equipoService.findEquipoById(equipoId);
				jugador.setId(jugadorId);
				jugador.setEquipo(e);
				this.jugadorService.saveJugador(jugador);
			
			return "redirect:/equipos/{equipoId}";
		}
	}

	@GetMapping("/jugadores/list")
	public String showJugadorList(final Map<String, Object> model) {
		log.info("Se ha iniciado la vista de mostrar la lista de jugadores");
		Jugadores jugadores = new Jugadores();
		jugadores.getJugadorList().addAll(jugadorService.findJugadores());
		model.put("jugadores", jugadores);
		return "jugadores/jugadoresList";
	}

	@GetMapping(value = "/equipos/{equipoId}/jugadores/{jugadorId}/delete")
	public String processDeleteForm(@PathVariable("jugadorId") final int jugadorId, @PathVariable("equipoId") final int equipoId) {
		log.info("Se ha iniciado la eliminación de un jugador");
		Jugador jugador = jugadorService.findJugadorById(jugadorId);
		Equipo equipo = equipoService.findEquipoById(equipoId);
		equipo.removeJugador(jugador);
		jugadorService.deleteJugador(jugador);
		return "redirect:/equipos/{equipoId}";
	}

}
