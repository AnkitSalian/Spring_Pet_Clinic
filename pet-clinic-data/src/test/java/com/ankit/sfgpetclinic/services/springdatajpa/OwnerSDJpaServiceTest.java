package com.ankit.sfgpetclinic.services.springdatajpa;

import com.ankit.sfgpetclinic.model.Owner;
import com.ankit.sfgpetclinic.repositories.OwnerRepository;
import com.ankit.sfgpetclinic.repositories.PetRepository;
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

@ExtendWith({MockitoExtension.class})
class OwnerSDJpaServiceTest {

    public final String LAST_NAME = "Smith";

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        Mockito.when(ownerRepository.findByLastName(ArgumentMatchers.any())).thenReturn(owner);
        Owner smith = ownerSDJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        Mockito.verify(ownerRepository).findByLastName(ArgumentMatchers.any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        Mockito.when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> ownerSet = ownerSDJpaService.findAll();

        assertNotNull(ownerSet);
        assertEquals(2, ownerSet.size());
    }

    @Test
    void findById() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(owner));

        Owner owner1 = ownerSDJpaService.findById(1L);
        assertNotNull(owner1);
    }

    @Test
    void findByIdNotFound() {
        Mockito.when(ownerRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Owner owner1 = ownerSDJpaService.findById(1L);
        assertNull(owner1);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        Mockito.when(ownerRepository.save(ArgumentMatchers.any())).thenReturn(ownerToSave);
        Owner savedOwner = ownerSDJpaService.save(ownerToSave);
        assertNotNull(savedOwner);
        Mockito.verify(ownerRepository).save(ArgumentMatchers.any());
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);
        Mockito.verify(ownerRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);
        Mockito.verify(ownerRepository).deleteById(ArgumentMatchers.anyLong());
    }
}