package org.springframework.samples.petclinic.web.validators;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PartidoValidator implements Validator{


	private PartidoService partidoService;

	public PartidoValidator(PartidoService partidoService) {
		this.partidoService=partidoService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Partido.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.info("Se ha iniciado el validador de Partido");
		Collection<Partido> restoDePartidos = partidoService.findAll();

		// Este codigo realiza la regla de negocio 3, donde se comprueba que en un
		// partido no pueda entrar jugadores sancionados
		Partido p = (Partido) target;

		if(p.getLugar().isEmpty()) {
			errors.rejectValue("lugar",
				"no puede estar vacío",
				"no puede estar vacío");
		}

		Set<Jugador> jugadores = new HashSet<Jugador>();
		try {
			jugadores = p.getJugadoresParticipantes();
			if (jugadores == null) {
				jugadores = new HashSet<Jugador>();
			}
		} catch (NullPointerException e) {

		} finally {
			log.info("Se van a validar las Tarjetas Rojas y Amarillas");
			for (Jugador j : jugadores)
				if (j.getTarjetaAmarilla() >= 5) {
					log.warn("Ha saltado la excepción de tarjetas amarillas");
					errors.rejectValue("jugadoresParticipantes",
						"El jugador no puede participar si tiene 5 o mas tarjetas amarillas",
						"El jugador no puede participar si tiene 5 o mas tarjetas amarillas");
				} else if (j.getTarjetaRoja() > 0) {
					log.warn("Ha saltado la excepción de tarjetas rojas");
					errors.rejectValue("jugadoresParticipantes", "El jugador no puede participar si tiene tarjeta roja",
						"El jugador no puede participar si tiene tarjeta roja");
				}
			// Este codigo realiza la regla de negocio 2, Un jugador que esté lesionado no
			// podrá ser inscrito en un
			// partido, hasta que no le hayan dado el alta de su lesión.
			log.info("Se van a validar las lesiones de los jugadores");
			for (Jugador jug : jugadores)
				if (jug.getLesion()) {
					errors.rejectValue("jugadoresParticipantes",
						"Un jugador que esté lesionado no podrá ser inscrito en un partido",
						"Un jugador que esté lesionado no podrá ser inscrito en un partido");
				}

			// Regla de negocio 4
			try {
				log.info("Se van a validar que hay un mismo arbitro arbitrando dos partidos al mismo tiempo");
			for (Partido partido : restoDePartidos)
				if (!p.getId().equals(partido.getId()) && p.getArbitro().equals(p.getArbitro()) && partido.getFecha().equals(p.getFecha())) {
					errors.rejectValue("arbitro", "Un arbitro no puede arbitrar dos partidos en una misma fecha",
						"Un arbitro no puede arbitrar dos partidos en una misma fecha");
				}
			} catch (Exception e2) {
				for (Partido partido : restoDePartidos)
					if (p.getArbitro().equals(p.getArbitro()) && partido.getFecha().equals(p.getFecha())) {
						errors.rejectValue("arbitro", "Un arbitro no puede arbitrar dos partidos en una misma fecha",
							"Un arbitro no puede arbitrar dos partidos en una misma fecha");
					}
			}
			

		}


	}

}
