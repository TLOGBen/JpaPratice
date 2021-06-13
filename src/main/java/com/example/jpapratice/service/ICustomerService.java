package com.example.jpapratice.service;

import com.example.jpapratice.dao.entity.Customer;

public interface ICustomerService {
    void queryInTrancation(Customer customer);

    void initCustomerData();
}
