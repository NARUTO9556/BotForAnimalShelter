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
import pro.sky.animalshelterbot.model.Report;
import pro.sky.animalshelterbot.repository.ReportRepository;
import pro.sky.animalshelterbot.service.ReportService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportRepository reportRepository;

    @SpyBean
    private ReportService reportService;

    @InjectMocks
    private ReportController reportController;

    static final Long id1 = 1L;
    static final Long chatId1 = 12345L;
    static final String textReport1 = "testReport1";
    //static final LocalDateTime dateReport1;
    static final String filePath1 = "filePath1";
    static final long fileSize1 = 100;
    static final String mediaType1 = "mediaType1";


    static final Report REPORT1 = new Report(id1, chatId1, textReport1, null, filePath1, fileSize1, mediaType1, null, null);

    static final long idTest = 1;


    @Test
    public void testReportController() throws Exception {
        JSONObject ReportObject = new JSONObject();
        ReportObject.put("id", id1);
        ReportObject.put("chatId", chatId1);
        ReportObject.put("textReport", textReport1);
        ReportObject.put("filePath", filePath1);
        ReportObject.put("fileSize", fileSize1);
        ReportObject.put("mediaType", mediaType1);


        when(reportRepository.save(any(Report.class))).thenReturn(REPORT1);
        when(reportRepository.findById(idTest)).thenReturn(REPORT1);
        when(reportRepository.existsById(id1)).thenReturn(true);

        /*
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/report")
                        .content(ReportObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.chatId").value(chatId1))
                .andExpect(jsonPath("$.textReport").value(textReport1))
                .andExpect(jsonPath("$.filePath").value(filePath1))
                .andExpect(jsonPath("$.fileSize").value(fileSize1))
                .andExpect(jsonPath("$.mediaType").value(mediaType1));


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/report")
                        .content(ReportObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.chatId").value(chatId1))
                .andExpect(jsonPath("$.textReport").value(textReport1))
                .andExpect(jsonPath("$.filePath").value(filePath1))
                .andExpect(jsonPath("$.fileSize").value(fileSize1))
                .andExpect(jsonPath("$.mediaType").value(mediaType1));

         */

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/report/" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.chatId").value(chatId1))
                .andExpect(jsonPath("$.textReport").value(textReport1))
                .andExpect(jsonPath("$.filePath").value(filePath1))
                .andExpect(jsonPath("$.fileSize").value(fileSize1))
                .andExpect(jsonPath("$.mediaType").value(mediaType1));


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/report/" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(reportRepository, times(1)).deleteById(id1);

    }
}