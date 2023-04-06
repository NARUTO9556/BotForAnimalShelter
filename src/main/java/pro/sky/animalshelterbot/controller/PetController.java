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
import pro.sky.animalshelterbot.model.Pet;
import pro.sky.animalshelterbot.service.PetService;

import java.util.List;

/**
 * Класс для обработки запросов от клиента и возвращения результатов,
 * работает с сущностью {@link pro.sky.animalshelterbot.service.PetService}.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            summary = "Создание питомца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные создаваемого питомца. " +
                            "id переданный в теле будет игнорироваться, будет присвоен следующий id из БД. " +
                            "Обязательные поля: pet_name, age, kind_of_animal,animal_breed, shelter_id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "питомец успешно создан",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {

        return ResponseEntity.ok(petService.createPet(pet));
    }

    @Operation(
            summary = "Найти питомца по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные о питомце",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Питомец с таким id не найден"
                    )
            }
    )
    @GetMapping("{petId}")
    public ResponseEntity<Pet> findPet(
            @Parameter(description = "id питомца которого нужно найти", example = "1")
            @PathVariable Long petId) {

        Pet pet = petService.findPetById(petId);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }

    @Operation(
            summary = "Изменить данные о питомце",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные о питомце с изменениями. " +
                            "Обязательные поля: pet_name, age, kind_of_animal,animal_breed, shelter_id",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Данные о питомце с изменениями",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Pet.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Питомец с таким id не найден"
                    )
            }
    )
    @PutMapping
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {

        Pet updatedPet = petService.updatePet(pet);
        if (updatedPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPet);
    }

    @Operation(
            summary = "Удалить питомца по его id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Питомец успешно удален"
                    )
            }
    )
    @DeleteMapping("{petId}")
    public ResponseEntity<Pet> deletePet(
            @Parameter(description = "id питомца которого нужно удалить")
            @PathVariable Long petId) {

        petService.deletePet(petId);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Найти всех питомцев",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получен список всех питомцев",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Pet.class))
                            )
                    )
            }
    )
    @GetMapping("/findAll")
    public ResponseEntity<List<Pet>> findAllPets() {
        return ResponseEntity.ok(petService.findAllPets());
    }
}
