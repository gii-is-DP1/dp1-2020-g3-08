package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	

}
