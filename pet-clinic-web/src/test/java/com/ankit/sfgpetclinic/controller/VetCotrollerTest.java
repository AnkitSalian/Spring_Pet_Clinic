package com.ankit.sfgpetclinic.controller;

import com.ankit.sfgpetclinic.model.Vet;
import com.ankit.sfgpetclinic.services.VetService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VetCotrollerTest {

    @Mock
    VetService vetService;

    @InjectMocks
    VetCotroller vetCotroller;

    Set<Vet> vets;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(vetCotroller)
                .build();
    }

    @Test
    void listVets() throws Exception {
        Mockito.when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(MockMvcRequestBuilders.get("/vets.html"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("vets/index"))
                .andExpect(MockMvcResultMatchers.model().attribute("vets", Matchers.hasSize(2)));
    }
}