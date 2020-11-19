package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
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
	
}
