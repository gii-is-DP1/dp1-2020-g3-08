package org.springframework.samples.petclinic.model;






import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "competiciones")
public class Competicion extends BaseEntity {
	@NotEmpty
	String nombreComp;

	@Override
	public String toString() {
		return "Competicion [NombreComp=" + nombreComp + "]";
	}
	


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "competicion")
	private Set<Equipo>	equipos;

	protected Set<Equipo> getEquiposInternal() {
		if (this.equipos == null) {
			this.equipos = new HashSet<>();
		}
		return this.equipos;
	}

	protected void setEquiposInternal(final Set<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	public List<Equipo> getEquipos() {
		List<Equipo> sortedEquipos = new ArrayList<>(this.getEquiposInternal());
		PropertyComparator.sort(sortedEquipos, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedEquipos);
	}
	public void addEquipo(@Valid Equipo equipo) {
		this.getEquiposInternal().add(equipo);
		equipo.setCompeticion(this);
		
	}
	public boolean removeEquipo(final Equipo equipo) {
		return this.getEquiposInternal().remove(equipo);
	}

	public Equipo getEquipo(final String name) {
		return this.getEquipo(name);
	}

	
	public Equipo getEquipo(String name, final boolean ignoreNew) {
		name = name.toLowerCase();
		for (Equipo equipo : this.getEquiposInternal()) {
			if (!ignoreNew || !equipo.isNew()) {
				String compName = equipo.getNombre();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return equipo;
				}
			}
		}
		return null;
	}



}
