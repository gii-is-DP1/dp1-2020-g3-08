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
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Noticia;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.NoticiaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.EquipoController;
import org.springframework.samples.petclinic.web.NoticiaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = EquipoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),excludeAutoConfiguration = SecurityConfiguration.class)

public class EquipoControllerTests {

	private static final int TEST_EQUIPO_ID = 1;
	private static final int TEST_COMPETICION_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EquipoController equipoController;

	@MockBean
	private EquipoService equipoService;

	@MockBean
	private CompeticionService competicionService;
	
	@MockBean
	private UserService userService;
        
    @MockBean
    private AuthoritiesService authoritiesService; 

	@BeforeEach
	void setup() {
		given(equipoService.findEquipoById(TEST_EQUIPO_ID)).willReturn(new Equipo());
		given(competicionService.findCompeticionById(TEST_COMPETICION_ID)).willReturn(new Competicion());
	}

	@WithMockUser(value="admin1")
	@Test
	void testInitNewEquipoForm() throws Exception {
		mockMvc.perform(get("/competiciones/{competicionId}/equipos/new", TEST_COMPETICION_ID)).andExpect(status().isOk())
		.andExpect(view().name("equipos/createOrUpdateEquiposForm"));
	}

	@WithMockUser(value="admin1")
	@Test
	void testProcessNewEquipoFormSuccess() throws Exception {
		mockMvc.perform(post("/competiciones/{competicionId}/equipos/new", TEST_COMPETICION_ID)
			.param("nombre", "Nombre")
			.with(csrf())
			.param("lugar", "Lugar"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/competiciones/{competicionId}"));
	}


	@WithMockUser(value="admin1")
	@Test
	void testProcessNewEquipoFormHasErrors() throws Exception {
		mockMvc.perform(post("/competiciones/{competicionId}/equipos/new", TEST_COMPETICION_ID)
			.param("nombre", "")
			.with(csrf())
			.param("lugar", "Lugar"))
		.andExpect(model().attributeHasErrors("equipo"))
		.andExpect(status().isOk())
		.andExpect(view().name("equipos/createOrUpdateEquiposForm"));
	}

	@WithMockUser(value="admin1")
	@Test
	void testEquiposFindForm() throws Exception {
		mockMvc.perform(get("/equipos/find")).andExpect(status().isOk())
		.andExpect(view().name("equipos/findEquipos"));
	}
	
	@WithMockUser(value="admin1")
	@Test
	void testEquipoUpdateForm() throws Exception {
		mockMvc.perform(get("/competiciones/{competicionId}/equipos/{equipoId}/edit", TEST_COMPETICION_ID,TEST_EQUIPO_ID)).andExpect(status().isOk())
		.andExpect(view().name("equipos/createOrUpdateEquiposForm"));
		
		
	}
	@WithMockUser(value="admin1")
	@Test
	void testProcessUpdateEquipoFormSuccess() throws Exception {
		mockMvc.perform(post("/competiciones/{competicionId}/equipos/{equipoId}/edit", TEST_COMPETICION_ID,TEST_EQUIPO_ID)
				.param("nombre", "Nombre")
				.with(csrf())
				.param("lugar", "Lugar"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/equipos/{equipoId}"));
	}
	
	
	@WithMockUser(value="admin1")
	@Test
	void testProcessUpdateEquipoFormHasErrors() throws Exception {
		mockMvc.perform(post("/competiciones/{competicionId}/equipos/{equipoId}/edit", TEST_COMPETICION_ID,TEST_EQUIPO_ID)
			.param("nombre", "")
			.with(csrf())
			.param("lugar", "Lugar"))
		.andExpect(model().attributeHasErrors("equipo"))
		.andExpect(status().isOk())
		.andExpect(view().name("equipos/createOrUpdateEquiposForm"));
	}

	
	@WithMockUser(value = "admin1")
	@Test
	void testShowEquipo() throws Exception {
		mockMvc.perform(get("/equipos/{equipoId}", TEST_EQUIPO_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("equipo")).andExpect(view().name("equipos/equipoDetails"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowListEquipos() throws Exception {
		mockMvc.perform(get("/equipos/list")).andExpect(status().isOk())
		.andExpect(model().attributeExists("equipos")).andExpect(view().name("equipos/equiposList"));
	}

}
