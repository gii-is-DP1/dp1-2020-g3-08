
package org.springframework.samples.petclinic.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.JugadorService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.JugadorController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = JugadorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class JugadorControllerTests {

	private static final int	TEST_JUGADOR_ID	= 1;
	private static final int	TEST_EQUIPO_ID	= 1;

	@Autowired
	private MockMvc				mockMvc;

	@MockBean
	private JugadorService		jugadorService;

	@MockBean
	private EquipoService		equipoService;

	@MockBean
	private UserService			userService;

	@MockBean
	private AuthoritiesService	authoritiesService;


	@BeforeEach
	void setup() {
		BDDMockito.given(this.jugadorService.findJugadorById(JugadorControllerTests.TEST_JUGADOR_ID)).willReturn(new Jugador());
		BDDMockito.given(this.equipoService.findEquipoById(JugadorControllerTests.TEST_EQUIPO_ID)).willReturn(new Equipo());
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitNewJugadorForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/equipos/{equipoId}/jugadores/new", JugadorControllerTests.TEST_EQUIPO_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("jugadores/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewJugadorFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/equipos/{equipoId}/jugadores/new", JugadorControllerTests.TEST_EQUIPO_ID).param("apellidos", "Apellidos").with(SecurityMockMvcRequestPostProcessors.csrf()).param("dni", "DNI")
			.with(SecurityMockMvcRequestPostProcessors.csrf()).param("fechaNacimiento", "Fecha Nacimiento").with(SecurityMockMvcRequestPostProcessors.csrf()).param("nacionalidad", "Nacionalidad").with(SecurityMockMvcRequestPostProcessors.csrf())
			.param("nombre", "Nombre").with(SecurityMockMvcRequestPostProcessors.csrf()).param("lesion", "Lesion").with(SecurityMockMvcRequestPostProcessors.csrf()).param("tarjetaAmarilla", "Tarjeta Amarilla")
			.with(SecurityMockMvcRequestPostProcessors.csrf()).param("tarjetaRoja", "Tarjeta Roja")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("jugadores/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewJugadorFormHasErrors() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/equipos/{equipoId}/jugadores/new", JugadorControllerTests.TEST_EQUIPO_ID).param("apellidos", "").with(SecurityMockMvcRequestPostProcessors.csrf()).param("dni", "DNI")
				.with(SecurityMockMvcRequestPostProcessors.csrf()).param("fechaNacimiento", "Fecha Nacimiento").with(SecurityMockMvcRequestPostProcessors.csrf()).param("nacionalidad", "Nacionalidad").with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("nombre", "Nombre").with(SecurityMockMvcRequestPostProcessors.csrf()).param("lesion", "Lesion").with(SecurityMockMvcRequestPostProcessors.csrf()).param("tarjetaAmarilla", "Tarjeta Amarilla")
				.with(SecurityMockMvcRequestPostProcessors.csrf()).param("tarjetaRoja", "Tarjeta Roja"))
			.andExpect(MockMvcResultMatchers.model().attributeHasErrors("jugador")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("jugadores/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testJugadoresFindForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/jugadores/find")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("jugadores/findJugadores"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testJugadorUpdateForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/equipos/{equipoId}/jugadores/{jugadorId}/edit", JugadorControllerTests.TEST_EQUIPO_ID, JugadorControllerTests.TEST_JUGADOR_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("jugadores/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateJugadorFormSuccess() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/equipos/{equipoId}/jugadores/{jugadorId}/edit", JugadorControllerTests.TEST_EQUIPO_ID, JugadorControllerTests.TEST_JUGADOR_ID).param("apellidos", "Apellidos")
				.with(SecurityMockMvcRequestPostProcessors.csrf()).param("dni", "DNI").with(SecurityMockMvcRequestPostProcessors.csrf()).param("fechaNacimiento", "Fecha Nacimiento").with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("nacionalidad", "Nacionalidad").with(SecurityMockMvcRequestPostProcessors.csrf()).param("nombre", "Nombre").with(SecurityMockMvcRequestPostProcessors.csrf()).param("lesion", "Lesion").with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("tarjetaAmarilla", "Tarjeta Amarilla").with(SecurityMockMvcRequestPostProcessors.csrf()).param("tarjetaRoja", "Tarjeta Roja"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.view().name("jugadores/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateJugadorFormHasErrors() throws Exception {
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/equipos/{equipoId}/jugadores/{jugadorId}/edit", JugadorControllerTests.TEST_EQUIPO_ID, JugadorControllerTests.TEST_JUGADOR_ID).param("apellidos", "Apellidos")
				.with(SecurityMockMvcRequestPostProcessors.csrf()).param("dni", "").with(SecurityMockMvcRequestPostProcessors.csrf()).param("fechaNacimiento", "Fecha Nacimiento").with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("nacionalidad", "Nacionalidad").with(SecurityMockMvcRequestPostProcessors.csrf()).param("nombre", "Nombre").with(SecurityMockMvcRequestPostProcessors.csrf()).param("lesion", "Lesion").with(SecurityMockMvcRequestPostProcessors.csrf())
				.param("tarjetaAmarilla", "Tarjeta Amarilla").with(SecurityMockMvcRequestPostProcessors.csrf()).param("tarjetaRoja", "Tarjeta Roja"))
			.andExpect(MockMvcResultMatchers.model().attributeHasErrors("jugador")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("jugadores/createOrUpdateJugadorForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowListJugadores() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/jugadores/list")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("jugadores"))
			.andExpect(MockMvcResultMatchers.view().name("jugadores/jugadoresList"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testDeleteJugador() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/equipos/{equipoId}/jugadores/{jugadorId}/delete", JugadorControllerTests.TEST_EQUIPO_ID, JugadorControllerTests.TEST_JUGADOR_ID))
			.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("jugador")).andExpect(MockMvcResultMatchers.view().name("redirect:/equipos/{equipoId}"));
	}

}
