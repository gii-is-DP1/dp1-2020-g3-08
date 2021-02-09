package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Entrenador;

import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class EntrenadorServiceTests {

	@Autowired
	protected EntrenadorService entrenadorService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected EquipoService equipoService;

	@Test
	void shouldFindAllEntrenadores() {
		Collection<Entrenador> entrenadores = this.entrenadorService.findAll();
		Assertions.assertThat(entrenadores.size()).isEqualTo(4);

	}

	@Test
	void shouldFindEntrenadorById() {
		Entrenador entrenador = this.entrenadorService.findById(1);
		Assertions.assertThat(entrenador != null);

	}

	@Test
	@Transactional
	public void shouldInsertEntrenador() throws DataAccessException, DuplicatedException {
		User user1 = userService.findUserByUsername("admin1");
		Entrenador entrenador = new Entrenador();
		entrenador.setUser(user1);
		Collection<Entrenador> entrenadores = this.entrenadorService.findAll();
		int found = entrenadores.size();

		this.entrenadorService.saveEntrenador(entrenador);
		Collection<Entrenador> entrenadores2 = this.entrenadorService.findAll();
		int found2 = entrenadores2.size();
		Assertions.assertThat(found2).isEqualTo(found + 1);

	}

	@Test
	@Transactional
	void shouldUpdateEntrenador() throws DataAccessException, DuplicatedException {
		Entrenador entrenador = new Entrenador();
		User user1 = userService.findUserByUsername("admin1");
		entrenador.setUser(user1);

		this.entrenadorService.saveEntrenador(entrenador);

		Collection<Entrenador> entrenadores = this.entrenadorService.findAll();
		int found = entrenadores.size();

		Entrenador miEntrenador = entrenadorService.findById(1);
		miEntrenador.getUser().setTelephone("666666666");
		entrenadorService.saveEntrenador(miEntrenador);
		Entrenador miEntrenadorCambiado = entrenadorService.findById(1);

		// retrieving new name from database
		Collection<Entrenador> entrenadores2 = this.entrenadorService.findAll();
		int found2 = entrenadores2.size();
		Assertions.assertThat(found2).isEqualTo(found);
		Assertions.assertThat(
				!(miEntrenadorCambiado.getUser().getTelephone().equals(miEntrenador.getUser().getTelephone())));
	}

}
