package com.example.jpaaudit.customer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor(force = true)
@Table(schema = "Customer")
@RequiredArgsConstructor
public class Customer implements AuditableEntity{

    @Column(name = "FIRST_NAME")
    private final String firstName;
    @Column(name = "LAST_NAME")
    private final String lastName;
    @Column(name = "GENDER")
    private final Gender gender;
    @SequenceGenerator(name="customer_id_seq",
            sequenceName="customer_id_sequence",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="customer_id_seq")
    @Id
    @Column(name = "CUSTOMER_ID")
    private Long id;
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;
    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;


    public enum Gender{
        MALE,
        FEMALE
    }

}
