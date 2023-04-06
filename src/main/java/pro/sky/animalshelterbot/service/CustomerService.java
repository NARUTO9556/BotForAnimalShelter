package pro.sky.animalshelterbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.animalshelterbot.model.Customer;
import pro.sky.animalshelterbot.repository.CustomerRepository;
;

import java.util.List;


/**
 * Класс сервиса для работы с {@link CustomerRepository} и сущностью {@link Customer}
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Метод сохраняет сущность {@link Customer} в БД, с помошью метода {@link CustomerRepository#save(Object)}
     *
     * @param customer данные о владельце животного
     * @return данные о сохраненном владельце животного
     */
    public Customer createCustomer(Customer customer) {
        logger.info("Was invoked - method  createCustomer");

        return customerRepository.save(customer);
    }

    /**
     * Метод ищет данные о владельце животного по его id.
     * Используется метод {@link CustomerRepository#findById(long)}
     *
     * @param id идентификатор владельца животного
     * @return данные о владельце животного
     */
    public Customer findCustomerById(long id) {
        logger.info("Was invoked method - getCustomer");

        return customerRepository.findById(id);
    }

    /**
     * Метод изменяет данные о владельце животного, заменяет сущность {@link Customer} по указанному в ней id.
     * Используется метод {@link CustomerRepository#save(Object)}
     *
     * @param updatedCustomer измененные данные владельца животного
     *      * @return измененные данные владельца животного, если владелец животного не найден возвращает {@code null}
     */
    public Customer updateCustomer(Customer updatedCustomer) {
        logger.info("Was invoked method - updateCustomer");

        if (customerRepository.findById(updatedCustomer.getId()).isPresent()) {
            return customerRepository.save(updatedCustomer);
        }
        return null;
    }

    /**
     * Метод удаляет сущность {@link Customer} по указанному id.
     * Используется метод {@link CustomerRepository#deleteById(Object)}
     *
     * @param id идентификатор удаляемого владельца животного
     */
    public void deleteCustomer(long id) {
        logger.info("Was invoked method - deleteCustomer");

        customerRepository.deleteById(id);
    }

    /**
     * Метод возвращает список всех владельцев животных в БД.
     *
     * @return список всех владельцев животных
     */
    public List<Customer> findAllCustomers() {
        logger.info("Was invoked method - findAllCustomers");

        return customerRepository.findAll();
    }
}
