package com.ankit.sfgpetclinic.services.map;

import com.ankit.sfgpetclinic.model.Owner;
import com.ankit.sfgpetclinic.model.Pet;
import com.ankit.sfgpetclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitMapServiceTest {

    VisitMapService visitMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        visitMapService = new VisitMapService();
        Owner owner = Owner.builder().id(id).build();
        Pet pet = Pet.builder().id(id).owner(owner).build();
        pet.setId(id);
        Visit visit = Visit.builder().id(id).pet(pet).build();
        visitMapService.save(visit);
    }

    @Test
    void findAll() {
        Set<Visit> visits = visitMapService.findAll();
        assertEquals(1, visits.size());
    }

    @Test
    void findById() {
        Visit visit = visitMapService.findById(id);
        assertEquals(id, visit.getId());
    }

    @Test
    void save() {
        long visitId = 2L;
        Owner owner = Owner.builder().id(visitId).build();
        Pet pet = Pet.builder().id(visitId).owner(owner).build();
        pet.setId(visitId);
        Visit visit = Visit.builder().id(visitId).pet(pet).build();
        visitMapService.save(visit);
        Visit visit1 = visitMapService.findById(visitId);
        assertEquals(visitId, visit1.getId());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(id));
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(id);
        assertEquals(0, visitMapService.findAll().size());
    }
}