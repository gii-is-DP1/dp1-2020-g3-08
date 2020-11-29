package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entrenadores {
	private List<Entrenador> entrenadores;

	@XmlElement
	public List<Entrenador> getEntrenadorList() {
		if (entrenadores == null) {
			entrenadores = new ArrayList<>();
		}
		return entrenadores;
	}
}
