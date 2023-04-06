package pro.sky.animalshelterbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.animalshelterbot.model.Pet;
import pro.sky.animalshelterbot.repository.PetRepository;

import java.util.List;

/**
 *Класс сервиса для работы с {@link PetRepository} и сущностью {@link Pet}
 */
@Service
public class PetService {

    private final PetRepository petRepository;

    private final Logger logger = LoggerFactory.getLogger(PetService.class);

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Метод сохраняет сущность {@link Pet} в БД
     *
     * @param pet данные о питомце
     * @return данные о питомце
     */
    public Pet createPet(Pet pet) {
        logger.info("Was invoked method - createPet");

        return petRepository.save(pet);
    }

    /**
     * Метод ищет питомца в БД по его id
     *
     * @param id идентификатор питомца
     * @return данные о питомце
     */
    public Pet findPetById(long id) {
        logger.info("Was invoked method - findPetById");

        return petRepository.findById(id);
    }

    /**
     * Метод изменят данные питомца
     *
     * @param pet измененные данные о питомце
     * @return измененные данные о питомце
     */
    public Pet updatePet(Pet pet) {
        logger.info("Was invoked method - update pet");

        if (petRepository.findById(pet.getId()).isPresent()) {
            return petRepository.save(pet);
        }
        return null;
    }

    /**
     * Метод удаляет питомца из БД
     *
     * @param id идентификатор питомца
     */
    public void deletePet(long id) {
        logger.info("Was invoked method - deletePet");
        petRepository.deleteById(id);
    }

    /**
     * Метод возвращает список всех питомцев в БД
     *
     * @return список всех питомцев
     */
    public List<Pet> findAllPets() {
        logger.info("Was invoked method - findAllPets");
        return petRepository.findAll();
    }
}
