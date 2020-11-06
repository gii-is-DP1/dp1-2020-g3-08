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
package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.UsuarioRegistrado;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.UsuarioRegistradoRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class UsuarioRegistradoService {

	private UsuarioRegistradoRepository usuarioRegistradoRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public UsuarioRegistradoService(UsuarioRegistradoRepository usuarioRegistradoRepository) {
		this.usuarioRegistradoRepository = usuarioRegistradoRepository;
	}	


	@Transactional(readOnly = true)
	public Collection<UsuarioRegistrado> findAll() throws DataAccessException {
		return usuarioRegistradoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public UsuarioRegistrado findUsuarioRegistradoById(int id) throws DataAccessException {
		return usuarioRegistradoRepository.findById(id);
	}

	@Transactional
	public void saveUsuarioRegistrado(UsuarioRegistrado usuarioregiostrado) throws DataAccessException {
		
		usuarioRegistradoRepository.save(usuarioregiostrado);		
	
		userService.saveUser(usuarioregiostrado.getUser());
		
		authoritiesService.saveAuthorities(usuarioregiostrado.getUser().getUsername(), "usuarioRegistrado");
	}		

}
