package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Pet;
import pro.sky.telegrambot.service.PetService;

import java.util.List;

/**
 * Контроллер сервиса домашних животных.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetService petService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Эндпоинт для добавления домашнего животного.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового домашнего животного",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    })
    @PostMapping
    public Pet createPet(@Parameter(description = "Все параметры добавляемого домашнего животного") @RequestBody Pet pet) {
        logger.info("Домашнее животное добавлено");
        return petService.createPet(pet);
    }

    /**
     * Эндпоинт для получения домашнего животного.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации о домашнем животном по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pet> findPetById(@Parameter(description = "ID домашнего животного") @PathVariable Long id) {
        Pet pet = petService.findPetById(id);
        if (pet == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Домашнее животное найден");
        return ResponseEntity.ok(pet);
    }

    /**
     * Эндпоинт для обновления данных домашнего животного.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных домашнего животного",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    })
    @PutMapping("/updatePet/{id}")
    public ResponseEntity<Pet> updatePet(@Parameter(description = "ID изменяемого домашнего животного") @PathVariable Long id,
                                                   @Parameter(description = "Новые параметры домашнего животного") @RequestBody Pet pet) {
        Pet updatedPet = petService.updatePet(id, pet);
        if (updatedPet == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные домашнего животного обновлены");
            return ResponseEntity.ok(updatedPet);
        }
    }

    /**
     * Эндпоинт для удаления домашнего животного.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление домашнего животного",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePet(@Parameter(description = "ID удаляемого домашнего животного") @PathVariable Long id) {
        petService.deletePet(id);
        logger.info("Домашнее животное удалено");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода всех домашних животных.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех домашних животных",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    })
    @GetMapping
    public List<Pet> allPets() {
        logger.info("Список всех домашних животных");
        return petService.findAllPet();
    }
}
