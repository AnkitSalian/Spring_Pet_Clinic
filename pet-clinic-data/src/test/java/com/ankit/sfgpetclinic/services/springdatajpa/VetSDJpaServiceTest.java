package com.ankit.sfgpetclinic.services.springdatajpa;

import com.ankit.sfgpetclinic.model.Speciality;
import com.ankit.sfgpetclinic.model.Vet;
import com.ankit.sfgpetclinic.repositories.VetRepository;
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
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService vetSDJpaService;

    Vet vet;

    @BeforeEach
    void setUp() {
        Speciality speciality = Speciality.builder().id(1L).build();
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(speciality);
        vet = Vet.builder().id(1L).specialities(specialities).build();
    }

    @Test
    void findAll() {
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(Speciality.builder().id(1L).build());
        specialities.add(Speciality.builder().id(2L).build());

        Set<Vet> vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).specialities(specialities).build());
        vets.add(Vet.builder().id(2L).specialities(specialities).build());

        Mockito.when(vetRepository.findAll()).thenReturn(vets);

        Set<Vet> vetSet = vetSDJpaService.findAll();
        assertNotNull(vetSet);
        assertEquals(2, vetSet.size());
        Mockito.verify(vetRepository).findAll();
    }

    @Test
    void findById() {
        Mockito.when(vetRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vet));
        Vet vet1 = vetSDJpaService.findById(1L);
        assertNotNull(vet1);
        Mockito.verify(vetRepository).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        Set<Speciality> specialities = new HashSet<>();
        specialities.add(Speciality.builder().id(1L).build());
        specialities.add(Speciality.builder().id(2L).build());

        Vet vet1 = Vet.builder().id(1L).specialities(specialities).build();
        Mockito.when(vetRepository.save(ArgumentMatchers.any())).thenReturn(vet1);

        Vet vet2 = vetSDJpaService.save(vet1);
        assertNotNull(vet2);
        Mockito.verify(vetRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        vetSDJpaService.delete(vet);
        Mockito.verify(vetRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        vetSDJpaService.deleteById(1L);
        Mockito.verify(vetRepository).deleteById(ArgumentMatchers.anyLong());
    }
}