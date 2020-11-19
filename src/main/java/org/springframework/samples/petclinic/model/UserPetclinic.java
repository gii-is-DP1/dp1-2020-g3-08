package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users_petclinic")
public class UserPetclinic{
	@Id
	String username;

	String password;

	boolean enabled;

	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	//private Set<Authorities> authorities;
}
