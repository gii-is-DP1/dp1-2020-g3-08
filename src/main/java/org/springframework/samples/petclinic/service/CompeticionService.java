package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Competicion;
import org.springframework.samples.petclinic.repository.CompeticionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CompeticionService {
	private CompeticionRepository competicionRepository;

	@Autowired
	public CompeticionService(CompeticionRepository competicionRepository) {
		this.competicionRepository = competicionRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Competicion> findAll() throws DataAccessException {
		return competicionRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Competicion findById(int id) throws DataAccessException {
		return competicionRepository.findById(id);
	}
	@Transactional(readOnly = true)	
	public Collection<Competicion> findCompeticiones() throws DataAccessException {
		return competicionRepository.findAll();
	}

	@Transactional
	public void saveCompeticion(Competicion competicion) throws DataAccessException {

		competicionRepository.save(competicion);

	}

}
