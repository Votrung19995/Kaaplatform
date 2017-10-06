package com.kaa.platform.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kaa.platform.models.Customer;

@Repository
public interface CustomerService extends JpaRepository <Customer, Integer> {
     
}
