package org.springframework.samples.petclinic.web.validators;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.omg.CosNaming.NamingContextExtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Jugador;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.repository.PartidoRepository;
import org.springframework.samples.petclinic.service.PartidoService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


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
		Collection<Partido> todosLosPartidos = partidoService.findAll();

		// Este codigo realiza la regla de negocio 3, donde se comprueba que en un
		// partido no pueda entrar jugadores sancionados
		Partido p = (Partido) target;
		Set<Jugador> jugadores = new HashSet<Jugador>();
		try {
			jugadores = p.getJugadoresParticipantes();
			if (jugadores == null) {
				jugadores = new HashSet<Jugador>();
			}
		} catch (NullPointerException e) {

		} finally {

			for (Jugador j : jugadores) {
				if (j.getTarjetaAmarilla() >= 5) {
					errors.rejectValue("jugadoresParticipantes",
							"El jugador no puede participar si tiene 5 o mas tarjetas amarillas",
							"El jugador no puede participar si tiene 5 o mas tarjetas amarillas");
				} else if (j.getTarjetaRoja() > 0) {
					errors.rejectValue("jugadoresParticipantes", "El jugador no puede participar si tiene tarjeta roja",
							"El jugador no puede participar si tiene tarjeta roja");
				}

			}
			// Este codigo realiza la regla de negocio 2, Un jugador que esté lesionado no
			// podrá ser inscrito en un
			// partido, hasta que no le hayan dado el alta de su lesión.

			for (Jugador jug : jugadores) {
				if (jug.getLesion()) {
					errors.rejectValue("jugadoresParticipantes",
							"Un jugador que esté lesionado no podrá ser inscrito en un partido",
							"Un jugador que esté lesionado no podrá ser inscrito en un partido");
				}
			}
		
			// Regla de negocio 4

			for (Partido partido : todosLosPartidos) {
				if (partido.getArbitro().equals(p.getArbitro()) && partido.getFecha().equals(p.getFecha())) {
					errors.rejectValue("arbitro", "Un arbitro no puede arbitrar dos partidos en una misma fecha",
							"Un arbitro no puede arbitrar dos partidos en una misma fecha");

				}
			}

		}
	}

}
