package org.springframework.samples.petclinic.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.repository.ArbitroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArbitroService {
	private ArbitroRepository arbitroRepository;

	@Autowired
	public ArbitroService(ArbitroRepository arbitroRepository) {
		this.arbitroRepository = arbitroRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Arbitro> findAll() throws DataAccessException {
		return arbitroRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Arbitro findById(int id) throws DataAccessException {
		return arbitroRepository.findById(id);
	}
	@Transactional(readOnly = true)	
	public Collection<Arbitro> findArbitros() throws DataAccessException {
		return arbitroRepository.findAll();
	}


	@Transactional
	public void saveArbitro(Arbitro arbitro) throws DataAccessException {

		arbitroRepository.save(arbitro);

	}

}
