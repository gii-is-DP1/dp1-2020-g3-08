/*
 * Copyright Futvilla Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partidos")
public class Partido extends BaseEntity {

	/*
	 * @NotEmpty private String fecha;
	 */

	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;

	@NotEmpty
	private String lugar;

	@ManyToOne(optional = true)
	@JoinColumn(name = "arbitro_id")
	private Set<Arbitro> arbitro;
	
	protected Set<Arbitro> getArbitrosInternal() {
		if (this.arbitro == null) {
			this.arbitro = new HashSet<>();
		}
		return this.arbitro;
	}

	protected void setArbitrosInternal(final Set<Arbitro> arbitros) {
		this.arbitro = arbitros;
	}

	public List<Arbitro> getArbitros() {
		List<Arbitro> sortedArbitros = new ArrayList<>(this.getArbitrosInternal());
		PropertyComparator.sort(sortedArbitros, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedArbitros);
	}

	@SuppressWarnings("unchecked")
	public void addArbitro(final Arbitro arbitro) {
		this.getArbitrosInternal().add(arbitro);
		arbitro.setPartidos((Set<Partido>) this);
	}

	public boolean removeArbitro(final Arbitro arbitro) {
		return this.getArbitrosInternal().remove(arbitro);
	}

	public Arbitro getArbitro(final String name) {
		return this.getPartido(name, false);
	}
	
	public Arbitro getPartido(String name, final boolean ignoreNew) {
		name = name.toLowerCase();
		for (Arbitro arbitro: this.getArbitrosInternal()) {
			if (!ignoreNew || !arbitro.isNew()) {
				String compName = arbitro.getNombre();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return arbitro;
				}
			}
		}
		return null;
	}







}
