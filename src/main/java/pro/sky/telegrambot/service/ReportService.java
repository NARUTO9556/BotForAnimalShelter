package pro.sky.telegrambot.service;


import pro.sky.telegrambot.entity.Report;

import java.util.List;

public interface ReportService {
    Report createReport(Report report);
    Report findReportById(long id);
    Report updateReport(Long id, Report updatedReport);
    void deleteReport(long id);
    List<Report> findAllReports();
}
