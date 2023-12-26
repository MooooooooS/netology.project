package ru.netology.maksimmosiaev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.maksimmosiaev.entity.Customer;
import ru.netology.maksimmosiaev.entity.dto.CustomerDTO;
import ru.netology.maksimmosiaev.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDTO postCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);

        return CustomerDTO.fromCustomer(customer);
    }

    @GetMapping
    public Iterable<CustomerDTO> getCustomers() {
        return customerService.getCustomers()
                .stream()
                .map(CustomerDTO::fromCustomer)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomers()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .map(customer -> new ResponseEntity<>(CustomerDTO.fromCustomer(customer), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
