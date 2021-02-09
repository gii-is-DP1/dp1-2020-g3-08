
package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;


	@Override
	protected void configure(final HttpSecurity http) throws Exception {




		http.authorizeRequests().antMatchers("/resources/**", "/webjars/**", "/h2-console/**").permitAll()
		.antMatchers(HttpMethod.GET, "/", "/oups").permitAll()
		//NOTICIAS
		.antMatchers("/noticias/new").hasAnyAuthority("admin")
		.antMatchers("/noticias/**/delete").hasAnyAuthority("admin")
		.antMatchers("/noticias/**/edit").hasAnyAuthority("admin")
		.antMatchers("/noticias/**").hasAnyAuthority("user", "admin", "entrenador")
		//USERS
		.antMatchers("/users/new").permitAll()
		.antMatchers("/users/**").hasAnyAuthority("user", "admin", "entrenador")
		.antMatchers("/admin/**").hasAnyAuthority("admin")
		//EQUIPOS
		.antMatchers("/equipos/**").hasAnyAuthority("user", "admin", "entrenador")
		//JUGADORES
		.antMatchers("/jugadores/**").hasAnyAuthority("user", "admin", "entrenador")
		//ARBITROS
		.antMatchers("/arbitros/**/new").hasAnyAuthority("admin")
		.antMatchers("/arbitros/**/edit").hasAnyAuthority("admin")
		.antMatchers("/arbitros/**/delete").hasAnyAuthority("admin")
		.antMatchers("/arbitros/**").hasAnyAuthority("user", "admin", "entrenador")
		//COMPETICIONES
		.antMatchers("/competiciones/**/new").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/edit").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/delete").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/equipos/new").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/equipos/**").hasAnyAuthority("admin","entrenador")
		.antMatchers("/competiciones/**/partidos/**/new").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/partidos/**/edit").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/partidos/**/delete").hasAnyAuthority("admin")
		.antMatchers("/competiciones/**/partidos/**").hasAnyAuthority("user", "admin", "entrenador")
		.antMatchers("/competiciones/**").permitAll()
		//ENTRENADORES
		.antMatchers("/entrenadores/**").hasAnyAuthority("admin")
		//PARTIDOS
		.antMatchers("/partidos/**/administrarJugadores").hasAnyAuthority("admin","entrenador")
		.antMatchers("/partidos/**").permitAll()
		.anyRequest().denyAll().and().formLogin()
		.failureUrl("/login-error").and().logout().logoutSuccessUrl("/");



		// Configuración para que funcione la consola de administración
		// de la BD H2 (deshabilitar las cabeceras de protección contra
		// ataques de tipo csrf y habilitar los framesets si su contenido
		// se sirve desde esta misma página.
		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password,enabled " + "from users " + "where username = ?")
		.authoritiesByUsernameQuery("select username, authority " + "from authorities " + "where username = ?").passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = NoOpPasswordEncoder.getInstance();
		return encoder;
	}

}
