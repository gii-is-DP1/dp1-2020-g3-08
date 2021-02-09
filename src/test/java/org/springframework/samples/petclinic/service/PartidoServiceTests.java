
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Equipo;
import org.springframework.samples.petclinic.model.Partido;
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
 * equipoServiceTests#clinicService clinicService}</code> instance variable, which uses
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
class PartidoServiceTests {

	@Autowired
	protected EquipoService		equipoService;

	@Autowired
	protected PartidoService	partidoService;

	@Autowired
	protected CompeticionService	competicionService;



	@Test
	void shouldFindAllPartidos() {
		Collection<Partido> partidos = partidoService.findAll();
		Assertions.assertThat(partidos.size()).isEqualTo(7);
	}

	@Test
	public void shouldFindPartidoById() {
		Partido partido = partidoService.findById(1);

		Assertions.assertThat(partido).isNotNull();
	}

	@Test
	void shouldFindEquipoById() {
		Equipo equipo = equipoService.findEquipoById(1);
		Assertions.assertThat(equipo != null);
	}

	@Test
	@Transactional
	void shouldInsertPartido() {
		Partido partido = new Partido();
		partido.setEquipo1(equipoService.findEquipoById(1));
		partido.setEquipo2(equipoService.findEquipoById(2));
		partido.setFecha(LocalDate.now());
		partido.setLugar("Aqui");
		partido.setCompeticion(competicionService.findCompeticionById(1));

		int cantidadPartidos = partidoService.findAll().size();

		partidoService.savePartido(partido);

		Assertions.assertThat(partidoService.findAll().size()==cantidadPartidos+1);
	}

	@Test
	@Transactional
	void shouldUpdatePartido() {
		Partido partido = partidoService.findById(1);

		partido.setLugar("LUGAR ALEATORIO");

		int cantidadPartidos = partidoService.findAll().size();

		partidoService.savePartido(partido);

		Assertions.assertThat(partidoService.findAll().size()==cantidadPartidos);
	}

	@Test
	@Transactional
	public void shouldDeletePartido() throws Exception {
		Partido partido = partidoService.findById(1);

		int cantidadPartidos = partidoService.findAll().size();

		partidoService.deletePartido(partido);

		Assertions.assertThat(partidoService.findAll().size()==cantidadPartidos-1);
	}

}
