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
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Sexo;
import org.springframework.samples.petclinic.model.UsuarioRegistrado;
import org.springframework.samples.petclinic.repository.UsuarioRegistradoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
	public Collection<Sexo> findGenres() throws DataAccessException {
		return usuarioRegistradoRepository.findGenres();
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
	public void saveUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) throws DataAccessException {
		usuarioRegistradoRepository.save(usuarioRegistrado);
		userService.saveUser(usuarioRegistrado.getUser());
		authoritiesService.saveAuthorities(usuarioRegistrado.getUser().getUsername(), "usuarioRegistrado");
	}

}
