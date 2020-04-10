package com.ankit.sfgpetclinic.services.springdatajpa;

import com.ankit.sfgpetclinic.model.PetType;
import com.ankit.sfgpetclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService petTypeSDJpaService;

    PetType petType;

    @BeforeEach
    void setUp() {
        petType = PetType.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = new HashSet<>();
        petTypeSet.add(PetType.builder().id(1L).build());
        petTypeSet.add(PetType.builder().id(2L).build());

        Mockito.when(petTypeRepository.findAll()).thenReturn(petTypeSet);

        Set<PetType> petTypes = petTypeSDJpaService.findAll();

        assertNotNull(petTypes);
        assertEquals(2, petTypes.size());
        Mockito.verify(petTypeRepository).findAll();
    }

    @Test
    void findById() {
        Mockito.when(petTypeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(petType));

        PetType petType1 = petTypeSDJpaService.findById(1L);
        assertNotNull(petType1);
        Mockito.verify(petTypeRepository).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        PetType petType1 = PetType.builder().id(1L).build();
        Mockito.when(petTypeRepository.save(ArgumentMatchers.any())).thenReturn(petType1);

        PetType petType2 = petTypeSDJpaService.save(petType1);
        assertNotNull(petType2);
        Mockito.verify(petTypeRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        petTypeRepository.delete(petType);
        Mockito.verify(petTypeRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        petTypeRepository.deleteById(1L);
        Mockito.verify(petTypeRepository).deleteById(ArgumentMatchers.anyLong());
    }
}