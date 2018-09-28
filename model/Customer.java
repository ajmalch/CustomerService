package com.example.jpaaudit.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
public class Customer {

    @GeneratedValue
    @Id
    private Long id;

    private final String uniqueName;

    @LastModifiedDate
    private LocalDate lastModifiedDate;

    private final String firstName, lsatName;
    private final Gender gender;

    public Customer(){
        this.firstName = null;
        this.lsatName = null;
        this.gender = null;
        this.uniqueName = null;
    }

    public static enum Gender{
        MALE,
        FEMALE;
    }

}
