package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Arbitro;


public interface ArbitroRepository extends Repository<Arbitro, Integer> {

	void save(Arbitro arbitro) throws DataAccessException;

	Collection<Arbitro> findAll() throws DataAccessException;
	
	@Query("SELECT arbitro FROM Arbitro arbitro WHERE arbitro.id =:id")
	Arbitro findById(@Param("id") int id) throws DataAccessException;
}
