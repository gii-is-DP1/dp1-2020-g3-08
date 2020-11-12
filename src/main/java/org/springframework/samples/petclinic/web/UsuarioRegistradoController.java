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

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.UsuarioRegistrado;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.VetService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.service.UsuarioRegistradoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UsuarioRegistradoController {

	private static final String VIEWS_USUARIOREGISTRADO_CREATE_OR_UPDATE_FORM = "usuariosRegistrados/createOrUpdateUsuarioRegistradoForm";

	private final UsuarioRegistradoService usuarioRegistradoService;

	@Autowired
	public UsuarioRegistradoController(UsuarioRegistradoService usuarioRegistradoService, UserService userService, AuthoritiesService authoritiesService) {
		this.usuarioRegistradoService = usuarioRegistradoService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/usuariosRegistrados/new")
	public String initCreationForm(Map<String, Object> model) {
		UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado();
		model.put("usuarioRegistrado", usuarioRegistrado);
		return VIEWS_USUARIOREGISTRADO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/usuariosRegistrados/new")
	public String processCreationForm(@Valid UsuarioRegistrado usuarioRegistrado, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USUARIOREGISTRADO_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.usuarioRegistradoService.saveUsuarioRegistrado(usuarioRegistrado);;
			
			return "redirect:/usuariosRegistrados/" + usuarioRegistrado.getId();
		}
	}

	@GetMapping(value = "/usuariosRegistrados/{usuarioRegistradoID}/edit")
	public String initUpdateUsuarioRegistradoForm(@PathVariable("usuarioRegistradoID") int usuarioRegistradoID, Model model) {
		UsuarioRegistrado usuarioRegistrado = this.usuarioRegistradoService.findUsuarioRegistradoById(usuarioRegistradoID);
		model.addAttribute(usuarioRegistrado);
		return VIEWS_USUARIOREGISTRADO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/usuariosRegistrados/{usuarioRegistradoID}/edit")
	public String processUpdateUsuarioRegistradoForm(@Valid UsuarioRegistrado usuarioRegistrado, BindingResult result,
			@PathVariable("usuarioRegistradoID") int usuarioRegistradoID) {
		if (result.hasErrors()) {
			return VIEWS_USUARIOREGISTRADO_CREATE_OR_UPDATE_FORM;
		}
		else {
			usuarioRegistrado.setId(usuarioRegistradoID);
			this.usuarioRegistradoService.saveUsuarioRegistrado(usuarioRegistrado);
			return "redirect:/usuariosRegistrados/{usuarioRegistradoID}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/usuariosRegistrados/{usuarioRegistradoID}")
	public ModelAndView showUsuarioRegistrado(@PathVariable("usuarioRegistradoID") int usuarioRegistradoID) {
		ModelAndView mav = new ModelAndView("usuariosRegistrados/usuarioRegistradoDetails");
		mav.addObject(this.usuarioRegistradoService.findUsuarioRegistradoById(usuarioRegistradoID));
		return mav;
	}

}
