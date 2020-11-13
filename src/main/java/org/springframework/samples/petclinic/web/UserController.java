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
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		} else {
			userService.saveUser(user);
			authoritiesService.saveAuthorities(user.getUsername(), "user");
			return "redirect:/users/" + user.getUsername();
		}
	}

	@GetMapping(value = "/users/{username}/edit")
	public String initUpdateUsuarioRegistradoForm(@PathVariable("username") String username, Model model) {
		User user = userService.findUserByUsername(username);
		model.addAttribute(user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/users/{username}/edit")
	public String processUpdateUsuarioRegistradoForm(@Valid User user, BindingResult result, @PathVariable("username") String username) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		} else {
			if(user.getUsername().equals(username)) {
				userService.saveUser(user);
			}else {
				userService.saveUser(user);
				authoritiesService.saveAuthorities(user.getUsername(), "user");
				// Eliminamos el Usuario y su autoridad con el username abandonado
				Authorities auto = authoritiesService.findAllAuthorities().stream().
					filter(x->x.getUser().getUsername().equals(username)).findFirst().get();
				authoritiesService.deleteAuthorities(auto);
				userService.delete(userService.findUserByUsername(username));

			}

			return "redirect:/users/" + user.getUsername();
		}
	}

	@GetMapping("/users/{username}")
	public ModelAndView showUsuarioRegistrado(@PathVariable("username") String username) {
		ModelAndView mav = new ModelAndView(VIEWS_USER_SHOW);
		mav.addObject(userService.findUserByUsername(username));
		return mav;
	}

}
