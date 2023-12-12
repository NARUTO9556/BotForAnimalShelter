package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.repository.VolunteerRepository;

import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    public VolunteerServiceImpl(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        logger.info("Method createVolunteer has been run");
        return volunteerRepository.save(volunteer);
    }

    @Override
    public Volunteer findVolunteerById(long id) {
        logger.info("Method findVolunteerById has been run");
        return volunteerRepository.findById(id);
    }

    @Override
    public Volunteer updateVolunteer(Long id, Volunteer updatedVolunteer) {
        logger.info("Method updateVolunteer has been run");
        Volunteer volunteer = findVolunteerById(id);
        if (volunteer != null) {
            volunteer.setNameVolunteer(updatedVolunteer.getNameVolunteer());
            volunteer.setChatId(updatedVolunteer.getChatId());
            volunteer.setPhoneNumber(updatedVolunteer.getPhoneNumber());
            return volunteerRepository.save(volunteer);
        } else {
            return null;
        }
    }

    @Override
    public void deleteVolunteer(long id) {
        logger.info("Method deleteCustomer has been run");
        volunteerRepository.deleteById(id);
    }

    @Override
    public List<Volunteer> findAllVolunteers() {
        logger.info("Method updateReport has been run");
        return volunteerRepository.findAll();
    }
}
