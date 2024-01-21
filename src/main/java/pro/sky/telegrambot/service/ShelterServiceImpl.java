package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Shelter;
import pro.sky.telegrambot.repository.ShelterRepository;

import java.util.List;

/**
 * Класс сервиса для работы с {@link ShelterRepository} и сущностью {@link Shelter}
 */
@Service
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;
    private final Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Метод сохраняет сущность {@link Shelter} в БД, с помошью метода {@link ShelterRepository#save(Object)}.
     *
     * @param shelter данные о приюте
     * @return данные о сохраненном приюте
     */
    @Override
    public Shelter createShelter(Shelter shelter) {
        logger.info("Was invoked - method createShelter");
        return shelterRepository.save(shelter);
    }

    /**
     * Метод ищет данные о приюте по его id.
     * Используется метод {@link ShelterRepository#findById(long)}
     *
     * @param id идентификатор приюта
     * @return данные о приюте
     */
    @Override
    public Shelter findShelterById(long id) {
        logger.info("Was invoked - method findShelterById");
        return shelterRepository.findById(id);
    }

    /**
     * Метод изменяет данные приюта, заменяет сущность {@link Shelter} в БД по указаннму в ней id.
     * Использует метод {@link ShelterRepository#save(Object)}
     *
     * @param updatedShelter измененные данные приюта
     * @return измененные данные приюта, если приют не найден возвращает {@code null}
     */
    @Override
    public Shelter updateShelter(Shelter updatedShelter) {
        logger.info("Was invoked - method createShelter");
        if (shelterRepository.findById(updatedShelter.getId()).isPresent()) {
            return shelterRepository.save(updatedShelter);
        }
        return null;
    }

    /**
     * Метод удаляет приют по указанному id.
     * Использует метод {@link ShelterRepository#deleteById(Object)}
     *
     * @param id идентификатор удаляемого приюта
     */
    @Override
    public void deleteShelter(long id) {
        logger.info("Was invoked - method createShelter");
        shelterRepository.deleteById(id);
    }

    @Override
    public List<Shelter> findAllShelters() {
        logger.info("Was invoked - method createShelter");
        return shelterRepository.findAll();
    }
}
