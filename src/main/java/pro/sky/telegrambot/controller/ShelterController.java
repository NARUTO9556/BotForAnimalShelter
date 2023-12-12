package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.entity.Shelter;
import pro.sky.telegrambot.service.ShelterService;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterService shelterService;
    private final Logger logger = LoggerFactory.getLogger(ShelterService.class);

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @PostMapping
    public Shelter createShelter(@Parameter(description = "Все параметры добавляемого приюта") @RequestBody Shelter shelter) {
        logger.info("Приют добавлен");
        return shelterService.createShelter(shelter);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> findShelterById(@Parameter(description = "ID приюта") @PathVariable Long id) {
        Shelter shelter = shelterService.findShelterById(id);
        if (shelter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Приют найден");
        return ResponseEntity.ok(shelter);
    }
    @PutMapping("/updateShelter/{id}")
    public ResponseEntity<Shelter> updateShelter(@Parameter(description = "ID изменяемого приюта") @PathVariable Long id,
                                               @Parameter(description = "Новые параметры приюта") @RequestBody Shelter shelter) {
        Shelter updateShelter = shelterService.updateShelter(id, shelter);
        if (updateShelter == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные приюта обновлены");
            return ResponseEntity.ok(updateShelter);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Shelter> deleteShelter(@Parameter(description = "ID удаляемого приюта") @PathVariable Long id) {
        shelterService.deleteShelter(id);
        logger.info("Приют удален");
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Shelter> allReports() {
        logger.info("Список всех приютов");
        return shelterService.findAllShelters();
    }
}
