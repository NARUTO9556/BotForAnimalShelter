package pro.sky.telegrambot.service;


import pro.sky.telegrambot.entity.Shelter;

import java.util.List;

public interface ShelterService {
    Shelter createShelter(Shelter shelter);
    Shelter findShelterById(long id);
    Shelter updateShelter(Long id, Shelter updatedShelter);
    void deleteShelter(long id);
    List<Shelter> findAllShelters();
}
