package pro.sky.telegrambot.service;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Customer;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer findCustomerById(long id) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer updatedCustomer) {
        return null;
    }

    @Override
    public void deleteCustomer(long id) {

    }

    @Override
    public List<Customer> findAllCustomers() {
        return null;
    }
}
