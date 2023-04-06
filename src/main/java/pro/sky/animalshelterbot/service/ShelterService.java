package pro.sky.animalshelterbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.animalshelterbot.model.Shelter;
import pro.sky.animalshelterbot.repository.ShelterRepository;

import java.util.List;

/**
 * Класс сервиса для работы с {@link ShelterRepository} и сущностью {@link Shelter}
 */
@Service
public class ShelterService {

    private final ShelterRepository shelterRepository;

    private final Logger logger = LoggerFactory.getLogger(ShelterService.class);

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Метод сохраняет сущность {@link Shelter} в БД, с помошью метода {@link ShelterRepository#save(Object)}.
     *
     * @param shelter данные о приюте
     * @return данные о сохраненном приюте
     */
    public Shelter createShelter(Shelter shelter) {
        logger.info("Was invoked method - createShelter");
        return shelterRepository.save(shelter);
    }

    /**
     * Метод ищет данные о приюте по его id.
     * Используется метод {@link ShelterRepository#findById(long)}
     *
     * @param id идентификатор приюта
     * @return данные о приюте
     */
    public Shelter findShelterById(long id) {
        logger.info("Was invoked method - findShelterById");
        return shelterRepository.findById(id);
    }

    /**
     * Метод изменяет данные приюта, заменяет сущность {@link Shelter} в БД по указаннму в ней id.
     * Использует метод {@link ShelterRepository#save(Object)}
     *
     * @param shelter измененные данные приюта
     * @return измененные данные приюта, если приют не найден возвращает {@code null}
     */
    public Shelter updateShelter(Shelter shelter) {
        logger.info("Was invoked method - updateStudent");

        if (shelterRepository.findById(shelter.getId()).isPresent()) {
            return shelterRepository.save(shelter);
        }
        return null;
    }

    /**
     * Метод удаляет приют по указанному id.
     * Использует метод {@link ShelterRepository#deleteById(Object)}
     *
     * @param id идентификатор удаляемого приюта
     */
    public void deleteShelter(long id) {
        logger.info("Was invoked method - updateStudent");
        shelterRepository.deleteById(id);
    }

    /**
     * Метод возвращает список всех приютов из БД.
     *
     * @return список всех приютов
     */
    public List<Shelter> findAllShelters() {
        logger.info("Was invoked method - findAllShelters");
        return shelterRepository.findAll();
    }
}
