package com.example.jpapratice.service.impl;

import com.example.jpapratice.dao.ICustomerDao;
import com.example.jpapratice.dao.IPhoneDao;
import com.example.jpapratice.dao.entity.Customer;
import com.example.jpapratice.dao.entity.Phone;
import com.example.jpapratice.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.jpapratice.utils.RandomUtils.randomString;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    ICustomerDao customerDao;
    @Autowired
    IPhoneDao phoneDao;

    @Override
    @Transactional
    public void queryInTrancation(Customer customer) {
        Customer entity = customerDao.findById(customer.getId()).get();
        entity.setName("Change ?");
    }

    @Override
    @Transactional
    public void initCustomerData() {
        Customer customer = new Customer();
        customer.setName("FFF");
        customer.setRocId("123");

        Phone phone = new Phone();
        phone.setMsisdn("0911111111");
        phone.setCustomer(customer);

        customer.setPhones(Collections.singleton(phone));

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        for (int customerNum = 0; customerNum < 10; customerNum++) {
            Customer tmpCustomer = new Customer();
            tmpCustomer.setName(randomString(10));
            tmpCustomer.setRocId(randomString(10));
            customers.add(tmpCustomer);
        }

        customerDao.saveAll(customers);
    }
}
