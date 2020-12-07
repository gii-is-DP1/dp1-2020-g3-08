
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Jugador;

public interface JugadorRepository extends Repository<Jugador, String> {

	void save(final Jugador jugador) throws DataAccessException;

	Jugador findById(int id) throws DataAccessException;

	Collection<Jugador> findAll() throws DataAccessException;

	void delete(Jugador jugador);

	Collection<Jugador> findJugadorByNombre(String nombre);

}
