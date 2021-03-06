/*
 * Copyright 2002-2013 the original author or authors.
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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Authorities;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.AuthoritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthoritiesService {

	private AuthoritiesRepository authoritiesRepository;
	private UserService userService;

	@Autowired
	public AuthoritiesService(AuthoritiesRepository authoritiesRepository,UserService userService) {
		this.authoritiesRepository = authoritiesRepository;
		this.userService = userService;
	}

	@Transactional
	public Collection<Authorities> findAllAuthorities() throws DataAccessException {
		log.info("Se han recogido todas las Authorities");
		List<Authorities> result = new ArrayList<>();
		authoritiesRepository.findAll().forEach(result::add);
		System.out.println(result.toString());
		return  result;
	}


	@Transactional
	public void saveAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.save(authorities);
	}


	@Transactional
	public void saveAuthorities(String username, String role) throws DataAccessException {
		Authorities authorities = new Authorities();
		User user = userService.findUserByUsername(username);
		if(user!=null) {
			authorities.setUser(user);
			authorities.setAuthority(role);
			//user.get().getAuthorities().add(authority);
			authoritiesRepository.save(authorities);
		} else
			throw new DataAccessException("User '"+username+"' not found!") {};
	}

	@Transactional
	public void deleteAuthorities(Authorities authorities) throws DataAccessException {
		authoritiesRepository.delete(authorities);
	}


}
