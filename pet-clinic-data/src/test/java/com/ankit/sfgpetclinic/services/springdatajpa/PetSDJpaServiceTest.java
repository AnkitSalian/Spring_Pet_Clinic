package com.ankit.sfgpetclinic.services.springdatajpa;

import com.ankit.sfgpetclinic.model.Pet;
import com.ankit.sfgpetclinic.repositories.PetRepository;
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
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService petSDJpaService;

    Pet pet;

    @BeforeEach
    void setUp() {
        pet = Pet.builder().id(1L).build();
    }

    @Test
    void findAll() {
        Set<Pet> pets = new HashSet<>();
        pets.add(Pet.builder().id(1L).build());
        pets.add(Pet.builder().id(2L).build());

        Mockito.when(petRepository.findAll()).thenReturn(pets);

        Set<Pet> petSet = petSDJpaService.findAll();
        assertNotNull(petSet);
        assertEquals(2, petSet.size());
        Mockito.verify(petRepository).findAll();
    }

    @Test
    void findById() {
        Mockito.when(petRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(pet));
        Pet pet = petSDJpaService.findById(1L);
        assertNotNull(pet);
        Mockito.verify(petRepository).findById(ArgumentMatchers.anyLong());
    }

    @Test
    void save() {
        Pet pet1 = Pet.builder().id(1L).build();
        Mockito.when(petRepository.save(ArgumentMatchers.any())).thenReturn(pet1);

        Pet pet2 = petSDJpaService.save(pet1);
        assertNotNull(pet2);
        Mockito.verify(petRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        petSDJpaService.delete(pet);
        Mockito.verify(petRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        petSDJpaService.deleteById(1L);
        Mockito.verify(petRepository).deleteById(ArgumentMatchers.anyLong());
    }
}