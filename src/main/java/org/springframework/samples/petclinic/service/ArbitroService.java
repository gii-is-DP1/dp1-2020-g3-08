package org.springframework.samples.petclinic.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Arbitro;

import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.ArbitroRepository;
import org.springframework.samples.petclinic.repository.PartidoRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArbitroService {
	private ArbitroRepository arbitroRepository;

	private PartidoRepository partidoRepostory;

	@Autowired
	public ArbitroService(ArbitroRepository arbitroRepository,PartidoRepository partidoRepostory) {
		this.arbitroRepository = arbitroRepository;
		this.partidoRepostory=partidoRepostory;

	}

	@Transactional(readOnly = true)
	public Collection<Arbitro> findAll() throws DataAccessException {
		return arbitroRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Arbitro findById(int id) throws DataAccessException {
		return arbitroRepository.findById(id);
	}

	
	@Transactional
	public void savePartido(Partido partido) throws DataAccessException {
		partidoRepostory.save(partido);

	}


	@Transactional
	public void saveArbitro(Arbitro arbitro) throws DataAccessException {

		arbitroRepository.save(arbitro);

	}

	@Transactional
	public void deleteArbitro(final Arbitro arbitro) {
		this.arbitroRepository.delete(arbitro);
	}
	
	public Collection<Partido> findPartidosByArbitroId(int arbitroId) {
		return partidoRepostory.findByArbitroId(arbitroId);
	}

	public Collection<Arbitro> findArbitroByNombre(String nombre) {
		
		return arbitroRepository.findByNombre(nombre);
	}


}
