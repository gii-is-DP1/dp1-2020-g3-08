
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Jugador;

public interface JugadorRepository extends Repository<Jugador, String> {

	void save(final Jugador jugador) throws DataAccessException;
	@Query("SELECT jugador FROM Jugador jugador WHERE jugador.id =:id")
	Jugador findById(@Param("id") int id) throws DataAccessException;

	@Query("SELECT DISTINCT jugador FROM Jugador jugador WHERE jugador.nombre LIKE :nombre%")
	Collection<Jugador> findJugadorByNombre(@Param("nombre") String nombre);

	Collection<Jugador> findAll() throws DataAccessException;

	void delete(Jugador jugador);

}
