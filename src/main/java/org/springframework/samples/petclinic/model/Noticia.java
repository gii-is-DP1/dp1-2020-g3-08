package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "noticias")
public class Noticia extends BaseEntity{

	@NotEmpty
	private String title;

	@NotEmpty
	private String	text;

	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	date;
	
	@ManyToMany
	@JoinTable(name = "partidos_noticias", joinColumns = @JoinColumn(name = "partido_id"),
	inverseJoinColumns = @JoinColumn(name = "noticia_id"))
	private List<Partido> partidos;
	
}
