package com.example;

import com.example.jpaaudit.model.Customer;
import com.example.jpaaudit.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaauditApplication {

    private final CustomerRepository customerRepository;

    @Value("${auth.mode}")
    private String authMode;

    public static void main(String[] args) {
        SpringApplication.run(JpaauditApplication.class, args);
    }

    public @PostConstruct
    void init() {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT")); //Setting default time zone to GMT so that Last modified time will be shown in GMT in response header.

        if("disable".equals(authMode)){
            //URL encoding for # is %23 . So, if firstname is in the URL, #ajmal below should change to %23ajmal in the URL
            customerRepository.save(new Customer("#Ajmal", "Cholassery", Customer.Gender.MALE));
            customerRepository.save(new Customer("Shadiya", "Kundi", Customer.Gender.FEMALE));
        }

    }

}
