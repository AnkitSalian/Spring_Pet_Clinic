package com.ankit.sfgpetclinic.repositories;

import com.ankit.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
