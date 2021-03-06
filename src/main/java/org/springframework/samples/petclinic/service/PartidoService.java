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

package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.PartidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PartidoService {

	private PartidoRepository partidoRepository;


	@Autowired
	public PartidoService(final PartidoRepository partidoRepository) {
		this.partidoRepository = partidoRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Partido> findAll() throws DataAccessException {
		log.info("Se han recogido todos los Partidos");
		return partidoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Partido findById(final int id) throws DataAccessException {
		log.info("Se han recogido un Partido por id");
		return partidoRepository.findById(id);
	}

	@Transactional
	public void savePartido(final Partido partido) throws DataAccessException {
		log.info("Se han guardado un Partido");
		partidoRepository.save(partido);
	}

	@Transactional
	public void deletePartido(final Partido partido) {
		log.info("Se han eliminado un Partido");
		partidoRepository.delete(partido);

	}

}
