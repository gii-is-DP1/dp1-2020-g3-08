package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  List<Person> persons= new ArrayList<Person>();
		  Person person= new Person();
		  Person person2= new Person();
		  Person person3= new Person();
		  Person person4= new Person();
		  Person person5= new Person();
		  Person person6= new Person();
		  person.setFirstName("Marcos");
		  person.setLastName("Rodriguez");
		  person2.setFirstName("Gonzalo");
		  person2.setLastName("Fernandez");
		  person3.setFirstName("Javier");
		  person3.setLastName("Grosso");
		  person4.setFirstName("Juan Luis");
		  person4.setLastName("Munoz");
		  person5.setFirstName("Ignacio");
		  person5.setLastName("Sanabria");
		  person6.setFirstName("Bogdan");
		  person6.setLastName("Stefan");
		  persons.add(person);
		  persons.add(person2);
		  persons.add(person3);
		  persons.add(person4);
		  persons.add(person5);
		  persons.add(person6);
		  model.put("persons", persons);
		  model.put("title", "Prueba");
		  model.put("group", "G3-08");

	    return "welcome";
	  }
}
