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

import java.lang.reflect.AnnotatedType;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice<AuditableEntity> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {

        List<AnnotatedType> interfaces = Arrays.asList(methodParameter.getParameterType().getAnnotatedInterfaces());

        return interfaces.stream().anyMatch(interfaceType -> interfaceType.getType().getTypeName().equals("com.example.jpaaudit.model.AuditableEntity"));

    }

    @Override
    public AuditableEntity beforeBodyWrite(AuditableEntity auditableEntity, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        HttpHeaders headers = serverHttpResponse.getHeaders();
        //TODO getLastModifiedUser call null pointer exception handling
        if(auditableEntity != null){

            if(auditableEntity.getLastModifiedBy() != null){
                headers.add("LastModifiedBy", auditableEntity.getLastModifiedBy());
            }
            if(auditableEntity.getLastModifiedDate() != null){
                headers.setLastModified(auditableEntity.getLastModifiedDate().toEpochSecond(ZoneOffset.UTC) * 1000);
            }
            if(auditableEntity.getCreatedBy() != null){
                headers.add("CreatedBy" , auditableEntity.getCreatedBy());
            }
            if (auditableEntity.getCreatedDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                headers.add("Created",auditableEntity.getCreatedDate().atOffset(ZoneOffset.UTC).format(formatter));
            }
        }

        return auditableEntity;
    }
}
