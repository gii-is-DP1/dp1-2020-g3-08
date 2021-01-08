/*
 * Copyright Futvilla Team
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

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partidos")
public class Partido extends BaseEntity {
	
 /*   @NotEmpty
    private String fecha;  */
    
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha;
    
    @NotEmpty
    private String lugar;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipo1_id", referencedColumnName = "id")
    private Equipo equipo1;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipo2_id", referencedColumnName = "id")
    private Equipo equipo2;
    
    @ManyToMany
    @JoinTable(name = "jugador_partido", joinColumns = @JoinColumn(name = "partido_id"),
	inverseJoinColumns = @JoinColumn(name = "jugador_id"))
	private Set<Jugador>	jugadoresParticipantes;
       
}
