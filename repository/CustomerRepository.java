package com.example.jpaaudit.repository;

import com.example.jpaaudit.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findCustomerByFirstName(String firstName);

}
