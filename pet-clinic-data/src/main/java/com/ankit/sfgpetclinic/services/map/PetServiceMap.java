package com.ankit.sfgpetclinic.services.map;

import com.ankit.sfgpetclinic.model.Pet;
import com.ankit.sfgpetclinic.services.CrudService;
import com.ankit.sfgpetclinic.services.PetService;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.deleteByObject(object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
