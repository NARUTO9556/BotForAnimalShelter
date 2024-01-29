package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Customer;
import pro.sky.telegrambot.entity.Pet;
import pro.sky.telegrambot.repository.PetRepository;

import java.util.List;
@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final Logger logger = LoggerFactory.getLogger(PetService.class);

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet createPet(Pet pet) {
        logger.info("Method createPet has been run");
        return petRepository.save(pet);
    }

    @Override
    public Pet findPetById(long id) {
        logger.info("Method findPetById has been run");
        return petRepository.findById(id);
    }

    @Override
    public Pet updatePet(Long id, Pet updatedPet) {
        logger.info("Method updatePet has been run");
        Pet pet = findPetById(id);
        if (pet == null) {
            return null;
        }
        else {
            pet.setPetName(updatedPet.getPetName());
            pet.setAge(updatedPet.getAge());
            pet.setPhoto(updatedPet.getPhoto());
            pet.setKindOfAnimal(updatedPet.getKindOfAnimal());
            pet.setAnimalBreed(updatedPet.getAnimalBreed());
            pet.setShelterId(updatedPet.getShelterId());
            pet.setCustomer(updatedPet.getCustomer());
            return petRepository.save(pet);
        }
    }

    @Override
    public void deletePet(long id) {
        logger.info("Method deletePet has been run");
        petRepository.deleteById(id);
    }

    @Override
    public List<Pet> findAllPet() {
        logger.info("Method findAllPet has been run");
        return petRepository.findAll();
    }
}
