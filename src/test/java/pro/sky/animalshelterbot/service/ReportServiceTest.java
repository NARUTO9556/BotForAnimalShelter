package pro.sky.animalshelterbot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animalshelterbot.model.Pet;
import pro.sky.animalshelterbot.model.Report;
import pro.sky.animalshelterbot.repository.ReportRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;


    private final Long id1 = 1L;
    private final Long chatId1 = 12345L;
    private final String textReport1 = "testReport1";
    private final LocalDateTime dateReport1 = LocalDateTime.now();
    private final String filePath1 = "filePath1";
    private final long fileSize1 = 100;
    private final String mediaType1 = "mediaType1";
    private final byte[] photo1 = {1, 2, 3,};
    private final Pet pet1 = new Pet();

    private final Long id2 = 2L;
    private final Long chatId2 = 54321L;
    private final String textReport2 = "testReport2";
    private final LocalDateTime dateReport2 = LocalDateTime.now();
    private final String filePath2 = "filePath2";
    private final long fileSize2 = 200;
    private final String mediaType2 = "mediaType2";
    private final byte[] photo2 = {4, 5, 6};
    private final Pet pet2 = new Pet();

    private final Report REPORT1 = new Report(id1, chatId1, textReport1, dateReport1, filePath1, fileSize1, mediaType1, photo1, pet1);
    private final Report REPORT2 = new Report(id2, chatId2, textReport2, dateReport2, filePath2, fileSize2, mediaType2, photo2, pet2);

    private final List<Report> listOfReports = List.of(REPORT1, REPORT2);
    private final long idTest = 1;

    @Test
    void createReportTest() {
        when(reportRepository.save(any(Report.class))).thenReturn(REPORT1);
        Report expected = REPORT1;
        Report actual = reportService.create(REPORT1.getChatId(), REPORT1.getTextReport(), REPORT1.getDateReport());
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getTextReport(), actual.getTextReport());
        assertEquals(expected.getDateReport(), actual.getDateReport());
        assertEquals(expected.getFilePath(), actual.getFilePath());
        assertEquals(expected.getFileSize(),actual.getFileSize());
        assertEquals(expected.getMediaType(), actual.getMediaType());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getPet(), actual.getPet());
        verify(reportRepository, only()).save(any(Report.class));
    }


    @Test
    void findReportById() {
        when(reportRepository.findById(idTest)).thenReturn(REPORT1);
        Report expected = REPORT1;
        Report actual = reportService.findReportById(idTest);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getTextReport(), actual.getTextReport());
        assertEquals(expected.getDateReport(), actual.getDateReport());
        assertEquals(expected.getFilePath(), actual.getFilePath());
        assertEquals(expected.getFileSize(),actual.getFileSize());
        assertEquals(expected.getMediaType(), actual.getMediaType());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getPet(), actual.getPet());
        verify(reportRepository, only()).findById(idTest);
    }


    @Test
    void deleteReportTest() {
        reportService.deleteReport(idTest);
        verify(reportRepository, times(1)).deleteById(idTest);
    }


    @Test
    void findAllReportTest() {
        when(reportRepository.findAll()).thenReturn(listOfReports);
        Collection<Report> expected = listOfReports;
        Collection<Report> actual = reportService.findAllReports();
        assertEquals(expected, actual);
        verify(reportRepository, only()).findAll();
    }
}