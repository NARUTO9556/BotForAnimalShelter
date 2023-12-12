package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Customer;
import pro.sky.telegrambot.entity.Pet;
import pro.sky.telegrambot.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    private final Logger logger = LoggerFactory.getLogger(PetService.class);
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public Pet createPet(@Parameter(description = "Все параметры добавляемого домашнего животного") @RequestBody Pet pet) {
        logger.info("Домашнее животное добавлено");
        return petService.createPet(pet);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pet> findPetById(@Parameter(description = "ID домашнего животного") @PathVariable Long id) {
        Pet pet = petService.findPetById(id);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Домашнее животное найдено");
        return ResponseEntity.ok(pet);
    }
    @PutMapping("/updatePet/{id}")
    public ResponseEntity<Pet> updatePet(@Parameter(description = "ID изменяемого домашнего животного") @PathVariable Long id,
                                                   @Parameter(description = "Новые параметры домашнего животного") @RequestBody Pet pet) {
        Pet updatePet = petService.updatePet(id, pet);
        if (updatePet == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные домашнего животного обновлены");
            return ResponseEntity.ok(updatePet);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> delete(@Parameter(description = "ID удаляемого домашнего животного") @PathVariable Long id) {
        petService.deletePet(id);
        logger.info("Домашнеее животное удалено");
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Pet> allPets() {
        logger.info("Список всех домашних животных");
        return petService.findAllPet();
    }
}
