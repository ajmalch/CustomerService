package com.example.jpaaudit.configuartion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth")
@Data
@Validated
public class AuthConfiguration {


    @NotBlank(message = "mode should be one of basic, oidc or disable")
    @Pattern(regexp = "basic|oidc|disable", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String mode;

}
