package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Partido;
import org.springframework.samples.petclinic.service.PartidoService;

public class PartidoFormatter implements Formatter<Partido> {
	private final PartidoService partidoService;

	@Autowired
	public PartidoFormatter(final PartidoService partidoService) {
		this.partidoService = partidoService;
	}

	@Override
	public String print(final Partido partido, final Locale locale) {
		return partido.getLugar() + " " + partido.getFecha().toString();
	}

	@Override
	public Partido parse(final String text, final Locale locale) throws ParseException {
		Collection<Partido> findPartidos = partidoService.findAll();
		for (Partido partido : findPartidos)
			if (print(partido, locale).equals(text))
				return partido;
		throw new ParseException("partido not found: " + text, 0);
	}

}
