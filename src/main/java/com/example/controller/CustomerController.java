package com.example.controller;

import com.example.jpaaudit.model.Customer;
import com.example.jpaaudit.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/{firstName}")
    public Customer getCustomerByFirstName(@PathVariable String firstName) {

        return customerRepository.findCustomerByFirstName(firstName)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer with firstname "+firstName+" not found"));
    }

}
