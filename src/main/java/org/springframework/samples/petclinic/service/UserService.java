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
import org.springframework.samples.petclinic.model.Genre;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository usuarioRegistradoRepository) {
		userRepository = usuarioRegistradoRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Genre> findGenres() throws DataAccessException {
		return userRepository.findGenres();
	}


	@Transactional(readOnly = true)
	public Collection<User> findAll() throws DataAccessException {
		log.info("Se han recogido todos los Users");
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User findUserByUsername(String username) {
		log.info("Se han recogido un User por username");
		return userRepository.findByUsername(username);
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		log.info("Se han guardado un User");
		user.setEnabled(true);
		userRepository.save(user);
	}

	@Transactional
	public void delete(User user) throws DataAccessException {
		log.info("Se han eliminado un User");
		userRepository.delete(user);
	}

}
