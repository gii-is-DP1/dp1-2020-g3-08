/*
 * @Query("SELECT jugador FROM jugador jugador left join fetch jugador.pets WHERE jugador.id =:id")
 * public jugador findById(@Param("id") int id);
 * Copyright 2002-2013 the original author or authors.
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
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.repository.JugadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JugadorService {

	private JugadorRepository jugadorRepository;


	@Autowired
	public JugadorService(final JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}


	public void saveJugador(final Jugador jugador) throws DataAccessException {
		this.jugadorRepository.save(jugador);

	}

	@Transactional(readOnly = true)
	public Jugador findJugadorById(final int id) throws DataAccessException {
		log.info("Se han recogido un Jugador por id");
		return jugadorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Jugador> findJugadorByNombre(final String nombre) throws DataAccessException {
		log.info("Se han recogido todos los Jugadores por nombre");
		return jugadorRepository.findJugadorByNombre(nombre);
	}

	@Transactional(readOnly = true)
	public Collection<Jugador> findJugadores() throws DataAccessException {
		log.info("Se han recogido todos los Jugadores");
		return jugadorRepository.findAll();
	}

	@Transactional
	public void deleteJugador(final Jugador jugador) {
		log.info("Se han eliminado un Jugador");
		jugadorRepository.delete(jugador);
	}



}
