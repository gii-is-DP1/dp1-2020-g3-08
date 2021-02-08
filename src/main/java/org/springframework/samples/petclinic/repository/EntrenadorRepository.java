package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Entrenador;
import org.springframework.samples.petclinic.model.Jugador;

public interface EntrenadorRepository extends Repository<Entrenador, Integer> {

	void save(Entrenador entrenador) throws DataAccessException;

	Collection<Entrenador> findAll() throws DataAccessException;
	
	@Query("SELECT entrenador FROM Entrenador entrenador WHERE entrenador.id =:id")
	Entrenador findById(@Param("id") int id) throws DataAccessException;
	
	void delete(Entrenador entrenador);

}
