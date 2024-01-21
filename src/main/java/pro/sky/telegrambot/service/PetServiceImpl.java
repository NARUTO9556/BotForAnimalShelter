package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Pet;
import pro.sky.telegrambot.repository.PetRepository;

import java.util.List;

/**
 *Класс сервиса для работы с {@link PetRepository} и сущностью {@link Pet}
 */
@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Метод сохраняет сущность {@link Pet} в БД
     *
     * @param pet данные о питомце
     * @return данные о питомце
     */
    @Override
    public Pet createPet(Pet pet) {
        logger.info("Was invoked - method createCustomer");
        return petRepository.save(pet);
    }

    /**
     * Метод ищет питомца в БД по его id
     *
     * @param id идентификатор питомца
     * @return данные о питомце
     */
    @Override
    public Pet findPetById(long id) {
        logger.info("Was invoked - method findPetById");
        return petRepository.findById(id);
    }

    /**
     * Метод изменят данные питомца
     *
     * @param updatedPet измененные данные о питомце
     * @return измененные данные о питомце
     */
    @Override
    public Pet updatePet(Pet updatedPet) {
        logger.info("Was invoked - method updatePet");
        if (petRepository.findById(updatedPet.getId()).isPresent()) {
            return petRepository.save(updatedPet);
        }
        return null;
    }

    /**
     * Метод удаляет питомца из БД
     *
     * @param id идентификатор питомца
     */
    @Override
    public void deletePet(long id) {
        logger.info("Was invoked - method deletePet");
        petRepository.deleteById(id);

    }

    /**
     * Метод возвращает список всех питомцев в БД
     *
     * @return список всех питомцев
     */
    @Override
    public List<Pet> findAllPet() {
        logger.info("Was invoked - method findAllPet");
        return petRepository.findAll();
    }
}
