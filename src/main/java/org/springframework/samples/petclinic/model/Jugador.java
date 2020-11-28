
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

	@Column(name = "t_amarilla")
	protected Integer	t_amarilla;

	@Column(name = "t_roja")
	protected Integer	t_roja;

	@Column(name = "lesion")
	protected Boolean	lesion;

	@ManyToOne
	@JoinColumn(name = "equipo_id")
	private Equipo		equipo;

}
