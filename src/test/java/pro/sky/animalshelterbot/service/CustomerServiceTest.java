package pro.sky.animalshelterbot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animalshelterbot.model.Customer;
import pro.sky.animalshelterbot.repository.CustomerRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    static final Long id1 = 1L;
    static final String name1 = "testCustomer1";
    static final Long chatId1 = 12345L;
    static final String phoneNumber1 = "89012345678";


    static final Long id2 = 2L;
    static final String name2 = "testCustomer2";
    static final Long chatId2 = 54321L;
    static final String phoneNumber2 = "89087654321";


    static final Customer CUSTOMER1 = new Customer(id1, name1, chatId1, phoneNumber1);
    static final Customer CUSTOMER2 = new Customer(id2, name2, chatId2, phoneNumber2);

    static final List<Customer> listOfCustomers = List.of(CUSTOMER1, CUSTOMER2);
    static final long idTest = 1;


    @Test
    void createCustomerTest() {
        when(customerRepository.save(CUSTOMER1)).thenReturn(CUSTOMER1);
        Customer expected = CUSTOMER1;
        Customer actual = customerService.createCustomer(CUSTOMER1);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        verify(customerRepository, only()).save(CUSTOMER1);
    }


    @Test
    void findCustomerById() {
        when(customerRepository.findById(idTest)).thenReturn(CUSTOMER1);
        Customer expected = CUSTOMER1;
        Customer actual = customerService.findCustomerById(idTest);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        verify(customerRepository, only()).findById(idTest);
    }

    @Test
    void updateCustomer() {
        when(customerRepository.existsById(CUSTOMER2.getId())).thenReturn(true);
        when(customerRepository.save(CUSTOMER2)).thenReturn(CUSTOMER2);

        Customer expected = CUSTOMER2;
        Customer actual = customerService.updateCustomer(CUSTOMER2);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        verify(customerRepository, times(1)).save(CUSTOMER2);
        verify(customerRepository, times(1)).existsById(CUSTOMER2.getId());

    }

    @Test
    void deleteCustomerTest() {
        customerService.deleteCustomer(idTest);
        verify(customerRepository, times(1)).deleteById(idTest);
    }


    @Test
    void findAllCustomerTest() {
        when(customerRepository.findAll()).thenReturn(listOfCustomers);
        Collection<Customer> expected = listOfCustomers;
        Collection<Customer> actual = customerService.findAllCustomers();
        assertEquals(expected, actual);
        verify(customerRepository, only()).findAll();
    }
}