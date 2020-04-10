package com.ankit.sfgpetclinic.services.map;

import com.ankit.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<PetType> petTypes = petTypeMapService.findAll();
        assertEquals(1, petTypes.size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(id);
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void delete() {
       petTypeMapService.delete(petTypeMapService.findById(id));
       assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void save() {
        long petTypeId = 2L;
        PetType petType = PetType.builder().id(petTypeId).build();
        petTypeMapService.save(petType);
        PetType petType1 = petTypeMapService.findById(petTypeId);
        assertEquals(petTypeId, petType1.getId());
    }

    @Test
    void findById() {
        PetType petType = petTypeMapService.findById(id);
        assertEquals(id, petType.getId());
    }
}