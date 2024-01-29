package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Shelter;
import pro.sky.telegrambot.repository.ShelterRepository;

import java.util.List;
@Service
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository shelterRepository;
    private final Logger logger = LoggerFactory.getLogger(ShelterService.class);

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Shelter createShelter(Shelter shelter) {
        logger.info("Method createShelter has been run");
        return shelterRepository.save(shelter);
    }

    @Override
    public Shelter findShelterById(long id) {
        logger.info("Method findShelterById has been run");
        return shelterRepository.findById(id);
    }

    @Override
    public Shelter updateShelter(Long id, Shelter updatedShelter) {
        logger.info("Method updateShelter has been run");
        Shelter shelter = findShelterById(id);
        if (shelter != null) {
            shelter.setName(updatedShelter.getName());
            shelter.setAddress(updatedShelter.getAddress());
            shelter.setInfo(updatedShelter.getInfo());
            shelter.setMap(updatedShelter.getMap());
            return shelterRepository.save(shelter);
        } else {
            return null;
        }
    }

    @Override
    public void deleteShelter(long id) {
        logger.info("Method deleteShelter has been run");
        shelterRepository.deleteById(id);
    }

    @Override
    public List<Shelter> findAllShelters() {
        logger.info("Method findAllShelters has been run");
        return shelterRepository.findAll();
    }
}
