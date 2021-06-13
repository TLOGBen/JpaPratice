package com.example.jpapratice.dao.custom;

import com.example.jpapratice.dao.entity.Customer;

import java.util.List;

public interface ICustomerCustomDao {
    List<Customer> queryCustomerByNameList(List<String> nameList);
}
