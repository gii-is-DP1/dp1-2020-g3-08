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

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.Genre;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class UserController {

	private static final String				VIEWS_USER_CREATE_OR_UPDATE_FORM	= "users/createOrUpdateUserForm";
	private static final String				VIEWS_USER_SHOW					= "users/userDetails";

	private final UserService	userService;
	private final AuthoritiesService authoritiesService;


	@Autowired
	public UserController(UserService userService, AuthoritiesService authoritiesService) {
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		//dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("genres")
	public Collection<Genre> populateGenres() {
		return userService.findGenres();
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		log.info("Se ha iniciado la creación de un user");
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la creación de un user");
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la creación de un user");
			userService.saveUser(user);
			authoritiesService.saveAuthorities(user.getUsername(), "user");
			return "redirect:/users/" + user.getUsername();
		}
	}

	@GetMapping(value = "/users/{username}/edit")
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
		log.info("Se ha iniciado la edición de un user");
		User user = userService.findUserByUsername(username);
		model.addAttribute(user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/{username}/edit")
	public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("username") String username) {
		if (result.hasErrors()) {
			log.warn("Se ha abortado la edición de un user");
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		} else {
			log.info("Se ha finalizado la edición de un user");
			if(user.getUsername().equals(username))
				userService.saveUser(user);
			else {
				userService.saveUser(user);
				authoritiesService.saveAuthorities(user.getUsername(), "user");
				// Eliminamos el Usuario y su autoridad con el username abandonado
				Authorities auto = authoritiesService.findAllAuthorities().stream().
					filter(x->x.getUser().getUsername().equals(username)).findFirst().get();
				authoritiesService.deleteAuthorities(auto);
				userService.delete(userService.findUserByUsername(username));
				//Actualizamos el Authentication
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				org.springframework.security.core.userdetails.User updatedUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auth.getAuthorities());
				Authentication newAuth = new UsernamePasswordAuthenticationToken(updatedUser, auth.getCredentials(), auth.getAuthorities());

				SecurityContextHolder.getContext().setAuthentication(newAuth);
			}

			return "redirect:/users/" + user.getUsername();
		}
	}

	@GetMapping("/users/{username}")
	public ModelAndView showUser(@PathVariable("username") String username) {
		log.info("Se ha iniciado la muestra de detalles de un user");
		ModelAndView mav = new ModelAndView(VIEWS_USER_SHOW);
		mav.addObject(userService.findUserByUsername(username));
		return mav;
	}

}
