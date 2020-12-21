
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jugador")
public class Jugador extends Person {

	@Column(name = "tarjetaAmarilla")
	protected Integer	tarjetaAmarilla;

	@Column(name = "tarjetaRoja")
	protected Integer	tarjetaRoja;

	@Column(name = "lesion")
	protected Boolean	lesion;

	@ManyToOne
	@JoinColumn(name = "equipoId")
	private Equipo		equipo;

}
