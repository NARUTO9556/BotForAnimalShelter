package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.repository.ReportRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Класс сервиса для работы с {@link ReportRepository} и сущностью {@link Report}
 */
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    /**
     * Метод создает экземпляр класса {@link Report} и устанавливает значение его полей согласно значениям
     * параметров метода, после этого сохраняет сущность в БД
     * с помошью метода {@link ReportRepository#save(Object)}.
     *
     * @param chatId идентификатор чата, не может быть {@code null}
     * @param textReport текст  отчета, не может быть {@code null}
     * @param dateReport дата и время отправки отчета, не может быть {@code null}
     */
    @Override
    public void createReport(long chatId, String textReport, LocalDateTime dateReport) {
        logger.info("Was invoked - method createReport");
        Report report = new Report();
        report.setChatId(chatId);
        report.setTextReport(textReport);
        report.setDateReport(dateReport.truncatedTo(ChronoUnit.MINUTES));
        reportRepository.save(report);
    }

    /**
     * Метод ищет данные об отчете по его id.
     * Используется метод {@link ReportRepository#findById(long)}
     *
     * @param id идентификатор отчета
     * @return данные об отчете
     */
    @Override
    public Report findReportById(long id) {
        logger.info("Was invoked - method findReportById");
        return reportRepository.findById(id);
    }
    @Override
    public void deleteReport(long id) {
        logger.info("Was invoked - method deleteCustomer");
        reportRepository.deleteById(id);

    }

    @Override
    public List<Report> findAllReports() {
        logger.info("Was invoked - method createReport");
        return reportRepository.findAll();
    }
}
