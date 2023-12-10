package pro.sky.telegrambot.service;



import pro.sky.telegrambot.entity.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    Pet findPetById(long id);
    Pet updatePet(Pet updatedPet);
    void deletePet(long id);
    List<Pet> findAllPet();
}
