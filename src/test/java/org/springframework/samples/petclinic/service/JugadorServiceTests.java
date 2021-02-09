
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.service.exceptions.DuplicatedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * ClinicServiceSpringDataJpaTests subclasses benefit from the following services provided
 * by the Spring TestContext Framework:
 * </p>
 * <ul>
 * <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li>
 * <li><strong>Dependency Injection</strong> of test fixture instances, meaning that we
 * don't need to perform application context lookups. See the use of
 * {@link Autowired @Autowired} on the <code>{@link
 * ClinicServiceTests#clinicService clinicService}</code> instance variable, which uses
 * autowiring <em>by type</em>.
 * <li><strong>Transaction management</strong>, meaning each test method is executed in
 * its own transaction, which is automatically rolled back by default. Thus, even if tests
 * insert or otherwise change database state, there is no need for a teardown or cleanup
 * script.
 * <li>An {@link org.springframework.context.ApplicationContext ApplicationContext} is
 * also inherited and can be used for explicit bean lookup if necessary.</li>
 * </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Dave Syer
 */

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class JugadorServiceTests {

	@Autowired
	protected JugadorService	jugadorService;

	@Autowired
	protected EquipoService		equipoService;


	@Test
	void shouldFindJugadorWithCorrectId() {
		Jugador jugador1 = this.jugadorService.findJugadorById(1);
		assertThat(jugador1.getNombre()).startsWith("Daniel");
		assertThat(jugador1.getEquipo().getNombre()).isEqualTo("Betis");

	}

	@Test
	@Transactional
	public void shouldInsertJugadorIntoDatabaseAndGenerateId() {
		Equipo equipo1 = this.equipoService.findEquipoById(1);
		int found = equipo1.getJugadores().size();

		Jugador jugador = new Jugador();
		jugador.setNombre("Paco");
		jugador.setApellidos("Perez");
		jugador.setDni("29517543X");
		jugador.setFechaNacimiento(LocalDate.now().minusYears(12));
		jugador.setNacionalidad("España");
		jugador.setLesion(false);
		jugador.setTarjetaAmarilla(0);
		jugador.setTarjetaRoja(0);
		equipo1.addJugador(jugador);
		assertThat(equipo1.getJugadores().size()).isEqualTo(found + 1);

		this.equipoService.saveEquipo(equipo1);

		equipo1 = this.equipoService.findEquipoById(1);
		assertThat(equipo1.getJugadores().size()).isEqualTo(found + 1);
		// checks that id has been generated
		assertThat(jugadorService.findJugadorByNombre("Paco").iterator().next().getId()).isNotNull();
	}

	

	@Test
	@Transactional
	public void shouldUpdateJugador() throws Exception {
		Jugador jugador1 = this.jugadorService.findJugadorById(1);
		String oldName = jugador1.getNombre();
		String oldLastName = jugador1.getApellidos();
		String oldNacionalidad = jugador1.getNacionalidad();

		String newName = oldName + "X";
		String newLastName = oldLastName + "XX";
		LocalDate newFechaNacimiento = LocalDate.now().minusYears(12);
		String newDNI = "29557543X";
		String newNacionalidad = oldNacionalidad + "Argelia";
		jugador1.setNombre(newName);
		jugador1.setApellidos(newLastName);
		jugador1.setDni(newDNI);
		jugador1.setNacionalidad(newNacionalidad);
		jugador1.setFechaNacimiento(newFechaNacimiento);
		this.jugadorService.saveJugador(jugador1);

		jugador1 = this.jugadorService.findJugadorById(1);
		assertThat(jugador1.getNombre()).isEqualTo(newName);
	}

	

	@Test
	@Transactional
	void shouldFindJugadoresByNombre() {
		Collection<Jugador> jugadores = this.jugadorService.findJugadorByNombre("Daniel");
		assertThat(jugadores.size()).isEqualTo(1);

		jugadores = this.jugadorService.findJugadorByNombre("Pepe");
		assertThat(jugadores.isEmpty()).isTrue();
	}

	@Test
	@Transactional
	public void shouldDeleteJugador() throws Exception {
		Jugador jugador = this.jugadorService.findJugadorById(1);
		this.jugadorService.deleteJugador(jugador);
		Jugador jugadorNew = this.jugadorService.findJugadorById(1);
		assertThat(jugadorNew).isEqualTo(null);
	}

}
