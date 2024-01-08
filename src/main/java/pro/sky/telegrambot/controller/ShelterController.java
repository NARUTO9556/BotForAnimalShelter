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
import pro.sky.telegrambot.entity.Shelter;
import pro.sky.telegrambot.service.ShelterService;


import java.util.List;

/**
 * Контроллер сервиса приюта.
 */
@RestController
@RequestMapping("/shelter")
public class ShelterController {
    private final ShelterService shelterService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }


    /**
     * Эндпоинт для добавления приюта.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового приюта",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    })
    @PostMapping
    public Shelter createShelter(@Parameter(description = "Все параметры добавляемого приюта") @RequestBody Shelter shelter) {
        logger.info("Приют добавлен");
        return shelterService.createShelter(shelter);
    }

    /**
     * Эндпоинт для получения приюта.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации о приюте по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Shelter> findShelterById(@Parameter(description = "ID приюта") @PathVariable Long id) {
        Shelter shelter = shelterService.findShelterById(id);
        if (shelter == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Приют найден");
        return ResponseEntity.ok(shelter);
    }

    /**
     * Эндпоинт для обновления приюта.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных приюта",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    })
    @PutMapping("/updateShelter/{id}")
    public ResponseEntity<Shelter> updateShelter(@Parameter(description = "ID изменяемого приюта") @PathVariable Long id,
                                               @Parameter(description = "Новые параметры приюта") @RequestBody Shelter shelter) {
        Shelter updatedShelter = shelterService.updateShelter(id, shelter);
        if (updatedShelter == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные приюта обновлены");
            return ResponseEntity.ok(updatedShelter);
        }
    }

    /**
     * Эндпоинт для удаления приюта.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление приюта",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Shelter> deleteShelter(@Parameter(description = "ID удаляемого приюта") @PathVariable Long id) {
        shelterService.deleteShelter(id);
        logger.info("Приют удален");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода всех приютов.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех приютов",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            )
    })
    @GetMapping
    public List<Shelter> allShelters() {
        logger.info("Список всех приютов");
        return shelterService.findAllShelters();
    }
}
