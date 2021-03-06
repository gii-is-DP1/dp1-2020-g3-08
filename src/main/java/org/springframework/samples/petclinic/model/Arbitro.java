package org.springframework.samples.petclinic.model;





import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "arbitro")
public class Arbitro extends Person {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "arbitro")
	private Set<Partido> partidos;
	
	
	protected Set<Partido> getPartidosInternal() {
		if (this.partidos == null) {
			this.partidos = new HashSet<>();
		}
		return this.partidos;
	}
	
	protected void setPartidosInternal(final Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public void addPartido(Partido partido) {
		getPartidosInternal().add(partido);
	}


	






}
