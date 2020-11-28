//package org.springframework.samples.petclinic.web;
//
//import org.assertj.core.util.Lists;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
//import org.springframework.samples.petclinic.model.Equipo;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.hamcrest.Matchers.hasProperty;
//import static org.hamcrest.Matchers.is;
//import static org.mockito.BDDMockito.given;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.samples.petclinic.service.AuthoritiesService;
//import org.springframework.samples.petclinic.service.EquipoService;
//import org.springframework.samples.petclinic.service.UserService;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.test.context.support.WithMockUser;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//
///**
// * Test class for {@link EquipoController}
// *
// * @author Colin But
// */
//
//@WebMvcTest(controllers=EquipoController.class,
//		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
//		excludeAutoConfiguration= SecurityConfiguration.class)
//class EquipoControllerTests {
//
//	private static final int TEST_Equipo_ID = 1;
//
//	@Autowired
//	private EquipoController EquipoController;
//
//	@MockBean
//	private EquipoService clinicService;
//
//        @MockBean
//	private UserService userService;
//
//        @MockBean
//        private AuthoritiesService authoritiesService;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	private Equipo george;
//
//	@BeforeEach
//	void setup() {
//
//		george = new Equipo();
//		george.setId(TEST_Equipo_ID);
//		george.setFirstName("George");
//		george.setLastName("Franklin");
//		george.setAddress("110 W. Liberty St.");
//		george.setCity("Madison");
//		george.setTelephone("6085551023");
//		given(this.clinicService.findEquipoById(TEST_Equipo_ID)).willReturn(george);
//
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testInitCreationForm() throws Exception {
//		mockMvc.perform(get("/Equipos/new")).andExpect(status().isOk()).andExpect(model().attributeExists("Equipo"))
//				.andExpect(view().name("Equipos/createOrUpdateEquipoForm"));
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testProcessCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("/Equipos/new").param("firstName", "Joe").param("lastName", "Bloggs")
//							.with(csrf())
//							.param("address", "123 Caramel Street")
//							.param("city", "London")
//							.param("telephone", "01316761638"))
//				.andExpect(status().is3xxRedirection());
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/Equipos/new")
//							.with(csrf())
//							.param("firstName", "Joe")
//							.param("lastName", "Bloggs")
//							.param("city", "London"))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("Equipo"))
//				.andExpect(model().attributeHasFieldErrors("Equipo", "address"))
//				.andExpect(model().attributeHasFieldErrors("Equipo", "telephone"))
//				.andExpect(view().name("Equipos/createOrUpdateEquipoForm"));
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testInitFindForm() throws Exception {
//		mockMvc.perform(get("/Equipos/find")).andExpect(status().isOk()).andExpect(model().attributeExists("Equipo"))
//				.andExpect(view().name("Equipos/findEquipos"));
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testProcessFindFormSuccess() throws Exception {
//		given(this.clinicService.findEquipoByLastName("")).willReturn(Lists.newArrayList(george, new Equipo()));
//
//		mockMvc.perform(get("/Equipos")).andExpect(status().isOk()).andExpect(view().name("Equipos/EquiposList"));
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testProcessFindFormByLastName() throws Exception {
//		given(this.clinicService.findEquipoByLastName(george.getLastName())).willReturn(Lists.newArrayList(george));
//
//		mockMvc.perform(get("/Equipos").param("lastName", "Franklin")).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/Equipos/" + TEST_Equipo_ID));
//	}
//
//        @WithMockUser(value = "spring")
//	@Test
//	void testProcessFindFormNoEquiposFound() throws Exception {
//		mockMvc.perform(get("/Equipos").param("lastName", "Unknown Surname")).andExpect(status().isOk())
//				.andExpect(model().attributeHasFieldErrors("Equipo", "lastName"))
//				.andExpect(model().attributeHasFieldErrorCode("Equipo", "lastName", "notFound"))
//				.andExpect(view().name("Equipos/findEquipos"));
//	}
//
//        @WithMockUser(value = "spring")
//	@Test
//	void testInitUpdateEquipoForm() throws Exception {
//		mockMvc.perform(get("/Equipos/{EquipoId}/edit", TEST_Equipo_ID)).andExpect(status().isOk())
//				.andExpect(model().attributeExists("Equipo"))
//				.andExpect(model().attribute("Equipo", hasProperty("lastName", is("Franklin"))))
//				.andExpect(model().attribute("Equipo", hasProperty("firstName", is("George"))))
//				.andExpect(model().attribute("Equipo", hasProperty("address", is("110 W. Liberty St."))))
//				.andExpect(model().attribute("Equipo", hasProperty("city", is("Madison"))))
//				.andExpect(model().attribute("Equipo", hasProperty("telephone", is("6085551023"))))
//				.andExpect(view().name("Equipos/createOrUpdateEquipoForm"));
//	}
//
//        @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateEquipoFormSuccess() throws Exception {
//		mockMvc.perform(post("/Equipos/{EquipoId}/edit", TEST_Equipo_ID)
//							.with(csrf())
//							.param("firstName", "Joe")
//							.param("lastName", "Bloggs")
//							.param("address", "123 Caramel Street")
//							.param("city", "London")
//							.param("telephone", "01616291589"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/Equipos/{EquipoId}"));
//	}
//
//        @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateEquipoFormHasErrors() throws Exception {
//		mockMvc.perform(post("/Equipos/{EquipoId}/edit", TEST_Equipo_ID)
//							.with(csrf())
//							.param("firstName", "Joe")
//							.param("lastName", "Bloggs")
//							.param("city", "London"))
//				.andExpect(status().isOk())
//				.andExpect(model().attributeHasErrors("Equipo"))
//				.andExpect(model().attributeHasFieldErrors("Equipo", "address"))
//				.andExpect(model().attributeHasFieldErrors("Equipo", "telephone"))
//				.andExpect(view().name("Equipos/createOrUpdateEquipoForm"));
//	}
//
//        @WithMockUser(value = "spring")
//	@Test
//	void testShowEquipo() throws Exception {
//		mockMvc.perform(get("/Equipos/{EquipoId}", TEST_Equipo_ID)).andExpect(status().isOk())
//				.andExpect(model().attribute("Equipo", hasProperty("lastName", is("Franklin"))))
//				.andExpect(model().attribute("Equipo", hasProperty("firstName", is("George"))))
//				.andExpect(model().attribute("Equipo", hasProperty("address", is("110 W. Liberty St."))))
//				.andExpect(model().attribute("Equipo", hasProperty("city", is("Madison"))))
//				.andExpect(model().attribute("Equipo", hasProperty("telephone", is("6085551023"))))
//				.andExpect(view().name("Equipos/EquipoDetails"));
//	}
//
//}
