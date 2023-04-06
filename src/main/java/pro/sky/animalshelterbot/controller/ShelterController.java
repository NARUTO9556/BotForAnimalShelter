package pro.sky.animalshelterbot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.animalshelterbot.model.Shelter;
import pro.sky.animalshelterbot.service.ShelterService;

import java.util.List;

/**
 * Класс для обработки запросов от клиента и возвращения результатов,
 * работает с сущностью {@link pro.sky.animalshelterbot.service.ShelterService}.
 */
@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    /**
     * Метод принимает запрос с телом в формате JSON и создает сущность {@link Shelter}.
     * Используется метод createShelter из сервиса {@link ShelterService}
     *
     * @param shelter тело запроса с данными о создаваемом приюте
     * @return данные созданного приюта
     */
    @Operation(
            summary = "Создание приюта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные создаваемого приюта. " +
                            "id переданный в теле будет игнорироваться, будет присвоен следующий id из БД. " +
                            "Поля: name и address обязательны к заполнению.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные созданного приюта",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Shelter> createShelter(@RequestBody Shelter shelter) {

        Shelter createdShelter = shelterService.createShelter(shelter);
        return ResponseEntity.ok(createdShelter);
    }

    /**
     * Метод принимает запрос с параметром id приюта и возвращает в теле ответа данные о приюте.
     *
     * @param shelterId - идентификатор приюта
     * @return 200 - данные о приюте, 404 - приют не найден
     */
    @Operation(
            summary = "Поиск приюта по идентификатору",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные о приюте",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Shelter.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Приют с таким id не найден"
                    )
            }
    )
    @GetMapping({"{shelterId}"})
    public ResponseEntity<Shelter> getShelter(
            @Parameter(description = "id приюта который нужно найти", example = "3")
            @PathVariable long shelterId) {

        Shelter shelter = shelterService.findShelterById(shelterId);
        if (shelter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shelter);
    }

    /**
     * Метод принимает запрос с телом в формате JSON и изменяет данные
     * в сущности {@link Shelter} согласно новым данным.
     * Используется метод updateShelter из сервиса {@link ShelterService}
     *
     * @param shelter тело запроса с измененными данными о приюте
     * @return 200 - данные измененного приюта, 404 - приют не найден
     */
    @Operation(
            summary = "Изменить данные о приюте",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные о приюте с изменениями. " +
                            "Обязательны поля: id приюта в котором нужно внести изменения, name и address.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Shelter.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененные данные о приюте",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Shelter.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Приют с таким id не найден"
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Shelter> updateShelter(@RequestBody Shelter shelter) {

        Shelter updatedShelter = shelterService.updateShelter(shelter);
        if (updatedShelter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedShelter);
    }

    /**
     * Метод принимает запрос с параметром id приюта и удаляет сущность {@link Shelter}
     * Используется метод deleteShelter из сервиса {@link ShelterService}
     *
     * @param shelterId идентификатор удаляемого приюта
     * @return 200
     */
    @Operation(
            summary = "Удаление приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Приют успешно удален"
                    )
            }
    )
    @DeleteMapping("{shelterId}")
    public ResponseEntity<Shelter> deleteShelter(
            @Parameter(description = "id приюта который нужно удалить")
            @PathVariable long shelterId) {

        shelterService.deleteShelter(shelterId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получить список всех приютов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список всех приютов",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Shelter.class))
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Shelter>> findAllShelters() {
        return ResponseEntity.ok(shelterService.findAllShelters());
    }
}