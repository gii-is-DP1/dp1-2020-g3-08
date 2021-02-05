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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Equipo;

import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.EntrenadorService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.EntrenadorController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = EntrenadorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class EntrenadorControllerTests {

	private static final int TEST_ENTRENADOR_ID = 1;
	private static final int TEST_EQUIPO_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EntrenadorController entrenadorController;

	@MockBean
	private EntrenadorService entrenadorService;

	@MockBean
	private EquipoService equipoService;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@BeforeEach
	void setup() {
		given(entrenadorService.findById(TEST_ENTRENADOR_ID)).willReturn(new Entrenador());
		given(equipoService.findEquipoById(TEST_EQUIPO_ID)).willReturn(new Equipo());
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitNewEntrenadorForm() throws Exception {
		mockMvc.perform(get("/equipos/{equipoId}/entrenadores/new", TEST_EQUIPO_ID)).andExpect(status().isOk())
				.andExpect(view().name("entrenadores/createOrUpdateEntrenadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewEntrenadorFormSuccess() throws Exception {
		mockMvc.perform(post("/equipos/{equipoId}/entrenadores/new", TEST_EQUIPO_ID).with(csrf())
				.param("firstName", "Juan").param("lastName", "Garcia").param("email", "juan@gmail.com")
				.param("birthDate", "2020/07/22").param("genre", "hombre").param("telephone", "666666666")
				.param("username", "asd").param("password", "asd")).andExpect(status().is2xxSuccessful());

	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewEntrenadorFormHasErrors() throws Exception {
		mockMvc.perform(post("/equipos/{equipoId}/entrenadores/new", TEST_EQUIPO_ID).with(csrf())
				.param("first name", "hola").param("last name", "Romano").param("email", "manuel@gmail.com")
				.param("birthDate", "2020/01/01").param("genre", "genre").param("telephone", "666666666")
				.param("username", "")).andExpect(model().attributeHasErrors("entrenador"))
				.andExpect(status().isOk()).andExpect(view().name("entrenadores/createOrUpdateEntrenadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testEntrenadorUpdateForm() throws Exception {
		mockMvc.perform(get("/equipos/{equipoId}/entrenadores/{entrenadorId}/edit", TEST_EQUIPO_ID, TEST_ENTRENADOR_ID))
				.andExpect(status().isOk()).andExpect(view().name("entrenadores/createOrUpdateEntrenadorForm"));

	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateEntrenadorFormSuccess() throws Exception {
		mockMvc.perform(post("/equipos/{equipoId}/entrenadores/{entrenadorId}/edit", TEST_EQUIPO_ID, TEST_ENTRENADOR_ID)
				.with(csrf()).param("first name", "Manuel").param("last name", "Sanchez")
				.param("email", "manuel@gmail.com").param("birthDate", "2020/01/01").param("genre", "genre")
				.param("telephone", "666666666").param("username", "romano")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/equipos/{equipoId}"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateEntrenadorFormHasErrors() throws Exception {
		mockMvc.perform(
				post("/equipos/{equipoId}/entrenadores/{entrenadorId}/edit", TEST_ENTRENADOR_ID, TEST_ENTRENADOR_ID)
						.with(csrf()).param("first name", "").param("last name", "Romano")
						.param("email", "manuel@gmail.com").param("birthDate", "2020/01/01").param("genre", "genre")
						.param("telephone", "666666666").param("username", "romano"))
				.andExpect(model().attributeHasErrors("entrenador")).andExpect(status().isOk())
				.andExpect(view().name("entrenadores/createOrUpdateEntrenadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowEntrenador() throws Exception {
		mockMvc.perform(get("/entrenadores/{entrenadorId}", TEST_ENTRENADOR_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("entrenador"))
				.andExpect(view().name("entrenadores/entrenadorDetails"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowListEntrenadores() throws Exception {
		mockMvc.perform(get("/entrenadores/list")).andExpect(status().isOk())
				.andExpect(model().attributeExists("entrenadores"))
				.andExpect(view().name("entrenadores/entrenadoresList"));
	}



}
