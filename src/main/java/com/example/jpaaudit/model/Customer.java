package com.example.jpaaudit.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
