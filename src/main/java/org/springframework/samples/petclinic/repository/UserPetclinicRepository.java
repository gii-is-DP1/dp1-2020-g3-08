package org.springframework.samples.petclinic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.UserPetclinic;


public interface UserPetclinicRepository extends  CrudRepository<UserPetclinic, String>{
	
}
