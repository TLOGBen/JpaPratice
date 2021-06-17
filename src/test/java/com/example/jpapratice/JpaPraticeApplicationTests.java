package com.example.jpapratice;

import com.example.jpapratice.dao.ICustomerDao;
import com.example.jpapratice.dao.custom.ICustomerCustomDao;
import com.example.jpapratice.dao.entity.Customer;
import com.example.jpapratice.service.ICustomerService;
import org.assertj.core.api.Assertions;
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

    @Autowired
    ICustomerService customerService;

    @Test
    void jpaRepositoriesDefaultMethodTest() {
        customerService.initCustomerData();
        List<Customer> customers = customerDao.findAll();
        Assertions.assertThat(customers.isEmpty()).isFalse();
    }

    @Test
    void customMethodTest() {
        customerService.initCustomerData();
        // 註 : 由於如果impl直接對著customerDao實作, 就必須覆蓋掉JPARepository的所有內容,
        // 因此在官網說明中寫道: 若是需要Custom method, 就必須開另一個interface
        List<Customer> customers = customerCustomDao.queryCustomerByNameList(Collections.singletonList("FFF"));
        Assertions.assertThat(customers.isEmpty()).isFalse();
    }

    @Test
    void methodNameQueryTest() {
        customerService.initCustomerData();
        // 用Method Name查詢 等同於-- Query Customer By Name like "%?%"
        List<Customer> customers = customerDao.queryCustomerByNameIsLike("FFF");
        Assertions.assertThat(customers.isEmpty()).isFalse();
    }

    /**
     * Transaction + Entity 會需要注意的特性(1)
     */
    @Test
    void EntityTransactionTest() {
        customerService.initCustomerData();

        Customer customer = customerDao.findAll().get(0);

        // transaction 對entity的任何變動都會影響到DB
        customerService.queryInTrancation(customer);

        Customer customerTest = customerDao.findById(customer.getId()).orElse(new Customer());
        Assertions.assertThat(customerTest.getName()).isEqualTo("FFF");
    }

    /**
     * Hibernate.query 與mapping測試
     */
    @Test
    void HibernateMapQueryTest() {
        customerService.initCustomerData();
        List<Customer> customers = customerCustomDao.hibernateQueryCustomerByNameList(Collections.singletonList("FFF"));

        Assertions.assertThat(customers.isEmpty()).isFalse();
    }

    /**
     * Criteria使用測試
     */
    @Test
    void CriteriaQueryTest() {
        customerService.initCustomerData();
        List<Customer> customers = customerCustomDao.queryByCriteria();

        Assertions.assertThat(customers.isEmpty()).isFalse();
    }
}
