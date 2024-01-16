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
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.service.VolunteerService;

import java.util.List;

/**
 * Контроллер сервиса приютов.
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    /**
     * Эндпоинт для добавления волонтера.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового волонтера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    })
    @PostMapping
    public Volunteer createVolunteer(@Parameter(description = "Все параметры добавляемого волонтера") @RequestBody Volunteer volunteer) {
        logger.info("Волонтер добавлен");
        return volunteerService.createVolunteer(volunteer);
    }

    /**
     * Эндпоинт для получения волонтера.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации о волонтере по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> findVolunteerById(@Parameter(description = "ID волонтера") @PathVariable Long id) {
        Volunteer volunteer = volunteerService.findVolunteerById(id);
        if (volunteer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Волонтер найден");
        return ResponseEntity.ok(volunteer);
    }

    /**
     * Эндпоинт для обновления данных волонтера.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных волонтера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    })
    @PutMapping("/updateVolunteer/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@Parameter(description = "ID изменяемого волонтера") @PathVariable Long id,
                                         @Parameter(description = "Новые параметры волонтера") @RequestBody Volunteer volunteer) {
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(id, volunteer);
        if (updatedVolunteer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные волонтера обновлены");
            return ResponseEntity.ok(updatedVolunteer);
        }
    }

    /**
     * Эндпоинт для удаления волонтера.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление волонтера",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@Parameter(description = "ID удаляемого волонтера") @PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
        logger.info("Волонтер удален");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода всех волонтеров.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех волонтеров",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            )
    })
    @GetMapping
    public List<Volunteer> allVolunteers() {
        logger.info("Список всех волонтеров");
        return volunteerService.findAllVolunteers();
    }
}
