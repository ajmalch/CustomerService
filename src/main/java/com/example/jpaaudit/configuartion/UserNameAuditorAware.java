package com.example.jpaaudit.configuartion;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserNameAuditorAware implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {

        /*
         This should be something like SecurityContextHolder.getContext().getAuthentication().getName();
         As security is not implemented yet, defaulting user to Ajmal
         */
        return Optional.of("Ajmal");
    }
}
