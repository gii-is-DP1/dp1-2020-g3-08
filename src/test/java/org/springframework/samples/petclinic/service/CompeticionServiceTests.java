package org.springframework.samples.petclinic.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Competicion;
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
class CompeticionServiceTests {

	@Autowired
	protected CompeticionService competicionService;


	@Test
	@Transactional
	public void shouldInsertEquipo() {
		Collection<Competicion> Competiciones = this.competicionService.findAll();
		int found = Competiciones.size();

		Competicion Competicion = new Competicion();
		Competicion.setNombreComp("Premier");
		
		this.competicionService.saveCompeticion(Competicion);
		Assertions.assertThat(Competicion.getId().longValue()).isNotEqualTo(0);

		Competiciones = this.competicionService.findAll();
		Assertions.assertThat(Competiciones.size()).isEqualTo(found + 1);
	}
		
	@Test
	void shouldFindCompeticionById() {
		Competicion competicion = this.competicionService.findCompeticionById(1);
		Assertions.assertThat(competicion!=null);
	}

	@Test
	@Transactional
	void shouldUpdateEquipo() {
		Competicion competicion = this.competicionService.findCompeticionById(1);
		String oldNombre = competicion.getNombreComp();
		String newNombre = oldNombre + "X";

		competicion.setNombreComp(newNombre);
		this.competicionService.saveCompeticion(competicion);

		// retrieving new name from database
		competicion = this.competicionService.findCompeticionById(1);
		Assertions.assertThat(competicion.getNombreComp()).isEqualTo(newNombre);
	}
	
	@Test
	public void shouldDeleteCompeticion() throws Exception {
		Competicion competicion = this.competicionService.findCompeticionById(3);
		this.competicionService.deleteCompeticion(competicion);
		Competicion competicionNueva = this.competicionService.findCompeticionById(3);
		Assertions.assertThat(competicionNueva).isEqualTo(null);
	}

	
	

}
