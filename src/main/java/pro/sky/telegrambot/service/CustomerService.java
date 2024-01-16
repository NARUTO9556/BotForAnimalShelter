package pro.sky.telegrambot.service;

import pro.sky.telegrambot.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer findCustomerById(long id);
    Customer updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(long id);
    List<Customer> findAllCustomers();
}
