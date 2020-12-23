package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.service.ArbitroService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ArbitroController {
	private static final String				VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM= "arbitros/createOrUpdateArbitroForm";
	private static final String				VIEWS_ARBITRO_SHOW					= "arbitros/arbitroDetails";

	private final ArbitroService arbitroService;
	
	@Autowired
	public ArbitroController(ArbitroService arbitroService) {
		this.arbitroService = arbitroService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/arbitros/new")
	public String initCreationForm(Map<String, Object> model) {
		Arbitro arbitro = new Arbitro();
		model.put("arbitro", arbitro);
		return VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/arbitros/new")
	public String processCreationForm(@Valid Arbitro arbitro, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ARBITRO_CREATE_OR_UPDATE_FORM;
		}
		else {
			
			arbitroService.saveArbitro(arbitro);

			return "redirect:/arbitros/" + arbitro.getId();
		}
	}

	@GetMapping("/arbitros/{id}")
	public ModelAndView showArbitro(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(VIEWS_ARBITRO_SHOW);
		mav.addObject(arbitroService.findById(id));
		return mav;
	}

	

}
