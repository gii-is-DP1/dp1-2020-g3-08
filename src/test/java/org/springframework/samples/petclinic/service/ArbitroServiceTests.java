package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Arbitro;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ArbitroServiceTests {
	@Autowired
	protected ArbitroService arbitroService;
	@Autowired
	protected PartidoService partidoService;

	@Test
	void shouldFindAllArbitros() {
		Collection<Arbitro> arbitros = this.arbitroService.findAll();
		Assertions.assertThat(arbitros.isEmpty()).isFalse();
		Assertions.assertThat(arbitros.size()).isEqualTo(1);
	}

	@Test
	void shouldFindArbitroById() {
		Arbitro arbitro = this.arbitroService.findById(1);
		Assertions.assertThat(arbitro != null);

	}

	@Test
	@Transactional
	public void shouldInsertArbitro() {
		
		Arbitro arbitro = new Arbitro();
		Collection<Arbitro> arbitros = this.arbitroService.findAll();
		int found = arbitros.size();
		arbitro.setNombre("Alex");
		arbitro.setApellidos("Barroso");
		arbitro.setDni("28846292Q");
		arbitro.setFechaNacimiento(LocalDate.now().minusWeeks(12));
		arbitro.setNacionalidad("Brasil");

		this.arbitroService.saveArbitro(arbitro);
		Collection<Arbitro> arbitros2 = this.arbitroService.findAll();
		int found2 = arbitros2.size();
		Assertions.assertThat(found2).isEqualTo(found + 1);

	}

	@Test
	@Transactional
	void shouldUpdateArbitro() {
		Arbitro arbitro = new Arbitro();
		arbitro.setNombre("Alex");
		arbitro.setApellidos("Barroso");
		arbitro.setDni("28846292Q");
		arbitro.setFechaNacimiento(LocalDate.now().minusWeeks(12));
		arbitro.setNacionalidad("Brasil");

		this.arbitroService.saveArbitro(arbitro);
		Collection<Arbitro> arbitros = this.arbitroService.findAll();
		int found = arbitros.size();

		Arbitro arbitro2 = arbitroService.findById(2);
		arbitro2.setNombre("Perico");

		this.arbitroService.saveArbitro(arbitro2);

		// retrieving new name from database
		Collection<Arbitro> arbitros2 = this.arbitroService.findAll();
		int found2 = arbitros2.size();
		Assertions.assertThat(found2).isEqualTo(found);
		Arbitro arbitro3 = arbitroService.findById(2);
		Assertions.assertThat(arbitro3.getNombre()).isEqualTo("Perico");

	}

}
