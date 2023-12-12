package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Customer;
import pro.sky.telegrambot.repository.CustomerRepository;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {
    private  final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("Method createCustomer has been run");
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerById(long id) {
        logger.info("Method findCustomerById has been run");
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        logger.info("Method updateCustomer has been run");
        Customer customer = findCustomerById(id);
        if (customer == null) {
            return null;
        }
        else {
            customer.setNameCustomer(updatedCustomer.getNameCustomer());
            customer.setChatId(updatedCustomer.getChatId());
            customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            return customerRepository.save(customer);
        }
    }

    @Override
    public void deleteCustomer(long id) {
        logger.info("Method deleteCustomer has been run");
            customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
