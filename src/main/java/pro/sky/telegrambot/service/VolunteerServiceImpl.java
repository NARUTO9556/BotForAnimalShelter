package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Volunteer;

import java.util.List;
@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return null;
    }

    @Override
    public Volunteer findVolunteerById(long id) {
        return null;
    }

    @Override
    public Volunteer updateVolunteer(Long id, Volunteer updatedVolunteer) {
        return null;
    }

    @Override
    public void deleteVolunteer(long id) {

    }

    @Override
    public List<Volunteer> findAllVolunteers() {
        return null;
    }
}
