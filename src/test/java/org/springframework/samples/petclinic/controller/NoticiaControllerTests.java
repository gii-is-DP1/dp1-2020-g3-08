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
import org.springframework.samples.petclinic.model.Noticia;
import org.springframework.samples.petclinic.service.NoticiaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.samples.petclinic.web.NoticiaController;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = NoticiaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),excludeAutoConfiguration = SecurityConfiguration.class)

public class NoticiaControllerTests {

	private static final int TEST_NOTICIA_ID = 1;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private NoticiaController noticiaController;

	@MockBean
	private NoticiaService noticiaService;

	@MockBean
	private PartidoService partidoService;

	@BeforeEach
	void setup() {
		given(noticiaService.findById(TEST_NOTICIA_ID)).willReturn(new Noticia());
	}

	@WithMockUser(value="admin1")
	@Test
	void testInitNewNoticiaForm() throws Exception {
		mockMvc.perform(get("/noticias/new")).andExpect(status().isOk())
		.andExpect(view().name("noticias/createOrUpdateNoticiaForm"));
	}

	@WithMockUser(value="admin1")
	@Test
	void testProcessNewNoticiaFormSuccess() throws Exception {
		mockMvc.perform(post("/noticias/new")
			.param("title", "Titulo")
			.with(csrf())
			.param("text", "Texto"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/noticias/null"));
	}


	@WithMockUser(value="admin1")
	@Test
	void testProcessNewNoticiaFormHasErrors() throws Exception {
		mockMvc.perform(post("/noticias/new")
			.param("title", "")
			.with(csrf())
			.param("date", "2015/02/12")
			.param("text", "Texto"))
		.andExpect(model().attributeHasErrors("noticia"))
		.andExpect(status().isOk())
		.andExpect(view().name("noticias/createOrUpdateNoticiaForm"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowNoticia() throws Exception {
		mockMvc.perform(get("/noticias/{noticiaId}", TEST_NOTICIA_ID)).andExpect(status().isOk())
		.andExpect(model().attributeExists("noticia")).andExpect(view().name("noticias/noticiaDetails"));
	}

	@WithMockUser(value = "admin1")
	@Test
	void testShowListNoticias() throws Exception {
		mockMvc.perform(get("/noticias/list")).andExpect(status().isOk())
		.andExpect(model().attributeExists("noticias")).andExpect(view().name("noticias/noticiasList"));
	}

}
