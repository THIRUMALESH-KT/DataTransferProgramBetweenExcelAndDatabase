package com.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.entity.customer;

@Repository
public interface CustomerRepository extends JpaRepository<customer, Long> {

}
