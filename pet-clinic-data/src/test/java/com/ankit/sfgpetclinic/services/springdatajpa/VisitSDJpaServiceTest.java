package com.ankit.sfgpetclinic.services.springdatajpa;

import com.ankit.sfgpetclinic.model.Owner;
import com.ankit.sfgpetclinic.model.Pet;
import com.ankit.sfgpetclinic.model.Visit;
import com.ankit.sfgpetclinic.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;

    Visit visit;

    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().id(1L).build();
        Pet pet = Pet.builder().id(1L).owner(owner).build();
        visit = Visit.builder().id(1L).pet(pet).build();
    }

    @Test
    void findAll() {
        Owner owner = Owner.builder().id(1L).build();
        Pet pet = Pet.builder().id(1L).owner(owner).build();

        Set<Visit> visits = new HashSet<>();
        visits.add(Visit.builder().id(1L).pet(pet).build());
        visits.add(Visit.builder().id(2L).pet(pet).build());

        Mockito.when(visitRepository.findAll()).thenReturn(visits);

        Set<Visit> visitSet = visitSDJpaService.findAll();
        assertNotNull(visitSet);
        assertEquals(2, visitSet.size());
        Mockito.verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        Mockito.when(visitRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(visit));
        Visit visit1 = visitSDJpaService.findById(1L);
        assertNotNull(visit1);
        Mockito.verify(visitRepository).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(1L).build();
        Pet pet = Pet.builder().id(1L).owner(owner).build();
        Visit visit1 = Visit.builder().id(1L).pet(pet).build();

        Mockito.when(visitRepository.save(ArgumentMatchers.any())).thenReturn(visit1);

        Visit visit2 = visitSDJpaService.save(visit1);
        assertNotNull(visit2);
        Mockito.verify(visitRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        visitSDJpaService.delete(visit);
        Mockito.verify(visitRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1L);
        Mockito.verify(visitRepository).deleteById(ArgumentMatchers.anyLong());
    }
}