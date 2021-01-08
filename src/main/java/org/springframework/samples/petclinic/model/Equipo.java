/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 */
@Getter
@Setter
@Entity
@Table(name = "equipos")
public class Equipo extends BaseEntity {

	@Column(name = "nombre")
	@Size(min = 4, max = 50)
	@NotEmpty
	private String			nombre;

	@NotEmpty
	@Column(name = "lugar")
	private String			lugar;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "equipo")
	private Set<Jugador>	jugadores;

	//	@ManyToMany
	//	@JoinTable(name = "equipo_partido", joinColumns = @JoinColumn(name = "partido_id"), inverseJoinColumns = @JoinColumn(name = "equipo_id"))
	//	private Set<Partido>	partidos;


	protected Set<Jugador> getJugadoresInternal() {
		if (this.jugadores == null) {
			this.jugadores = new HashSet<>();
		}
		return this.jugadores;
	}

	protected void setJugadoresInternal(final Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugador> getJugadores() {
		List<Jugador> sortedJugadores = new ArrayList<>(this.getJugadoresInternal());
		PropertyComparator.sort(sortedJugadores, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedJugadores);
	}

	public void addJugador(final Jugador jugador) {
		this.getJugadoresInternal().add(jugador);
		jugador.setEquipo(this);
	}

	public boolean removeJugador(final Jugador jugador) {
		return this.getJugadoresInternal().remove(jugador);
	}

	public Jugador getJugador(final String name) {
		return this.getJugador(name, false);
	}

	public Jugador getJugadorwithIdDifferent(String dni, final Integer id) {
		dni = dni.toLowerCase();
		for (Jugador jugador : this.getJugadoresInternal()) {
			String compName = jugador.getDni();
			compName = compName.toLowerCase();
			if (compName.equals(dni) && jugador.getId() != id) {
				return jugador;
			}
		}
		return null;
	}

	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 *
	 * @param name
	 *            to test
	 * @return true if pet name is already in use
	 */
	public Jugador getJugador(String name, final boolean ignoreNew) {
		name = name.toLowerCase();
		for (Jugador jugador : this.getJugadoresInternal()) {
			if (!ignoreNew || !jugador.isNew()) {
				String compName = jugador.getNombre();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return jugador;
				}
			}
		}
		return null;
	}

	//	protected Set<Partido> getPartidosInternal() {
	//		if (this.partidos == null) {
	//			this.partidos = new HashSet<>();
	//		}
	//		return this.partidos;
	//	}
	//
	//	protected void setPartidosInternal(final Set<Partido> partidos) {
	//		this.partidos = partidos;
	//	}
	//
	//	public boolean removePartido(final Partido partido) {
	//		return this.getPartidosInternal().remove(partido);
	//	}
	//
	//	public boolean removeAllPartidos(final Partido partido) {
	//		return this.getPartidosInternal().removeAll(this.partidos);
	//	}

}
