package com.example.jpaaudit.configuartion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserNameAuditorAware implements AuditorAware<String> {

    @Value("${auth.mode}")
    private String authMode;

    @Override
    public Optional<String> getCurrentAuditor() {

        /*
         This should be something like SecurityContextHolder.getContext().getAuthentication().getName();
         As security is not implemented yet, defaulting user to Ajmal
         */
        return "disable".equals(authMode)? Optional.of("Ajmal"):
                Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
