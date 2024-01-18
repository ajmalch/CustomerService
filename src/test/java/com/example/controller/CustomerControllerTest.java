package com.example.controller;

import com.example.jpaaudit.model.Customer;
import com.example.jpaaudit.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void getCustomerByFirstNameShouldCallCustomerRepository() {

        when(customerRepository.findCustomerByFirstName(anyString()))
                .thenReturn(Optional.of(new Customer("Ajmal"
                        , "Cholassery"
                        , Customer.Gender.MALE)));

        customerController.getCustomerByFirstName("ajmal");
        verify(customerRepository, times(1))
                .findCustomerByFirstName("ajmal");

    }

    @Test
    void getCustomerByFirstNameShouldThrowResourceNotFoundException() {

        when(customerRepository.findCustomerByFirstName(anyString()))
                .thenReturn(Optional.empty());

        final var resourceNotFoundException = assertThrows(ResourceNotFoundException.class,
                () -> customerController.getCustomerByFirstName("ajmal"));

        assertEquals(
                "Customer with firstname ajmal not found"
                , resourceNotFoundException.getMessage()
        );

    }


}