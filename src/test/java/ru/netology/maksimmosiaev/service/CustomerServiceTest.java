package ru.netology.maksimmosiaev.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.maksimmosiaev.OperationHistoryApiApplicationTest;
import ru.netology.maksimmosiaev.entity.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void assertThatCustomerServiceWorksRight() {
        int id = 1;
        String name = "Dima";
        String password = "pass";
        Customer peter = new Customer(id, name, password);

        assertEquals(new Customer(2, "Boot", "pass"), customerService.getCustomer(1));
        assertEquals(new Customer(1, "Spring", "pass"), customerService.getCustomer(0));

        customerService.addCustomer(peter);
        Customer customer = customerService.getCustomer(2);

        assertEquals(peter, customer);
        assertEquals(id, customer.getId());
        assertEquals(password, customer.getPassword());
        assertEquals(name, customer.getName());
    }
}