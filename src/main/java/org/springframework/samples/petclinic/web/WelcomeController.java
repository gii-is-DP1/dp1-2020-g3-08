
package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping({
		"/", "/welcome"
	})
	public String welcome(final Map<String, Object> model) {
		List<Person> persons = new ArrayList<Person>();
		Person person = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		Person person4 = new Person();
		Person person5 = new Person();
		Person person6 = new Person();
		person.setNombre("Marcos");
		person.setApellidos("Rodriguez");
		person2.setNombre("Gonzalo");
		person2.setApellidos("Fernandez");
		person3.setNombre("Javier");
		person3.setApellidos("Grosso");
		person4.setNombre("Juan Luis");
		person4.setApellidos("Munoz");
		person5.setNombre("Ignacio");
		person5.setApellidos("Sanabria");
		person6.setNombre("Bogdan");
		person6.setApellidos("Stefan");
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
