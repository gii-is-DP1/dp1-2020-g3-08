/*
 * 
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
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.UsuarioRegistrado;
import org.springframework.samples.petclinic.model.Vet;


public interface EquipoRepository extends Repository<Equipo, Integer>{
	
	void save(Equipo equipo) throws DataAccessException;

	Collection<Equipo> findAll() throws DataAccessException;

}
