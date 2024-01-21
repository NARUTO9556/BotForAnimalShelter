package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Volunteer;
import pro.sky.telegrambot.service.VolunteerService;

import java.util.List;

/**
 * Класс для обработки запросов от клиента и возвращения результатов,
 * работает с сущностью {@link VolunteerService}.
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    private final VolunteerService volunteerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Operation(
            summary = "Создание волонтера",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные создаваемого волонтера." +
                            "id переданный в теле будет игнорироваться, будет присвоен следующий id из БД. " +
                            "Все поля кроме id обязательны.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные созданного волонтера",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )

                    )
            }
    )
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        logger.info("Добавление данных о волонтере");
        Volunteer createdVolunteer = volunteerService.createVolunteer(volunteer);
        return ResponseEntity.ok(createdVolunteer);
    }

    @Operation(
            summary = "Поиск волонтера по id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о найденном волонтере",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Волонтер с данным id не найден"
                    )
            }
    )
    @GetMapping("{volunteerId}")
    public ResponseEntity<Volunteer> findVolunteer(
            @Parameter(description = "Идентификатор волонтера", example = "1")
            @PathVariable long volunteerId) {
        logger.info("Поиск данных о волонтере");
        Volunteer volunteer = volunteerService.findVolunteerById(volunteerId);
        if (volunteer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(volunteer);
    }

    @Operation(
            summary = "Изменение данных волонтера.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные о волонтере с изменениями. Все поля обязательны.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Volunteer.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененные данные о волонтере",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Volunteer.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Волонтер с данным id не найден"
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Volunteer> updateVolunteer(@RequestBody Volunteer volunteer) {
        logger.info("Изменение данных о волонтере");
        Volunteer updatedVolunteer = volunteerService.updateVolunteer(volunteer);
        if (updatedVolunteer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedVolunteer);
    }

    @Operation(
            summary = "Удаление волонтера по id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Волонтер успешно удален"
                    )
            }
    )
    @DeleteMapping("{volunteerId}")
    public ResponseEntity<Volunteer> deleteVolunteer(
            @Parameter(description = "id удаляемого волонтера")
            @PathVariable long volunteerId) {
        logger.info("Удаление данных о волонтере");
        volunteerService.deleteVolunteer(volunteerId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить список всех волонтеров",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех волонтеров",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Volunteer.class))
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Volunteer>> findAllVolunteers() {
        logger.info("Вывод всех данных о волонтерах");
        return ResponseEntity.ok(volunteerService.findAllVolunteers());
    }
}
