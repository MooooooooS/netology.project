package ru.netology.maksimmosiaev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.maksimmosiaev.OperationHistoryApiApplicationTest;
import ru.netology.maksimmosiaev.config.OperationProperties;
import ru.netology.maksimmosiaev.entity.Currency;
import ru.netology.maksimmosiaev.entity.Operation;

import java.util.List;

@AutoConfigureMockMvc
public class OperationControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private OperationProperties properties;

    @Test
    public void assertThatOperationControllerWorksRight() throws Exception {
        Operation operation = new Operation(1, 100, Currency.EUR, "Tea", 2);

        mvc.perform(MockMvcRequestBuilders.get("/api/operations/%d".formatted(operation.getCustomerId())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        mvc.perform(MockMvcRequestBuilders.post("/api/operations")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(operation)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(operation)));

        Thread.sleep(2L * properties.getSleepMilliSeconds());

        mvc.perform(MockMvcRequestBuilders.get("/api/operations/%d".formatted(operation.getCustomerId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(List.of(operation))));

        mvc.perform(MockMvcRequestBuilders.delete("/api/operations/%d".formatted(operation.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(operation)));

        mvc.perform(MockMvcRequestBuilders.delete("/api/operations/%d".formatted(operation.getId())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}