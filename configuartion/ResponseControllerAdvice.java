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

import javax.persistence.EntityListeners;
import java.lang.annotation.Annotation;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<AuditableEntity> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        List<Annotation> annotations = Arrays.asList(methodParameter.getParameterType().getAnnotations());

        return annotations.stream().anyMatch(type -> type.annotationType().equals(EntityListeners.class));

    }

    @Override
    public AuditableEntity beforeBodyWrite(AuditableEntity auditableEntity, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        HttpHeaders headers = serverHttpResponse.getHeaders();
        headers.add("LastModifiedBy", auditableEntity.getLastModifiedUser());
        headers.setLastModified(auditableEntity.getLastModifiedDate().toEpochSecond(ZoneOffset.UTC)*1000);

        return auditableEntity;
    }
}
