package ru.netology.maksimmosiaev.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.netology.maksimmosiaev.entity.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();

    public List<Customer> getCustomers() {
        return storage;
    }

    public Customer getCustomer(int index) {
        return storage.get(index);
    }

    public void addCustomer(Customer customer) {
        storage.add(customer);
    }

    @PostConstruct
    public void initStorage() {
        addCustomer(new Customer(1, "Spring", "pass"));
        addCustomer(new Customer(2, "Boot", "pass"));
    }
}
