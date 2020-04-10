package com.ankit.sfgpetclinic.services.map;

import com.ankit.sfgpetclinic.model.Speciality;
import com.ankit.sfgpetclinic.model.Vet;
import com.ankit.sfgpetclinic.services.SpecialityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VetMapServiceTest {

    VetMapService vetMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialityMapService());
        Speciality speciality = Speciality.builder().id(id).build();
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(speciality);
        vetMapService.save(Vet.builder().id(id).specialities(specialities).build());
    }

    @Test
    void findAll() {
        Set<Vet> vets = vetMapService.findAll();
        assertEquals(1, vets.size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(id);
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void save() {
        Long vetId = 2L;
        Speciality speciality = Speciality.builder().id(vetId).build();
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(speciality);
        vetMapService.save(Vet.builder().id(vetId).specialities(specialities).build());
        Vet vet = vetMapService.findById(vetId);
        assertEquals(vetId, vet.getId());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(id));
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void findById() {
        vetMapService.deleteById(id);
        assertEquals(0, vetMapService.findAll().size());
    }
}