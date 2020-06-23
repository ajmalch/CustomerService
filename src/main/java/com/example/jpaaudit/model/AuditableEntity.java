package com.example.jpaaudit.model;

import java.time.LocalDateTime;

public interface AuditableEntity {

    LocalDateTime getLastModifiedDate();
    String getLastModifiedUser();

}
