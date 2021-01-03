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

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Noticia;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.NoticiaService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoticiaController {

	private static final String				VIEWS_NOTICIA_CREATE_OR_UPDATE_FORM= "noticias/createOrUpdateNoticiaForm";
	private static final String				VIEWS_NOTICIA_SHOW					= "noticias/noticiaDetails";

	private final NoticiaService	noticiaService;

	private final PartidoService	partidoService;


	@Autowired
	public NoticiaController(NoticiaService noticiaService,PartidoService partidoService) {
		this.noticiaService = noticiaService;
		this.partidoService = partidoService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("partidos")
	public Collection<Partido> populatePartidos() {
		return partidoService.findAll();
	}

	@GetMapping(value = "/noticias/new")
	public String initCreationForm(Map<String, Object> model) {
		Noticia noticia = new Noticia();
		model.put("noticia", noticia);
		return VIEWS_NOTICIA_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/noticias/new")
	public String processCreationForm(@Valid Noticia noticia, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_NOTICIA_CREATE_OR_UPDATE_FORM;
		}
		else {
			LocalDate fechaActual = LocalDate.now();
			noticia.setDate(fechaActual);
			noticiaService.saveNoticia(noticia);

			return "redirect:/noticias/" + noticia.getId();
		}
	}

	@GetMapping("/noticias/{id}")
	public ModelAndView showNoticia(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(VIEWS_NOTICIA_SHOW);
		mav.addObject(noticiaService.findById(id));
		return mav;
	}

	//Lista de noticias
	@GetMapping("/noticias/list")
	public String showEquipoList(final Map<String, Object> model) {
		Collection<Noticia> noticias = noticiaService.findAll();
		model.put("noticias", noticias);
		return "noticias/noticiasList";
	}

}
