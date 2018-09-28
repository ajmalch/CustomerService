package com.example.jpaaudit;

import com.example.jpaaudit.model.Customer;
import com.example.jpaaudit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
@RestController
public class JpaauditApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaauditApplication.class, args);
    }

    @Autowired
    CustomerRepository customerRepository;

    public @PostConstruct void init(){

        customerRepository.save( new Customer("ajmal","Ajmal","Cholassery", Customer.Gender.MALE));
        customerRepository.save( new Customer("shadiya","Shadiya","Kundi", Customer.Gender.FEMALE));

    }

    @GetMapping("clients")
    public Customer getCustomer(){
        return customerRepository.findById(1L)
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
    }


}
