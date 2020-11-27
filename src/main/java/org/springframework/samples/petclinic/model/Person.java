/*
 * Copyright 2002-2013 the original author or authors.
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

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Ken Krebs
 */
@MappedSuperclass
@Getter
@Setter
public class Person extends BaseEntity {

	@Column(name = "nombre")
	@NotEmpty
	@Length(min = 3, max = 16)
	protected String	nombre;

	@Column(name = "apellidos")
	@NotEmpty
	@Length(min = 3, max = 24)
	protected String	apellidos;

	@Column(name = "fecha_nacimiento")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected LocalDate	fecha_nacimiento;

	@Column(name = "nacionalidad")
	@NotEmpty
	protected String	nacionalidad;

	@Column(name = "dni")
	@NotEmpty
	@Pattern(regexp = "[0-9]{8,8}[A-Z]")
	protected String	dni;

}
