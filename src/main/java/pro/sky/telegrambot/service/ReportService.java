package pro.sky.telegrambot.service;


import pro.sky.telegrambot.entity.Report;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    void createReport(long chatId, String textReport, LocalDateTime dateReport);
    Report findReportById(long id);
    void deleteReport(long id);
    List<Report> findAllReports();
}
