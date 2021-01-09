
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Noticia;
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
 * NoticiaServiceTests#clinicService clinicService}</code> instance variable, which uses
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
class NoticiaServiceTests {

	@Autowired
	protected NoticiaService noticiaService;


	@Test
	void shouldFindAllNoticias() {
		Collection<Noticia> noticias = this.noticiaService.findAll();
		Assertions.assertThat(noticias.isEmpty()).isFalse();
		Assertions.assertThat(noticias.size()).isEqualTo(1);

	}

	@Test
	void shouldFindNoticiaById() {
		Noticia noticia = this.noticiaService.findById(1);
		Assertions.assertThat(noticia != null);

	}

	@Test
	@Transactional
	public void shouldInsertNoticias() {
		Collection<Noticia> noticias = this.noticiaService.findAll();
		int found = noticias.size();

		Noticia not = new Noticia();
		not.setFecha(LocalDate.now());
		not.setTexto("Hola soy un ejemplo");
		not.setTitulo("Primicia: el ejemplo");

		this.noticiaService.saveNoticia(not);
		;
		Collection<Noticia> noticias2 = this.noticiaService.findAll();
		int found2 = noticias2.size();

		Assertions.assertThat(found2).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdateNoticias() {
		Noticia not = new Noticia();
		not.setFecha(LocalDate.now());
		not.setTexto("Hola soy un ejemplo");
		not.setTitulo("Primicia: el ejemplo");

		this.noticiaService.saveNoticia(not);
		Collection<Noticia> usuarios = this.noticiaService.findAll();
		int found = usuarios.size();

		Noticia not2 = this.noticiaService.findById(2);
		not2.setTitulo("Ya no es primicia");

		this.noticiaService.saveNoticia(not2);

		// retrieving new name from database
		Collection<Noticia> noticias2 = this.noticiaService.findAll();
		int found2 = noticias2.size();
		Assertions.assertThat(found2).isEqualTo(found);
		Noticia not3 = this.noticiaService.findById(2);
		Assertions.assertThat(not3.getTitulo()).isEqualTo("Ya no es primicia");
	}

}
