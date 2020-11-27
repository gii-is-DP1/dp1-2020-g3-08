/*
 *
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

package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Equipo;

public interface EquipoRepository extends Repository<Equipo, Integer> {

	void save(Equipo equipo) throws DataAccessException;

	Collection<Equipo> findAll() throws DataAccessException;

	//	@Query("SELECT DISTINCT equipo FROM Equipo equipo left join fetch equipo.jugadores WHERE equipo.nombre LIKE :nombre%")
	//	Collection<Equipo> findByNombre(@Param("nombre") String nombre);

	@Query("SELECT DISTINCT equipo FROM Equipo equipo WHERE equipo.nombre LIKE :nombre%")
	Collection<Equipo> findByNombre(@Param("nombre") String nombre);

	//	@Query("SELECT equipo FROM Equipo equipo left join fetch equipo.jugadores WHERE equipo.id =:id")
	//	Equipo findById(@Param("id") int id);

	@Query("SELECT equipo FROM Equipo equipo WHERE equipo.id =:id")
	Equipo findById(@Param("id") int id) throws DataAccessException;

	void delete(Equipo equipo);

}
