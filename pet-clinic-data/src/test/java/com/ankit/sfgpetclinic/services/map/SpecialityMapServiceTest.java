package com.ankit.sfgpetclinic.services.map;

import com.ankit.sfgpetclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SpecialityMapServiceTest {

    SpecialityMapService specialityMapService;
    final Long id = 1L;

    @BeforeEach
    void setUp() {
        specialityMapService = new SpecialityMapService();
        specialityMapService.save(Speciality.builder().id(id).build());
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = specialityMapService.findAll();
        assertEquals(1, specialities.size());
    }

    @Test
    void findById() {
        Speciality speciality = specialityMapService.findById(id);
        assertEquals(id, speciality.getId());
    }

    @Test
    void save() {
        Long specialityId = 2L;
        Speciality speciality = Speciality.builder().id(specialityId).build();
        specialityMapService.save(speciality);
        Speciality speciality1 = specialityMapService.findById(specialityId);
        assertEquals(specialityId, speciality1.getId());
    }

    @Test
    void delete() {
        specialityMapService.delete(specialityMapService.findById(id));
        assertEquals(0, specialityMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialityMapService.deleteById(id);
        assertEquals(0, specialityMapService.findAll().size());
    }
}