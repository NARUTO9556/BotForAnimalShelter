package pro.sky.animalshelterbot.controller;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.animalshelterbot.model.Customer;
import pro.sky.animalshelterbot.repository.CustomerRepository;
import pro.sky.animalshelterbot.service.CustomerService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CustomerRepository customerRepository;

    @SpyBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    static final Long id1 = 1L;
    static final String name1 = "testCustomer1";
    static final Long chatId1 = 12345L;
    static final String phoneNumber1 = "89012345678";
    static final long idTest = 1;

    static final Customer CUSTOMER1 = new Customer(id1, name1, chatId1, phoneNumber1);


    @Test
    public void testCustomerController() throws Exception {
        JSONObject customerObject = new JSONObject();
        customerObject.put("id", id1);
        customerObject.put("name", name1);
        customerObject.put("chatId", chatId1);
        customerObject.put("phoneNumber", phoneNumber1);


        when(customerRepository.save(any(Customer.class))).thenReturn(CUSTOMER1);
        when(customerRepository.findById(idTest)).thenReturn(CUSTOMER1);
        when(customerRepository.existsById(id1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/customer")
                        .content(customerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.chatId").value(chatId1))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber1));
        verify(customerRepository, only()).save(CUSTOMER1);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/customer")
                        .content(customerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.chatId").value(chatId1))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber1));


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.name").value(name1))
                .andExpect(jsonPath("$.chatId").value(chatId1))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber1));
//        verify(customerRepository, only()).findById(idTest);


        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/customer/" + id1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerRepository, times(1)).deleteById(id1);

    }


}