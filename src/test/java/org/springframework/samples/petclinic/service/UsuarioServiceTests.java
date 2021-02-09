package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Genre;
import org.springframework.samples.petclinic.model.User;
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
 * UserServiceTests#clinicService clinicService}</code> instance variable, which uses
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
class UsuarioServiceTests {

	@Autowired
	protected UserService userService;


	@Test
	void shouldFindAllGenders() {
		Collection<Genre> generos = this.userService.findGenres();
		Assertions.assertThat(generos.isEmpty()).isFalse();
		Assertions.assertThat(generos.size()).isEqualTo(2);
		
	}
	
	@Test
	void shouldFindAllUsers() {
		Collection<User> usuarios = this.userService.findAll();
		Assertions.assertThat(usuarios.isEmpty()).isFalse();
		Assertions.assertThat(usuarios.size()).isEqualTo(8);
		
	}
	
	@Test
	void shouldFindUserByUsername() {
		User usuario = this.userService.findUserByUsername("admin1");
		Assertions.assertThat(usuario!=null);
		
	}

	@Test
	@Transactional
	public void shouldInsertUser() {
		Collection<User> usuarios = this.userService.findAll();
		int found = usuarios.size();

		
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("Sam");
		user.setEnabled(true);
		user.setFirstName("Sammy");
		user.setLastName("Gammy");
		user.setEmail("saga@correo.com");
		user.setTelephone("954345723");
		user.setBirthDate(LocalDate.now().minusYears(20));

		this.userService.saveUser(user);
		Collection<User> usuarios2 = this.userService.findAll();
		int found2 = usuarios2.size();
		
		Assertions.assertThat(found2).isEqualTo(found + 1);
	}

	@Test
	@Transactional
	void shouldUpdateUser() {
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("Sam");
		user.setEnabled(true);
		user.setFirstName("Sammy");
		user.setLastName("Gammy");
		user.setEmail("saga@correo.com");
		user.setTelephone("954345723");
		user.setBirthDate(LocalDate.now().minusYears(20));

		this.userService.saveUser(user);
		Collection<User> usuarios = this.userService.findAll();
		int found = usuarios.size();
		
		User user2=userService.findUserByUsername("Sam");
		user2.setFirstName("Paco");

		this.userService.saveUser(user2);

		// retrieving new name from database
		Collection<User> usuarios2 = this.userService.findAll();
		int found2 = usuarios2.size();
		Assertions.assertThat(found2).isEqualTo(found);
		User user3=userService.findUserByUsername("Sam");
		Assertions.assertThat(user3.getFirstName()).isEqualTo("Paco");
	}
	
	public void shouldDeleteUser() {

		
		User user = new User();
		user.setUsername("Sam");
		user.setPassword("Sam");
		user.setEnabled(true);
		user.setFirstName("Sammy");
		user.setLastName("Gammy");
		user.setEmail("saga@correo.com");
		user.setTelephone("954345723");
		user.setBirthDate(LocalDate.now().minusYears(20));

		this.userService.saveUser(user);
		Collection<User> usuarios = this.userService.findAll();
		int found = usuarios.size();
		userService.delete(user);
		Collection<User> usuarios2 = this.userService.findAll();
		int found2 = usuarios2.size();
		Assertions.assertThat(found2).isEqualTo(found - 1);
	}

}
