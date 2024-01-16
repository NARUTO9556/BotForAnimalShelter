package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Pet;

import java.util.List;
@Service
public class PetServiceImpl implements PetService {
    @Override
    public Pet createPet(Pet pet) {
        return null;
    }

    @Override
    public Pet findPetById(long id) {
        return null;
    }

    @Override
    public Pet updatePet(Pet updatedPet) {
        return null;
    }

    @Override
    public void deletePet(long id) {

    }

    @Override
    public List<Pet> findAllPet() {
        return null;
    }
}
