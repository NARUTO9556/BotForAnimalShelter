package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.repository.ReportRepository;

import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report createReport(Report report) {
        logger.info("Method createReport has been run");
        return reportRepository.save(report);
    }

    @Override
    public Report findReportById(long id) {
        logger.info("Method findReportById has been run");
        return reportRepository.findById(id);
    }

    @Override
    public Report updateReport(Long id, Report updatedReport) {
        logger.info("Method updateReport has been run");
        Report report = findReportById(id);
        if (report != null) {
            report.setChatId(updatedReport.getChatId());
            report.setTextReport(updatedReport.getTextReport());
            report.setDateReport(updatedReport.getDateReport());
            report.setFilePath(updatedReport.getFilePath());
            report.setFileSize(updatedReport.getFileSize());
            report.setMediaType(updatedReport.getMediaType());
            report.setPhoto(updatedReport.getPhoto());
            return reportRepository.save(report);
        } else {
            return null;
        }
    }

    @Override
    public void deleteReport(long id) {
        logger.info("Method deleteReport has been run");
        reportRepository.deleteById(id);
    }

    @Override
    public List<Report> findAllReports() {
        logger.info("Method findAllReports has been run");
        return reportRepository.findAll();
    }
}
