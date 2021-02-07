package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Competiciones {
	private List<Competicion> competiciones;
	public List<Competicion> getCompeticionList() {
		if (competiciones == null) {
			competiciones = new ArrayList<>();
		}
		return competiciones;
	}

}
