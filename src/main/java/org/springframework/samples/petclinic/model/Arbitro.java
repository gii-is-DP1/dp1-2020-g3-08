package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "arbitros")
public class Arbitro extends BaseEntity{
	
	private String nombreArbitro;

}
