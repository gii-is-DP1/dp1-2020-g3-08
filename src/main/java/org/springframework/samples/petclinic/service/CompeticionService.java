package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.repository.CompeticionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CompeticionService {
	private CompeticionRepository competicionRepository;

	@Autowired
	public CompeticionService(CompeticionRepository competicionRepository) {
		this.competicionRepository = competicionRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Competicion> findAll() throws DataAccessException {
		log.info("Se han recogido todas las Competiciones");
		return competicionRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Competicion findCompeticionById(int id) throws DataAccessException {
		log.info("Se han recogido una Competición por id");
		return competicionRepository.findById(id);
	}


	@Transactional
	public void saveCompeticion(Competicion competicion) throws DataAccessException {
		log.info("Se han guardado una Competición");
		competicionRepository.save(competicion);

	}

	public void deleteCompeticion(final Competicion competicion) {
		log.info("Se han eliminado una Competición");
		competicionRepository.delete(competicion);

	}

}
