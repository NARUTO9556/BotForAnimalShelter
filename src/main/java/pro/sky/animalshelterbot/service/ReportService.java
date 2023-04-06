package pro.sky.animalshelterbot.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.animalshelterbot.model.Report;
import pro.sky.animalshelterbot.repository.ReportRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Класс сервиса для работы с {@link ReportRepository} и сущностью {@link Report}
 */
@Service
public class ReportService {
    private final ReportRepository reportRepository;

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);


    public ReportService(ReportRepository reportRepository) {
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
    @Transactional
    public void create(long chatId, String textReport, LocalDateTime dateReport
    //паратметры фото - путь к файлу, размер файла, тип данных, массив байт
    ) {
        logger.info("Method create has been run");

        Report report = new Report();
        report.setChatId(chatId);
        report.setTextReport(textReport);
        report.setDateReport(dateReport.truncatedTo(ChronoUnit.MINUTES));
        //реализовать сохранение фото из отчета
        reportRepository.save(report);
    }

    /**
     * Метод ищет данные об отчете по его id.
     * Используется метод {@link ReportRepository#findById(long)}
     *
     * @param id идентификатор отчета
     * @return данные об отчете
     */
    public Report findReportById(long id) {
        logger.info("Was invoked method - getreport");

        return reportRepository.findById(id);
    }



    /**
     * Метод удаляет сущность {@link Report} по указанному id.
     * Используется метод {@link ReportRepository#deleteById(Object)}
     *
     * @param id идентификатор удаляемого отчета
     */
    public void deleteReport(long id) {
        logger.info("Was invoked method - deletereport");

        reportRepository.deleteById(id);
    }

    /**
     * Метод возвращает список всех отчетов в БД.
     *
     * @return список всех отчетов
     */
    public List<Report> findAllReports() {
        logger.info("Was invoked method - findAllReports");

        return reportRepository.findAll();
    }

}
