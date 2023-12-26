package ru.netology.maksimmosiaev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.maksimmosiaev.OperationHistoryApiApplicationTest;
import ru.netology.maksimmosiaev.entity.Customer;
import ru.netology.maksimmosiaev.entity.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void assertThatControllerWorksRight() throws Exception {
        List<CustomerDTO> customers = new ArrayList<>(List.of(
                new CustomerDTO(1, "Spring"), new CustomerDTO(2, "Boot")
        ));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Boot"));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Spring"));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(customers)));

        Customer henry = new Customer(3, "Anna", "pass");
        mvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(henry)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(String.valueOf(henry.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(henry.getName()));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers/10"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        customers.add(CustomerDTO.fromCustomer(henry));
        mvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(customers)));

    }
}
