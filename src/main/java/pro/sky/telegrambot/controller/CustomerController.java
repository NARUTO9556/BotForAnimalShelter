package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.entity.Customer;
import pro.sky.telegrambot.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@Parameter(description = "Все параметры добавляемого клиента") @RequestBody Customer customer) {
        logger.info("Клиент добавлен");
        return customerService.createCustomer(customer);
    }
    @GetMapping("/{chatId}")
    public ResponseEntity<Customer> findCustomerById(@Parameter(description = "ID клиента") @PathVariable Long chatId) {
        Customer customer = customerService.findCustomerById(chatId);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        logger.info("Клиент найден");
        return ResponseEntity.ok(customer);
    }
    @PutMapping("/updateCustomer/{chatId}")
    public ResponseEntity<Customer> updateCustomer(@Parameter(description = "ID изменяемого клиента") @PathVariable Long chatId,
                                         @Parameter(description = "Новые параметры клиента") @RequestBody Customer customer) {
        Customer updateCustomer = customerService.updateCustomer(chatId, customer);
        if (updateCustomer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            logger.info("Данные клиента обновлены");
            return ResponseEntity.ok(updateCustomer);
        }
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Customer> delete(@Parameter(description = "ID удаляемого клиента") @PathVariable Long chatId) {
        customerService.deleteCustomer(chatId);
        logger.info("Клиент удален");
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public List<Customer> allCustomers() {
        logger.info("Список всех клиентов");
        return customerService.findAllCustomers();
    }
}
