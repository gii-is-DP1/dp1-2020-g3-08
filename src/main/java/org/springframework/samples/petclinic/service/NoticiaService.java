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
import org.springframework.samples.petclinic.model.Noticia;
import org.springframework.samples.petclinic.repository.NoticiaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticiaService {

	private NoticiaRepository noticiaRepository;

	@Autowired
	public NoticiaService(NoticiaRepository noticiaRepository) {
		this.noticiaRepository = noticiaRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Noticia> findAll() throws DataAccessException {
		log.info("Se han recogido todas las Noticias");
		return noticiaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Noticia findById(int id) throws DataAccessException {
		log.info("Se han recogido una Noticia por id");
		return noticiaRepository.findById(id);
	}

	@Transactional
	public void saveNoticia(Noticia noticia) throws DataAccessException {
		log.info("Se han guardado una Noticia");
		noticiaRepository.save(noticia);
	}
	@Transactional
	public void deleteNoticia(Noticia noticia) throws DataAccessException {
		log.info("Se han eliminado una Noticia");
		noticiaRepository.delete(noticia);
	}


}
