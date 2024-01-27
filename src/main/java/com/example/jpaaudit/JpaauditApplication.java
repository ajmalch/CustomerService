package com.example.jpaaudit;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class JpaauditApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaauditApplication.class, args);
    }
}
