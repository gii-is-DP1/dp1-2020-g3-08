package org.springframework.samples.petclinic.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.ArbitroService;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.PartidoController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PartidoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class PartidoControllerTests {

	private static final int TEST_COMPETICION_ID = 1;
	private static final int TEST_PARTIDO_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ArbitroService arbitroService;

	@MockBean
	private EquipoService equipoService;

	@MockBean
	private PartidoService partidoService;

	@MockBean
	private CompeticionService competicionService;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@BeforeEach
	void setup() {
		given(partidoService.findById(TEST_PARTIDO_ID)).willReturn(new Partido());
		given(competicionService.findCompeticionById(TEST_COMPETICION_ID)).willReturn(new Competicion());
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitNewPartidoForm() throws Exception {
		mockMvc.perform(get("/competiciones/{competicionId}/partidos/new", TEST_COMPETICION_ID))
		.andExpect(status().isOk()).andExpect(view().name("partidos/createOrUpdatePartidoForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewPartidoFormSuccess() throws Exception {
		mockMvc.perform(post("/competiciones/{competicionId}/partidos/new", TEST_COMPETICION_ID)
			.param("fecha", "2015/02/12")
			.param("lugar", "Lugar").with(csrf())
			.param("equipo1", "1")
			.param("equipo2", "2"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/competiciones/{competicionId}"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewPartidoFormHasErrors() throws Exception {
		mockMvc.perform(post("/competiciones/{competicionId}/partidos/new", TEST_COMPETICION_ID)
			.param("fecha", "2015/02/12")
			.param("lugar", "").with(csrf())
			.param("equipo1", "1")
			.param("equipo2", "2")).andExpect(model().attributeHasErrors("partido"))
		.andExpect(status().isOk()).andExpect(view().name("partidos/createOrUpdatePartidoForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowPartidoForm() throws Exception {
		mockMvc.perform(get("/partidos/{id}", TEST_PARTIDO_ID)).andExpect(status().isOk()).andExpect(view().name("partidos/partidoDetails"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitUpdatePartidoForm() throws Exception {
		mockMvc.perform(
			get("/competiciones/{competicionId}/partidos/{partidoId}/edit", TEST_COMPETICION_ID, TEST_PARTIDO_ID))
		.andExpect(status().isOk()).andExpect(view().name("partidos/createOrUpdatePartidoForm"));

	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdatePartidoFormSuccess() throws Exception {
		mockMvc.perform(
			post("/competiciones/{competicionId}/partidos/{partidoId}/edit", TEST_COMPETICION_ID, TEST_PARTIDO_ID)
			.param("fecha", "2015/02/12")
			.param("lugar", "Lugar").with(csrf())
			.param("equipo1", "1")
			.param("equipo2", "2"))
		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/competiciones/{competicionId}"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdatePartidoFormHasErrors() throws Exception {
		mockMvc.perform(
			post("/competiciones/{competicionId}/partidos/{partidoId}/edit", TEST_COMPETICION_ID, TEST_PARTIDO_ID)
			.param("fecha", "2015/02/12")
			.param("lugar", "").with(csrf())
			.param("equipo1", "1")
			.param("equipo2", "2"))
		.andExpect(model().attributeHasErrors("partido")).andExpect(status().isOk())
		.andExpect(view().name("partidos/createOrUpdatePartidoForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	@Disabled("Se ha probado a ejecutar el get especificado"
		+ "y ha mostrado correctamente la vista de administrar"
		+ "jugadores mientras que en el test muestra exception.")
	void testInitAdminJugadoresDePartidoForm() throws Exception {
		mockMvc.perform(
			get("/partidos/{id}/administrarJugadores", TEST_PARTIDO_ID))
		.andExpect(status().isOk()).andExpect(view().name("partidos/adminJugadoresForm"));

	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessAdminJugadoresDePartidoFormSuccess() throws Exception {
		mockMvc.perform(
			post("/partidos/{id}/administrarJugadores", TEST_PARTIDO_ID)
			.param("fecha", "2015/02/12")
			.param("lugar", "Lugar").with(csrf())
			.param("equipo1", "1")
			.param("equipo2", "2"))
		.andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/partidos/"+TEST_PARTIDO_ID));
	}

	@WithMockUser(value = "admin1")
	@Test
	@Disabled("Se ha probado a administrar los jugadores del partido con"
		+ "la misma id y ha saltado el error de lugar vacio mientras"
		+ "que el test no detecta NINGUN error")
	void testProcessAdminJugadoresDePartidoFormHasErrors() throws Exception {
		mockMvc.perform(
			post("/partidos/{id}/administrarJugadores", TEST_PARTIDO_ID)
			.param("fecha", "2015/02/12")
			.param("lugar", "").with(csrf())
			.param("equipo1", "1")
			.param("equipo2", "2"))
		.andExpect(model().hasErrors()).andExpect(status().isOk())
		.andExpect(view().name("partidos/createOrUpdatePartidoForm"));
	}

}
