
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
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
public class Noticia extends BaseEntity {

	@NotEmpty
	private String		titulo;

	@NotEmpty
	private String		texto;

	@PastOrPresent
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	fecha;

}
