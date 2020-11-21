/*
 * Copyright Futvilla Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidoController {
	
	private static final String VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM = "partidos/createOrUpdatePartidoForm";
	private static final String VIEWS_PARTIDO_SHOW = "partidos/partidoDetails";

	private final PartidoService partidoService;

	@Autowired
	public PartidoController(PartidoService partidoService) {
		this.partidoService = partidoService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/partidos/new")
	public String initCreationForm(Map<String, Object> model) {
		Partido partido = new Partido();
		model.put("partido", partido);
		return VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
	}

/*	@PostMapping(value = "/partidos/new")
	public String processCreationForm(@Valid Partido partido, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_PARTIDO_CREATE_OR_UPDATE_FORM;
		} else {
			LocalDate fechaActual = LocalDate.now();
			partido.setDate(fechaActual);
			partidoService.savePartido(partido);

			return "redirect:/partidos/" + partido.getId();
		}
	}   */

	@GetMapping("/partidos/{id}")
	public ModelAndView showPartido(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(VIEWS_PARTIDO_SHOW);
		mav.addObject(partidoService.findById(id));
		return mav;
	}
}