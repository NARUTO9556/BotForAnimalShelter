package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Pet;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.service.PetService;
import pro.sky.telegrambot.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(ReportService.class);
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Report createReport(@Parameter(description = "Все параметры добавляемого отчета") @RequestBody Report report) {
        logger.info("Отчет добавлен");
        return reportService.createReport(report);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Report> findReportById(@Parameter(description = "ID отчета") @PathVariable Long id) {
       Report report = reportService.findReportById(id);
        if (report == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Отчет найден");
        return ResponseEntity.ok(report);
    }
    @PutMapping("/updateReport/{id}")
    public ResponseEntity<Report> updateReport(@Parameter(description = "ID изменяемого отчета") @PathVariable Long id,
                                         @Parameter(description = "Новые параметры отчета") @RequestBody Report report) {
        Report updateReport = reportService.updateReport(id, report);
        if (updateReport == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные отчета обновлены");
            return ResponseEntity.ok(updateReport);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Report> delete(@Parameter(description = "ID удаляемого отчета") @PathVariable Long id) {
        reportService.deleteReport(id);
        logger.info("Отчет удален");
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Report> allReports() {
        logger.info("Список всех отчетов");
        return reportService.findAllReports();
    }
}
