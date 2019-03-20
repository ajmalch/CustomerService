package com.example.jpaaudit.configuartion;

import com.example.jpaaudit.model.AuditableEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.ZoneOffset;

@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<AuditableEntity> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true; //we are supporting all entities implementing this interface
    }

    @Override
    public AuditableEntity beforeBodyWrite(AuditableEntity auditableEntity, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        HttpHeaders headers = serverHttpResponse.getHeaders();
        headers.add("LastModifiedBy", auditableEntity.getLastModifiedUser());
        headers.setLastModified(auditableEntity.getLastModifiedDate().toEpochSecond(ZoneOffset.UTC)*1000);

        return auditableEntity;
    }
}
