package com.example.jpapratice;

import com.example.jpapratice.dao.ICustomerDao;
import com.example.jpapratice.dao.custom.ICustomerCustomDao;
import com.example.jpapratice.dao.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class JpaPraticeApplicationTests {
    @Autowired
    ICustomerDao customerDao;

    @Autowired
    ICustomerCustomDao customerCustomDao;

    @Test
    void test() {
        customerDao.save(new Customer());
        List<Customer> customers = customerDao.findAll();
        System.out.println(customers);
    }

    @Test
    void test2() {
        Customer customer = new Customer();
        customer.setName("FFF");
        customer.setRocId("F123");
        customerDao.save(customer);

        // 註 : 由於如果impl直接對著customerDao實作, 就必須覆蓋掉JPARepository的所有內容,
        // 因此在官網說明中寫道: 若是需要Custom method, 就必須開另一個interface
        List<Customer> customers = customerCustomDao.queryCustomerByNameList(Collections.singletonList("FFF"));
        System.out.println(customers);
    }
}
