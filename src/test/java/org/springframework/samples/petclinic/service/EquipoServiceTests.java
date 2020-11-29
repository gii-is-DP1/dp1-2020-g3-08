package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Equipo;
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
class EquipoServiceTests {

	@Autowired
	protected EquipoService equipoService;


	@Test
	void shouldFindEquiposByNombre() {
		Collection<Equipo> equipos = this.equipoService.findEquipoByNombre("Betis");
		Assertions.assertThat(equipos.size()).isEqualTo(1);

		equipos = this.equipoService.findEquipoByNombre("Osasuna");
		Assertions.assertThat(equipos.isEmpty()).isTrue();
	}

	@Test
	@Transactional
	public void shouldInsertEquipo() {
		Collection<Equipo> Equipos = this.equipoService.findEquipoByNombre("Schultz");
		int found = Equipos.size();

		Equipo Equipo = new Equipo();
		Equipo.setNombre("Villareal");
		Equipo.setLugar("Campo de la ceramica");
		
		this.equipoService.saveEquipo(Equipo);
		Assertions.assertThat(Equipo.getId().longValue()).isNotEqualTo(0);

		Equipos = this.equipoService.findEquipoByNombre("Villareal");
		Assertions.assertThat(Equipos.size()).isEqualTo(found + 1);
	}
		
	@Test
	void shouldFindEquipoById() {
		Equipo equipo = this.equipoService.findEquipoById(1);
		Assertions.assertThat(equipo!=null);
	}

	@Test
	@Transactional
	void shouldUpdateEquipo() {
		Equipo Equipo = this.equipoService.findEquipoById(1);
		String oldNombre = Equipo.getNombre();
		String newNombre = oldNombre + "X";

		Equipo.setNombre(newNombre);
		this.equipoService.saveEquipo(Equipo);

		// retrieving new name from database
		Equipo = this.equipoService.findEquipoById(1);
		Assertions.assertThat(Equipo.getNombre()).isEqualTo(newNombre);
	}
	
	@Test
	public void shouldDeleteEquipo() throws Exception {
		Equipo equipo = this.equipoService.findEquipoById(1);
		this.equipoService.deleteEquipo(equipo);
		Equipo equipoNew = this.equipoService.findEquipoById(1);
		Assertions.assertThat(equipoNew).isEqualTo(null);
	}

	
	

}
