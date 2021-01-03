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
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.PartidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidoService {
	private PartidoRepository partidoRepository;

	@Autowired
	public PartidoService(PartidoRepository partidoRepository) {
		this.partidoRepository = partidoRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Partido> findAll() throws DataAccessException {
		return partidoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Partido findById(int id) throws DataAccessException {
		return partidoRepository.findById(id);
	}

	@Transactional
	public void savePartido(Partido partido) throws DataAccessException {
		partidoRepository.save(partido);
	}
}
