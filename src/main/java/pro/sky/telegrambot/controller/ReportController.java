package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.service.ReportService;

import java.util.List;

/**
 * Контроллер сервиса отчетов.
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Эндпоинт для добавления отчета.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового отчета",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Report.class)
                    )
            )
    })
    @PostMapping
    public Report createReport(@Parameter(description = "Все параметры добавляемого отчета") @RequestBody Report report) {
        logger.info("Отчет добавлен");
        return reportService.createReport(report);
    }

    /**
     * Эндпоинт для получения отчета.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации об отчете по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Report.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Report> findReportById(@Parameter(description = "ID отчета") @PathVariable Long id) {
        Report report = reportService.findReportById(id);
        if (report == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Отчет найден");
        return ResponseEntity.ok(report);
    }

    /**
     * Эндпоинт для обновления отчета.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных отчета",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Report.class)
                    )
            )
    })
    @PutMapping("/updateReport/{id}")
    public ResponseEntity<Report> updateReport(@Parameter(description = "ID изменяемого отчета") @PathVariable Long id,
                                               @Parameter(description = "Новые параметры отчтета") @RequestBody Report report) {
        Report updatedReport = reportService.updateReport(id, report);
        if (updatedReport == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные отчета обновлены");
            return ResponseEntity.ok(updatedReport);
        }
    }

    /**
     * Эндпоинт для удаления отчета.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление отчета",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Report.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Report> deleteReport(@Parameter(description = "ID удаляемого отчета") @PathVariable Long id) {
        reportService.deleteCustomer(id);
        logger.info("Отчет удален");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода всех отчетов.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех отчетов",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Report.class)
                    )
            )
    })
    @GetMapping
    public List<Report> allReports() {
        logger.info("Список всех отчетов");
        return reportService.findAllReports();
    }
}
