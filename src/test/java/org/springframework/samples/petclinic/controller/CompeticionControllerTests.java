
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
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.samples.petclinic.web.CompeticionController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = CompeticionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)

public class CompeticionControllerTests {

	private static final int	TEST_COMPETICION_ID	= 1;

	@Autowired
	private MockMvc				mockMvc;

	@MockBean
	private CompeticionService	competicionService;

	@MockBean
	private UserService			userService;

	@MockBean
	private AuthoritiesService	authoritiesService;


	@BeforeEach
	void setup() {
		BDDMockito.given(this.competicionService.findCompeticionById(CompeticionControllerTests.TEST_COMPETICION_ID)).willReturn(new Competicion());
	}

	@WithMockUser(value = "admin1")
	@Test
	void testInitNewCompeticionForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/competiciones/new")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("competiciones/createOrUpdateCompeticionForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewCompeticionFormSuccess() throws Exception {
		Competicion competicion = new Competicion();
		this.mockMvc.perform(MockMvcRequestBuilders.post("/competiciones/new").param("nombreComp", "Nombre").with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.view().name("redirect:/competiciones/" + competicion.getId()));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessNewCompeticionFormHasErrors() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/competiciones/new").param("nombreComp", "").with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.model().attributeHasErrors("competicion"))
			.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("competiciones/createOrUpdateCompeticionForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testCompeticionUpdateForm() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/competiciones/{competicionId}/edit", CompeticionControllerTests.TEST_COMPETICION_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("competiciones/createOrUpdateCompeticionForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateCompeticionFormSuccess() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/competiciones/{competicionId}/edit", CompeticionControllerTests.TEST_COMPETICION_ID).param("nombreComp", "Nombre").with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(MockMvcResultMatchers.status().is3xxRedirection()).andExpect(MockMvcResultMatchers.view().name("redirect:/competiciones/{competicionId}"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testProcessUpdateCompeticionFormHasErrors() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.post("/competiciones/{competicionId}/edit", CompeticionControllerTests.TEST_COMPETICION_ID).param("nombreComp", "").with(SecurityMockMvcRequestPostProcessors.csrf()))
			.andExpect(MockMvcResultMatchers.model().attributeHasErrors("competicion")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("competiciones/createOrUpdateCompeticionForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowCompeticion() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/competiciones/{competicionId}", CompeticionControllerTests.TEST_COMPETICION_ID)).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attributeExists("competicion")).andExpect(MockMvcResultMatchers.view().name("competiciones/competicionDetails"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowListCompeticiones() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/competiciones/list")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.model().attributeExists("competiciones"))
			.andExpect(MockMvcResultMatchers.view().name("competiciones/competicionesList"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testDeleteCompeticion() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/competiciones/{competicionId}/delete", CompeticionControllerTests.TEST_COMPETICION_ID)).andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("competicion"))
			.andExpect(MockMvcResultMatchers.view().name("redirect:/competiciones/list"));
	}

}
