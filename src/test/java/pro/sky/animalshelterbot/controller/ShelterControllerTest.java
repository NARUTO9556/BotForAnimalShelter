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
import pro.sky.animalshelterbot.model.Shelter;
import pro.sky.animalshelterbot.repository.ShelterRepository;
import pro.sky.animalshelterbot.service.ShelterService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterController.class)
public class ShelterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterRepository shelterRepository;

    @SpyBean
    private ShelterService shelterService;

    @InjectMocks
    private ShelterController shelterController;

    private final Long id1 = 1L;
    private final String name1 = "testShelter1";
    private final String address1 = "address1";
    private final String shelterSchedule1 = "shelterSchedule1";
    private final String securityInfo1 = "securityInfo1";
    private final String info1 = "info1";
    private final byte[] map1 = null;

    private final Shelter SHELTER1 = new Shelter(id1, name1, address1, shelterSchedule1, securityInfo1, info1, map1);

    private final long idTest = 1;




    @Test
    public void testShelterController() throws Exception {
        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", id1);
        shelterObject.put("name", name1);
        shelterObject.put("address", address1);
        shelterObject.put("shelterSchedule", shelterSchedule1);
        shelterObject.put("securityInfo", securityInfo1);
        shelterObject.put("info", info1);
        shelterObject.put("map", map1);


        when(shelterRepository.save(any(Shelter.class))).thenReturn(SHELTER1);
        when(shelterRepository.findById(idTest)).thenReturn(SHELTER1);
        when(shelterRepository.existsById(id1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/shelter")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.address").value(address1))
                .andExpect(jsonPath("$.shelterSchedule").value(shelterSchedule1))
                .andExpect(jsonPath("$.securityInfo").value(securityInfo1))
                .andExpect(jsonPath("$.info").value(info1))
                .andExpect(jsonPath("$.map").value(map1));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/shelter")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.address").value(address1))
                .andExpect(jsonPath("$.shelterSchedule").value(shelterSchedule1))
                .andExpect(jsonPath("$.securityInfo").value(securityInfo1))
                .andExpect(jsonPath("$.info").value(info1))
                .andExpect(jsonPath("$.map").value(map1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelter/" + idTest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.address").value(address1))
                .andExpect(jsonPath("$.shelterSchedule").value(shelterSchedule1))
                .andExpect(jsonPath("$.securityInfo").value(securityInfo1))
                .andExpect(jsonPath("$.info").value(info1))
                .andExpect(jsonPath("$.map").value(map1));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/shelter/" + idTest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(shelterRepository, times(1)).deleteById(id1);
    }
}