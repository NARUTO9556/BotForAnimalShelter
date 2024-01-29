package pro.sky.telegrambot.service;

import pro.sky.telegrambot.entity.Volunteer;

import java.util.List;

public interface VolunteerService {
    Volunteer createVolunteer(Volunteer volunteer);
    Volunteer findVolunteerById(long id);
    Volunteer updateVolunteer(Volunteer updatedVolunteer);
    void deleteCustomer(long id);
    List<Volunteer> findAllVolunteers();
}
