package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Arbitros {

	List<Arbitro> arbitros;

	@XmlElement
	public List<Arbitro> getArbitroList() {
		if (arbitros == null) {
			arbitros = new ArrayList<>();
		}
		return arbitros;
	}

	

}
