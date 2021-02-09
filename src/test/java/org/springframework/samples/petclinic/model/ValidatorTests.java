package org.springframework.samples.petclinic.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.samples.petclinic.service.CompeticionService;
import org.springframework.samples.petclinic.service.EquipoService;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ValidatorTests {

	@Autowired
	protected EquipoService		equipoService;

	@Autowired
	protected PartidoService	partidoService;

	@Autowired
	protected CompeticionService	competicionService;

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenFirstLugarEmpty() {

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Partido partido = new Partido();
		partido.setEquipo1(equipoService.findEquipoById(1));
		partido.setEquipo2(equipoService.findEquipoById(2));
		partido.setFecha(LocalDate.now());
		partido.setLugar("");
		partido.setCompeticion(competicionService.findCompeticionById(1));

		Validator validator = createValidator();
		Set<ConstraintViolation<Partido>> constraintViolations = validator.validate(partido);

		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Partido> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("lugar");
		assertThat(violation.getMessage()).isEqualTo("must not be empty");
	}
}