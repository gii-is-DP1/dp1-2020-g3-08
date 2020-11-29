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

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Jugadores;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedJugadorDNIException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/equipos/{equipoId}")
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

	@ModelAttribute("equipo")
	public Equipo findEquipo(@PathVariable("equipoId") final int equipoId) {
		return this.equipoService.findEquipoById(equipoId);
	}

	@InitBinder("jugador")
	public void initJugadorBinder(final WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/jugadores/new")
	public String initCreationForm(final ModelMap model, @PathVariable("equipoId") final int equipoId) {
		Jugador jugador = new Jugador();
		jugador.setEquipo(this.findEquipo(equipoId));
		model.put("jugador", jugador);
		return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/jugadores/new")
	public String processCreationForm(@Valid final Jugador jugador, final BindingResult result, final ModelMap model, @PathVariable("equipoId") final int equipoId) {

		if (result.hasErrors()) {
			model.put("jugador", jugador);
			return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		} else {
			Equipo e = this.findEquipo(equipoId);
			try {
				e.addJugador(jugador);
				this.jugadorService.saveJugador(jugador);
				this.equipoService.saveEquipo(e);
			} catch (DuplicatedJugadorDNIException ex) {
				result.rejectValue("name", "duplicate", "already exists");
				return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
			}
			return "redirect:/equipos/{equipoId}";
		}
	}

	@GetMapping(value = "/jugadores/{jugadorId}/edit")
	public String initUpdateForm(@PathVariable("jugadorId") final int jugadorId, final ModelMap model) {
		Jugador jugador = this.jugadorService.findJugadorById(jugadorId);
		model.put("jugador", jugador);
		return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
	}

	/**
	 *
	 * @param jugador
	 * @param result
	 * @param jugadorId
	 * @param model
	 * @param equipo
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/jugadores/{jugadorId}/edit")
	public String processUpdateForm(@Valid final Jugador jugador, final BindingResult result, @PathVariable("jugadorId") final int jugadorId, @PathVariable("equipoId") final int equipoId, final ModelMap model) {

		if (result.hasErrors()) {
			model.put("jugador", jugador);
			return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
		} else {
			try {
				Equipo e = this.equipoService.findEquipoById(equipoId);
				jugador.setId(jugadorId);
				jugador.setEquipo(e);
				this.jugadorService.saveJugador(jugador);
			} catch (DuplicatedJugadorDNIException ex) {
				result.rejectValue("name", "duplicate", "already exists");
				return JugadorController.VIEWS_JUGADOR_CREATE_OR_UPDATE_FORM;
			}
			return "redirect:/equipos/{equipoId}";
		}
	}

	@GetMapping("/jugadores/list")
	public String showJugadorList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Jugadores jugadores = new Jugadores();
		jugadores.getJugadorList().addAll(this.jugadorService.findJugadores());
		model.put("jugadores", jugadores);
		return "jugadores/jugadoresList";
	}

	@GetMapping(value = "/jugadores/{jugadorId}/delete")
	public String processDeleteForm(@PathVariable("jugadorId") final int jugadorId, @PathVariable("equipoId") final int equipoId) {

		Jugador jugador = this.jugadorService.findJugadorById(jugadorId);
		Equipo equipo = this.equipoService.findEquipoById(equipoId);
		equipo.removeJugador(jugador);
		this.jugadorService.deleteJugador(jugador);
		return "redirect:/equipos/{equipoId}";
	}

}
