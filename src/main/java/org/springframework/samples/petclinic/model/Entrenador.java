package org.springframework.samples.petclinic.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entrenador")
public class Entrenador extends BaseEntity {
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;

	public Entrenador getEntrenadorwithIdDifferent(Set<Entrenador> entrenadores,String telefono, final Integer id) {
		
		for (Entrenador entrenador : entrenadores) {
			String compTel = entrenador.getUser().getTelephone();
			if (compTel.equals(telefono) && entrenador.getId() != id) {
				return entrenador;
			}
		}
		return null;
	}
}
