package com.example.jpaaudit.configuartion;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Customer Service",
  description = "Service to expose customer service", version = "v1.0"))
public class OpenApiConfiguration { }
