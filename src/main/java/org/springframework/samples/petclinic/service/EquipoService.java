/*
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
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EquipoService {

	private EquipoRepository	equipoRepository;
	private PartidoService		partidoService;


	@Autowired
	public EquipoService(final EquipoRepository equipoRepository, final PartidoService partidoService) {
		this.partidoService = partidoService;
		this.equipoRepository = equipoRepository;
	}

	@Transactional(readOnly = true)
	public Equipo findEquipoById(final int id) throws DataAccessException {
		log.info("Se han recogido un Equipo por id");
		return equipoRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Equipo> findEquipoByNombre(final String nombre) throws DataAccessException {
		log.info("Se han recogido todos los Equipos por nombre");
		return equipoRepository.findByNombre(nombre);
	}

	@Transactional(readOnly = true)
	public Collection<Equipo> findEquipos() throws DataAccessException {
		log.info("Se han recogido todos los Equipos");
		return equipoRepository.findAll();
	}

	@Transactional
	public void saveEquipo(final Equipo equipo) throws DataAccessException {
		log.info("Se han guardado un Equipo");
		equipoRepository.save(equipo);
	}

	@Transactional
	public void deleteEquipo(final Equipo equipo) {
		log.info("Se han eliminado un Equipo");
		Collection<Partido> partidos = partidoService.findAll();
		for (Partido partido : partidos)
			if (partido.getEquipo1().getId() == equipo.getId() || partido.getEquipo2().getId() == equipo.getId())
				partidoService.deletePartido(partido);
		equipoRepository.delete(equipo);
	}



}
