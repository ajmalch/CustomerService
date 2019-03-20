package com.example.jpaaudit;

import com.example.jpaaudit.model.Customer;
import com.example.jpaaudit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
@RestController
public class JpaauditApplication {

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaauditApplication.class, args);
    }

    public @PostConstruct
    void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT")); //Setting default time zone to GMT so that Last modified time will be shown in GMT in response header.

        //URL encoding for # is %23 . So, if firstname is in the URL, #ajmal below should change to %23ajmal in the URL
        customerRepository.save(new Customer("ajmal", "#Ajmal", "Cholassery", Customer.Gender.MALE));
        customerRepository.save(new Customer("shadiya", "Shadiya", "Kundi", Customer.Gender.FEMALE));

    }

    @GetMapping("clients")
    public Customer getCustomer() {
        return customerRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @GetMapping("/customer/{firstName}")
    public Customer getCustomerByFirstName(@PathVariable String firstName) {

        return customerRepository.findCustomerByFirstName(firstName)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }
}
