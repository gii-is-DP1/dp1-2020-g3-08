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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArbitroService {
	private ArbitroRepository arbitroRepository;

	private PartidoRepository partidoRepostory;

	@Autowired
	public ArbitroService(ArbitroRepository arbitroRepository,PartidoRepository partidoRepostory) {
		this.arbitroRepository = arbitroRepository;
		this.partidoRepostory = partidoRepostory;

	}

	@Transactional(readOnly = true)
	public Collection<Arbitro> findAll() throws DataAccessException {
		log.info("Se han recogido todos los Arbitros");
		return arbitroRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Arbitro findById(int id) throws DataAccessException {
		log.info("Se han recogido un Arbitro por id");
		return arbitroRepository.findById(id);
	}


	@Transactional
	public void saveArbitro(Arbitro arbitro) throws DataAccessException {
		log.info("Se han guardado un Arbitro");
		arbitroRepository.save(arbitro);
	}

	@Transactional
	public void deleteArbitro(final Arbitro arbitro) {
		log.info("Se han eliminado un Arbitro");
		arbitroRepository.delete(arbitro);
	}

	public Collection<Partido> findPartidosByArbitroId(int arbitroId) {
		log.info("Se han recogido partidos por Arbitro id");
		return partidoRepostory.findByArbitroId(arbitroId);
	}

	public Collection<Arbitro> findArbitroByNombre(String nombre) {
		log.info("Se han recogido arbitros por nombre");
		return arbitroRepository.findByNombre(nombre);
	}


}
