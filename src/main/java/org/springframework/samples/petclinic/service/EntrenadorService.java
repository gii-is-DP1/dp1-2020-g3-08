package org.springframework.samples.petclinic.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntrenadorService {
	private EntrenadorRepository entrenadorRepository;

	@Autowired
	public EntrenadorService(EntrenadorRepository entrenadorRepository) {
		this.entrenadorRepository = entrenadorRepository;
	}


	@Transactional(readOnly = true)
	public Entrenador findById(int id) throws DataAccessException {
		return entrenadorRepository.findById(id);
	}
	@Transactional(readOnly = true)	
	public Collection<Entrenador> findAll() throws DataAccessException {
		return entrenadorRepository.findAll();
	}


	@Transactional
	public void saveEntrenador(Entrenador entrenador) throws DataAccessException {

		entrenadorRepository.save(entrenador);

	}
	@Transactional
	public void deleteEntrenador(final Entrenador entrenador) {
		this.entrenadorRepository.delete(entrenador);
	}

}
