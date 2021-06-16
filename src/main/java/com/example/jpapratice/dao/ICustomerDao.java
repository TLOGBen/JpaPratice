package com.example.jpapratice.dao;

import com.example.jpapratice.dao.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, Long> {
    List<Customer> queryCustomerByNameIsLike(String name);
}
