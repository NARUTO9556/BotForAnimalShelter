package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Shelter;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.service.ShelterService;
import pro.sky.telegrambot.service.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;
    private final Logger logger = LoggerFactory.getLogger(VolunteerService.class);
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping
    public Volunteer createVolunteer(@Parameter(description = "Все параметры добавляемого с") @RequestBody Volunteer volunteer) {
        logger.info("Волонтер добавлен");
        return volunteerService.createVolunteer(volunteer);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> findShelterById(@Parameter(description = "ID волонтера") @PathVariable Long id) {
        Volunteer volunteer = volunteerService.findVolunteerById(id);
        if (volunteer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Волонтер найден");
        return ResponseEntity.ok(volunteer);
    }
    @PutMapping("/updateVolunteer/{id}")
    public ResponseEntity<Volunteer> updateShelter(@Parameter(description = "ID изменяемого волонтера") @PathVariable Long id,
                                                 @Parameter(description = "Новые параметры волонтера") @RequestBody Volunteer volunteer) {
        Volunteer updateVolunteer = volunteerService.updateVolunteer(id, volunteer);
        if (updateVolunteer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные волонтера обновлены");
            return ResponseEntity.ok(updateVolunteer);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@Parameter(description = "ID удаляемого волонтера") @PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        logger.info("Волонтер удален");
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Volunteer> allVolunteer() {
        logger.info("Список всех волонтеров");
        return volunteerService.findAllVolunteers();
    }
}
