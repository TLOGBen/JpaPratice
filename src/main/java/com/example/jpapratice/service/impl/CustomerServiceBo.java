package com.example.jpapratice.service.impl;

import com.example.jpapratice.dao.ICustomerDao;
import com.example.jpapratice.dao.entity.Customer;
import com.example.jpapratice.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceBo implements ICustomerService {
    @Autowired
    ICustomerDao customerDao;

    @Override
    @Transactional
    public void queryInTrancation(Customer customer) {
        Customer entity = customerDao.findById(customer.getId()).get();
        entity.setName("Change ?");
    }
}
