package pro.sky.telegrambot.service;


import pro.sky.telegrambot.entity.Report;

import java.util.List;

public interface ReportService {
    Report createReport(Report report);
    Report findReportById(long id);
    Report updateReport(Report updatedReport);
    void deleteCustomer(long id);
    List<Report> findAllReports();
}
