/*
 * // * Copyright 2002-2013 the original author or authors.
 * // *
 * // * Licensed under the Apache License, Version 2.0 (the "License");
 * // * you may not use this file except in compliance with the License.
 * // * You may obtain a copy of the License at
 * // *
 * // * http://www.apache.org/licenses/LICENSE-2.0
 * // *
 * // * Unless required by applicable law or agreed to in writing, software
 * // * distributed under the License is distributed on an "AS IS" BASIS,
 * // * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * // * See the License for the specific language governing permissions and
 * // * limitations under the License.
 * //
 */
//
//package org.springframework.samples.petclinic.service;
//
//import java.util.Collection;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.samples.petclinic.model.Equipo;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Integration test of the Service and the Repository layer.
// * <p>
// * ClinicServiceSpringDataJpaTests subclasses benefit from the following services provided
// * by the Spring TestContext Framework:
// * </p>
// * <ul>
// * <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
// * time between test execution.</li>
// * <li><strong>Dependency Injection</strong> of test fixture instances, meaning that we
// * don't need to perform application context lookups. See the use of
// * {@link Autowired @Autowired} on the <code>{@link
// * EquipoServiceTests#clinicService clinicService}</code> instance variable, which uses
// * autowiring <em>by type</em>.
// * <li><strong>Transaction management</strong>, meaning each test method is executed in
// * its own transaction, which is automatically rolled back by default. Thus, even if tests
// * insert or otherwise change database state, there is no need for a teardown or cleanup
// * script.
// * <li>An {@link org.springframework.context.ApplicationContext ApplicationContext} is
// * also inherited and can be used for explicit bean lookup if necessary.</li>
// * </ul>
// *
// * @author Ken Krebs
// * @author Rod Johnson
// * @author Juergen Hoeller
// * @author Sam Brannen
// * @author Michael Isvy
// * @author Dave Syer
// */
//
//@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
//class EquipoServiceTests {
//
//	@Autowired
//	protected EquipoService EquipoService;
//
//
//	@Test
//	void shouldFindEquiposByLastName() {
//		Collection<Equipo> equipos = this.EquipoService.findEquipoByNombre("Betis");
//		Assertions.assertThat(equipos.size()).isEqualTo(1);
//
//		equipos = this.EquipoService.findEquipoByNombre("Sevilla");
//		Assertions.assertThat(equipos.isEmpty()).isTrue();
//	}
//
//	@Test
//	@Transactional
//	public void shouldInsertEquipo() {
//		Collection<Equipo> Equipos = this.EquipoService.findEquipoByNombre("Schultz");
//		int found = Equipos.size();
//
//		Equipo Equipo = new Equipo();
//		Equipo.setNombre("Villareal");
//		Equipo.setLugar("Campo");
//		User user = new User();
//		user.setUsername("Sam");
//		user.setPassword("Sam");
//		user.setEnabled(true);
//		Equipo.setUser(user);
//
//		this.EquipoService.saveEquipo(Equipo);
//		Assertions.assertThat(Equipo.getId().longValue()).isNotEqualTo(0);
//
//		Equipos = this.EquipoService.findEquipoByNombre("Villareal");
//		Assertions.assertThat(Equipos.size()).isEqualTo(found + 1);
//	}
//
//	@Test
//	@Transactional
//	void shouldUpdateEquipo() {
//		Equipo Equipo = this.EquipoService.findEquipoById(1);
//		String oldNombre = Equipo.getNombre();
//		String newNombre = oldNombre + "X";
//
//		Equipo.setNombre(newNombre);
//		this.EquipoService.saveEquipo(Equipo);
//
//		// retrieving new name from database
//		Equipo = this.EquipoService.findEquipoById(1);
//		Assertions.assertThat(Equipo.getNombre()).isEqualTo(newNombre);
//	}
//
//}
