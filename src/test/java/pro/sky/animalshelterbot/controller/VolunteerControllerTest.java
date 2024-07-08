package pro.sky.animalshelterbot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.animalshelterbot.model.Volunteer;
import pro.sky.animalshelterbot.repository.VolunteerRepository;
import pro.sky.animalshelterbot.service.VolunteerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VolunteerController.class)
class VolunteerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VolunteerRepository volunteerRepository;

    @SpyBean
    private VolunteerService volunteerService;

    @Autowired
    private ObjectMapper objectMapper;

    private final long idTest = 1L;
    private final String nameTest = "testName";
    private final Long chatIdTest = 123L;
    private final String phoneNumberTest = "987654321";

    private final Volunteer volunteerTest = new Volunteer(idTest, nameTest, chatIdTest, phoneNumberTest);


    @Test
    void createVolunteerTest() throws Exception {
        JSONObject volunteerObject = createVolunteerJson();

        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteerTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/volunteer")
                        .content(volunteerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idTest))
                .andExpect(jsonPath("$.name").value(nameTest))
                .andExpect(jsonPath("$.chatId").value(chatIdTest))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumberTest));
        verify(volunteerRepository, only()).save(volunteerTest);
    }

    @Test
    void findVolunteerTest() throws Exception {

        when(volunteerRepository.findById(idTest)).thenReturn(volunteerTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/" + idTest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idTest))
                .andExpect(jsonPath("$.name").value(nameTest))
                .andExpect(jsonPath("$.chatId").value(chatIdTest))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumberTest));
        verify(volunteerRepository, only()).findById(idTest);
    }

    @Test
    void findVolunteerNotFoundTest() throws Exception {

        when(volunteerRepository.findById(idTest)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/" + idTest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(volunteerRepository, only()).findById(idTest);
    }

    @Test
    void updateVolunteerTest() throws Exception {

        JSONObject volunteerObject = createVolunteerJson();

        when(volunteerRepository.findById(any(long.class))).thenReturn(Optional.of(volunteerTest));
        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteerTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/volunteer")
                        .content(volunteerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(idTest))
                .andExpect(jsonPath("$.name").value(nameTest))
                .andExpect(jsonPath("$.chatId").value(chatIdTest))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumberTest));
        verify(volunteerRepository, times(1)).findById(any(long.class));
    }

    @Test
    void updateVolunteerNotFoundTest() throws Exception {

        JSONObject volunteerObject = createVolunteerJson();

        when(volunteerRepository.findById(any(long.class))).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/volunteer")
                        .content(volunteerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(volunteerRepository, times(1)).findById(any(long.class));
    }

    @Test
    void deleteVolunteerTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/volunteer/" + idTest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(volunteerRepository, times(1)).deleteById(idTest);
    }

    @Test
    void findAllVolunteersTest() throws Exception {
        List<Volunteer> volunteerListTest = Arrays.asList(volunteerTest, volunteerTest);

        when(volunteerRepository.findAll()).thenReturn(volunteerListTest);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(volunteerTest, volunteerTest))));
        verify(volunteerRepository, only()).findAll();
    }

    private JSONObject createVolunteerJson() {
        JSONObject volunteerObject = new JSONObject();
        volunteerObject.put("id", idTest);
        volunteerObject.put("name", nameTest);
        volunteerObject.put("chatId", chatIdTest);
        volunteerObject.put("phoneNumber", phoneNumberTest);
        return volunteerObject;
    }
}