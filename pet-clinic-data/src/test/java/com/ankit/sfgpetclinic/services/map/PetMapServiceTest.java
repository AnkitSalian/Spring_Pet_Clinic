package com.ankit.sfgpetclinic.services.map;

import com.ankit.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    PetMapService petMapService;
    final Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void save() {
        Long id = 2L;
        Pet pet = Pet.builder().id(id).build();
        petMapService.save(pet);
        Pet pet1 = petMapService.findById(id);
        assertEquals(id, pet1.getId());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);
        assertEquals(petId, pet.getId());
    }
}