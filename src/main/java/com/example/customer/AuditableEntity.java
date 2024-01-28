package com.example.customer;

import java.time.LocalDateTime;

public interface AuditableEntity {

    LocalDateTime getLastModifiedDate();
    String getLastModifiedBy();

    String getCreatedBy();

    LocalDateTime getCreatedDate();
}
