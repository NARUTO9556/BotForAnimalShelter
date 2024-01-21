package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.entity.Report;
import pro.sky.telegrambot.service.ReportService;

import java.util.List;

/**
 * Класс для обработки запросов от клиента и возвращения результатов,
 * работает с сущностью {@link ReportService}.
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(
            summary = "Поиск отчета по id.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Информация о найденном отчете",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Report.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Отчет с данным id не найден"
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Report> findReportById(@Parameter(description = "ID отчета") @PathVariable Long id) {
        Report report = reportService.findReportById(id);
        if (report == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Отчет найден");
        return ResponseEntity.ok(report);
    }
    @Operation(
            summary = "Получить список всех отчетов",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список всех отчетов",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = Report.class))
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<List<Report>> findAllReports() {
        return ResponseEntity.ok(reportService.findAllReports());
    }
}
