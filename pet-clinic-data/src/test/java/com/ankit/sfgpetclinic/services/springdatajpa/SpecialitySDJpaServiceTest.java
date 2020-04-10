package com.ankit.sfgpetclinic.services.springdatajpa;

import com.ankit.sfgpetclinic.model.Speciality;
import com.ankit.sfgpetclinic.repositories.SpecialityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    Speciality speciality;

    @BeforeEach
    void setUp() {
        speciality = Speciality.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(Speciality.builder().id(1L).build());
        specialities.add(Speciality.builder().id(2L).build());

        Mockito.when(specialityRepository.findAll()).thenReturn(specialities);

        Set<Speciality> specialitySet = specialitySDJpaService.findAll();
        assertNotNull(specialitySet);
        assertEquals(2, specialitySet.size());
        Mockito.verify(specialityRepository).findAll();
    }

    @Test
    void findById() {
        Mockito.when(specialityRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(speciality));
        Speciality speciality = specialitySDJpaService.findById(1L);
        assertNotNull(speciality);
        Mockito.verify(specialityRepository).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        Speciality speciality1 = Speciality.builder().id(1L).build();
        Mockito.when(specialityRepository.save(ArgumentMatchers.any())).thenReturn(speciality1);
        Speciality speciality2 = specialitySDJpaService.save(speciality1);
        assertNotNull(speciality2);
        Mockito.verify(specialityRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        specialitySDJpaService.delete(speciality);
        Mockito.verify(specialityRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        Mockito.verify(specialityRepository).deleteById(ArgumentMatchers.anyLong());
    }
}