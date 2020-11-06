/*
 * Copyright Futvilla Team
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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.UsuarioRegistrado;
import org.springframework.samples.petclinic.repository.UsuarioRegistradoRepository;


public interface UsuarioRegistradoRepository extends Repository<UsuarioRegistrado, Integer> {

	
	void save(UsuarioRegistrado usuarioRegistrado) throws DataAccessException;

	
	@Query("SELECT  usuarioRegistrado FROM UsuarioRegistrado usuarioRegistrado ")
	public Collection<UsuarioRegistrado> findAll();
	
	@Query("SELECT usuarioRegistrado FROM UsuarioRegistrado usuarioRegistrado WHERE usuarioRegistrado.id =:id")
	public UsuarioRegistrado findById(@Param("id") int id);
	

}