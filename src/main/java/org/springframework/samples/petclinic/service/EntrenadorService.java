package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.repository.EntrenadorRepository;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class EntrenadorService {
	private EntrenadorRepository entrenadorRepository;

	private UserService userService;

	@Autowired
	public EntrenadorService(EntrenadorRepository entrenadorRepository, UserService userService) {
		this.entrenadorRepository = entrenadorRepository;
		this.userService = userService;
	}


		
	@Transactional(rollbackFor = DuplicatedException.class)
	public void saveEntrenador(Entrenador entrenador) throws DataAccessException,  DuplicatedException {
		Set<Entrenador> entrenadores= (Set<Entrenador>) entrenadorRepository.findAll();
		Entrenador otherEntrenador = entrenador.getEntrenadorwithIdDifferent(entrenadores,entrenador.getUser().getTelephone(),entrenador.getId());
		if (StringUtils.hasLength(entrenador.getUser().getTelephone()) && otherEntrenador != null && otherEntrenador.getId() != entrenador.getId()) {
			throw new DuplicatedException();	
		} else {
			this.entrenadorRepository.save(entrenador);
		}
	}
	
//	@Transactional     
//	public void saveEntrenador(Entrenador entrenador) throws DataAccessException {        
//		entrenadorRepository.save(entrenador);      
//		}
	

	@Transactional(readOnly = true)
	public Entrenador findById(int id) throws DataAccessException {
		return entrenadorRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Entrenador> findAll() throws DataAccessException {
		return entrenadorRepository.findAll();
	}

	@Transactional
	public void deleteEntrenador(final Entrenador entrenador) {
		this.entrenadorRepository.delete(entrenador);
	}

}

