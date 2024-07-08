package pro.sky.animalshelterbot.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.animalshelterbot.model.Pet;
import pro.sky.animalshelterbot.repository.PetRepository;
import pro.sky.animalshelterbot.service.PetService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetRepository petRepository;

    @SpyBean
    private PetService petService;

    @InjectMocks
    private PetController petController;

    static final Long id1 = 1L;
    static final String petName1 = "testPet1";
    static final Integer age1 = 3;
    //static final byte[] photo1;
    static final String kindOfAnimal1 = "cat";
    static final String animalBreed1 = "siamese";
    static final Long shelterId1 = 1L;
    //static final Customer customer1;


    static final long idTest = 1;

    static final Pet PET1 = new Pet(id1, petName1, age1, null, kindOfAnimal1, animalBreed1, shelterId1, null);


    @Test
    public void testPetController() throws Exception {
        JSONObject petObject = new JSONObject();
        petObject.put("id", id1);
        petObject.put("petName", petName1);
        petObject.put("age", age1);
        petObject.put("photo", null);
        petObject.put("kindOfAnimal", kindOfAnimal1);
        petObject.put("animalBreed", animalBreed1);
        petObject.put("shelterId", shelterId1);
        petObject.put("customer", null);


        when(petRepository.save(any(Pet.class))).thenReturn(PET1);
        when(petRepository.findById(idTest)).thenReturn(PET1);
        when(petRepository.existsById(id1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/pet")
                        .content(petObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.petName").value(petName1))
                .andExpect(jsonPath("$.age").value(age1))
                //.andExpect(jsonPath("$.photo").value(null))
                .andExpect(jsonPath("$.kindOfAnimal").value(kindOfAnimal1))
                .andExpect(jsonPath("$.animalBreed").value(animalBreed1))
                .andExpect(jsonPath("$.shelterId").value(shelterId1));
        //.andExpect(jsonPath("$.customer").value(null));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/pet")
                        .content(petObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.petName").value(petName1))
                .andExpect(jsonPath("$.age").value(age1))
                //.andExpect(jsonPath("$.photo").value(null))
                .andExpect(jsonPath("$.kindOfAnimal").value(kindOfAnimal1))
                .andExpect(jsonPath("$.animalBreed").value(animalBreed1))
                .andExpect(jsonPath("$.shelterId").value(shelterId1));
        //.andExpect(jsonPath("$.customer").value(null));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pet/" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.petName").value(petName1))
                .andExpect(jsonPath("$.age").value(age1))
                //.andExpect(jsonPath("$.photo").value(null))
                .andExpect(jsonPath("$.kindOfAnimal").value(kindOfAnimal1))
                .andExpect(jsonPath("$.animalBreed").value(animalBreed1))
                .andExpect(jsonPath("$.shelterId").value(shelterId1));
        // .andExpect(jsonPath("$.customer").value(null));


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/pet/" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(petRepository, times(1)).deleteById(id1);

    }
}