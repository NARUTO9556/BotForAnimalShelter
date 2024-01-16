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
import pro.sky.telegrambot.entity.Customer;
import pro.sky.telegrambot.service.CustomerService;

import java.util.List;

/**
 * Контроллер сервиса клиентов.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Эндпоинт для добавления клиента.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавление нового клиента",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    })
    @PostMapping
    public Customer createCustomer(@Parameter(description = "Все параметры добавляемого клиента") @RequestBody Customer customer) {
        logger.info("Клиент добавлен");
        return customerService.createCustomer(customer);
    }

    /**
     * Эндпоинт для поиска клиента.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Получение информации о клиенте по ID",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@Parameter(description = "ID клиента") @PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Клиент найден");
        return ResponseEntity.ok(customer);
    }

    /**
     * Эндпоинт для получения клиента.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Изменение данных клиента",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    })
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@Parameter(description = "ID изменяемого клиента") @PathVariable Long id,
                                                   @Parameter(description = "Новые параметры клиента") @RequestBody Customer customer) {
        Customer updateCustomer = customerService.updateCustomer(id, customer);
        if (updateCustomer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные клиента обновлены");
            return ResponseEntity.ok(updateCustomer);
        }
    }

    /**
     * Эндпоинт для удаления клиента.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Удаление клиента",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@Parameter(description = "ID удаляемого клиента") @PathVariable Long id) {
        customerService.deleteCustomer(id);
        logger.info("Клиент удален");
        return ResponseEntity.ok().build();
    }

    /**
     * Эндпоинт для вывода всех клиентов.
     */
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех клиентов",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class)
                    )
            )
    })
    @GetMapping
    public List<Customer> allCustomers() {
        logger.info("Список всех клиентов");
        return customerService.findAllCustomers();
    }
}