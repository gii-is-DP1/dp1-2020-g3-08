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
import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.service.ArbitroService;
import org.springframework.samples.petclinic.web.ArbitroController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ArbitroController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),excludeAutoConfiguration = SecurityConfiguration.class)

public class ArbitroControllerTests {
	
	private static final int TEST_ARBITRO_ID = 1;
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ArbitroController arbitroController;

	@MockBean
	private ArbitroService arbitroService;
	
	@BeforeEach
	void setup() {
		given(arbitroService.findById(TEST_ARBITRO_ID)).willReturn(new Arbitro());
	}
	
	@WithMockUser(value="admin1")
	@Test
	void testInitNewArbitroForm() throws Exception {
		mockMvc.perform(get("/arbitros/new")).andExpect(status().isOk())
		.andExpect(view().name("arbitros/createOrUpdateArbitroForm"));
	}
	
/*	@WithMockUser(value="admin1")
	@Test
	void testProcessNewArbitroFormSuccess() throws Exception {
		mockMvc.perform(post("/arbitros/new")	
			.param("nombre", "Nombre")
			.with(csrf())
			.param("apellidos", "Apellidos")
			.param("dni", "Dni")
			.param("fechaNacimiento", "FechaNacimiento")
			.param("nacionalidad", "Nacionalidad"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/arbitros/null")); //.andExpect(view().name("redirect:/arbitros/null"));   redirect:/arbitros/{arbitroId}
	} */
	
	@WithMockUser(value="admin1")
	@Test
	void testProcessNewArbitroFormHasErrors() throws Exception {
		mockMvc.perform(post("/arbitros/new")   //mockMvc.perform(post("/arbitros/{arbitroId}/new")
			.param("nombre", "")
			.with(csrf())
			.param("apellidos", "Apellidos")
			.param("dni", "Dni")
			.param("fechaNacimiento", "FechaNacimiento")
			.param("nacionalidad", "Nacionalidad"))
		.andExpect(model().attributeHasErrors("arbitro"))
		.andExpect(status().isOk())
		.andExpect(view().name("arbitros/createOrUpdateArbitroForm"));
	}
	
	@WithMockUser(value="admin1")
	@Test
	void testArbitrosFindForm() throws Exception {
		mockMvc.perform(get("/arbitros/find")).andExpect(status().isOk())
		.andExpect(view().name("arbitros/findArbitros"));
	}
	
	@WithMockUser(value="admin1")
	@Test
	void testArbitroUpdateForm() throws Exception {
		mockMvc.perform(get("/arbitros/{arbitroId}/edit",TEST_ARBITRO_ID)).andExpect(status().isOk())
		.andExpect(view().name("arbitros/createOrUpdateArbitroForm"));
	}
	
/*	@WithMockUser(value="admin1")
	@Test
	void testProcessUpdateArbitroFormSuccess() throws Exception {
		mockMvc.perform(post("/arbitros/{arbitroId}/edit",TEST_ARBITRO_ID)
				.param("nombre", "Nombre")
				.with(csrf())
				.param("apellidos", "Apellidos")
				.param("dni", "Dni")
				.param("fechaNacimiento", "FechaNacimiento")
				.param("nacionalidad", "Nacionalidad"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/arbitros/{arbitroId}"));
	} */
	
/*	@WithMockUser(value="admin1")
	@Test
	void testProcessUpdateArbitroFormHasErrors() throws Exception {
		mockMvc.perform(post("arbitros/{arbitroId}/edit",TEST_ARBITRO_ID)
				.param("nombre", "")
				.with(csrf())
				.param("apellidos", "Apellidos")
				.param("dni", "Dni")
				.param("fechaNacimiento", "FechaNacimiento")
				.param("nacionalidad", "Nacionalidad"))
		.andExpect(model().attributeHasErrors("arbitro"))
		.andExpect(status().isOk())
		.andExpect(view().name("arbitros/createOrUpdateArbitroForm"));
	} */
	
	@WithMockUser(value = "admin1")
	@Test
	void testShowArbitro() throws Exception {
		mockMvc.perform(get("/arbitros/{arbitroId}", TEST_ARBITRO_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("arbitro")).andExpect(view().name("arbitros/arbitroDetails"));
	}
	
/*	@WithMockUser(value = "admin1")
	@Test
	void testShowListArbitros() throws Exception {
		mockMvc.perform(get("/arbitros/list")).andExpect(status().isOk())
		.andExpect(model().attributeExists("arbitros")).andExpect(view().name("arbitros/arbitrosList"));
	} */
	
/*	@WithMockUser(value = "admin1")
	@Test
	void testDeleteArbitro() throws Exception {
		mockMvc.perform(get("/arbitros/delete", TEST_ARBITRO_ID))  ///arbitros/{arbitroId}/delete
		.andExpect(model().attributeDoesNotExist("arbitro")).andExpect(view().name("redirect:/arbitros/{arbitroId}"));
	} */
}

