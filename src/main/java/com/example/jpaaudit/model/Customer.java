package com.example.jpaaudit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
public class Customer implements AuditableEntity{

    @GeneratedValue
    @Id
    private Long id;

    private final String uniqueName;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @LastModifiedBy
    private String lastModifiedUser;

    @CreatedBy
    private String createdBy;

    private final String firstName, lastName;
    private final Gender gender;

    public Customer(){
        this.firstName = null;
        this.lastName = null;
        this.gender = null;
        this.uniqueName = null;
    }

    public enum Gender{
        MALE,
        FEMALE
    }

}
