package com.example.controller;

import com.example.jpaaudit.model.Customer;
import com.example.jpaaudit.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        when(customerRepository.findCustomerByFirstName(anyString()))
                .thenReturn(Optional.of(new Customer("ajmal"
                        , "Ajmal"
                        , "Cholassery"
                        , Customer.Gender.MALE)));
    }

    @Test
    void getCustomerByFirstNameShouldCallCustomerRepository() {
        final var ajmal = customerController.getCustomerByFirstName("ajmal");

        verify(customerRepository, times(1))
                .findCustomerByFirstName(eq("ajmal"));

    }
}