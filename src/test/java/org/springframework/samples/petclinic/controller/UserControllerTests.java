package org.springframework.samples.petclinic.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.UserController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = UserController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserControllerTests {

	private static final String TEST_USER_ID = "admin1";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@BeforeEach
	void setup() {
		given(userService.findUserByUsername(TEST_USER_ID)).willReturn(new User());
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitNewUserForm() throws Exception {
		mockMvc.perform(get("/users/new")).andExpect(status().isOk())
		.andExpect(view().name("users/createOrUpdateUserForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewUserFormSuccess() throws Exception {
		mockMvc.perform(post("/users/new").with(csrf())
			.param("firstName", "Juan").param("lastName", "Garcia").param("email", "juan@gmail.com")
			.param("birthDate", "2020/07/22").param("genre", "hombre").param("telephone", "666666666")
			.param("username", "asd").param("password", "asd")).andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/users/asd"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewUserFormHasErrors() throws Exception {
		mockMvc.perform(post("/users/new").with(csrf())
			.param("firstName", "hola").param("lastName", "Romano").param("email", "manuel@gmail.com")
			.param("birthDate", "2020/01/01").param("genre", "genre").param("telephone", "666666666")
			.param("username", "")).andExpect(model().attributeHasErrors("user"))
		.andExpect(status().isOk()).andExpect(view().name("users/createOrUpdateUserForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitUserUpdateForm() throws Exception {
		mockMvc.perform(get("/users/{id}/edit", TEST_USER_ID).with(csrf()))
		.andExpect(status().isOk()).andExpect(view().name("users/createOrUpdateUserForm"));

	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateUserFormSuccess() throws Exception {
		mockMvc.perform(post("/users/{id}/edit", TEST_USER_ID)
			.with(csrf()).param("firstName", "Manuel").param("lastName", "Sanchez")
			.param("email", "manuel@gmail.com").param("birthDate", "2020/01/01").param("genre", "hombre")
			.param("telephone", "666666666").param("password", "romano")).andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/users/admin1"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateUserFormHasErrors() throws Exception {
		mockMvc.perform(
			post("/users/{id}/edit", TEST_USER_ID).with(csrf())
			.param("firstName", "").param("lastName", "Romano")
			.param("email", "manuel@gmail.com").param("birthDate", "2020/01/01").param("genre", "hombre")
			.param("telephone", "666666666").param("password", "romano"))
		.andExpect(model().attributeHasErrors("user")).andExpect(status().isOk())
		.andExpect(view().name("users/createOrUpdateUserForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowUser() throws Exception {
		mockMvc.perform(get("/users/{id}", TEST_USER_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("user"))
		.andExpect(view().name("users/userDetails"));
	}

}
