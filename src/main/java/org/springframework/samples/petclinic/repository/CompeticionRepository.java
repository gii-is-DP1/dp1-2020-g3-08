package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Competicion;


public interface CompeticionRepository extends Repository<Competicion, Integer>{
	
	void save(Competicion competicion) throws DataAccessException;

	Collection<Competicion> findAll() throws DataAccessException;
	@Query("SELECT competicion FROM Competicion competicion WHERE competicion.id =:id")
	Competicion findById(@Param("id") int id) throws DataAccessException;

}
