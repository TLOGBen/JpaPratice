package com.example.jpapratice.dao.custom.impl;

import com.example.jpapratice.dao.custom.ICustomerCustomDao;
import com.example.jpapratice.dao.entity.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerDaoImpl implements ICustomerCustomDao {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Customer> queryCustomerByNameList(List<String> nameList) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM CUSTOMER WHERE NAME IN (:name)");

        Query query = em.createNativeQuery(sb.toString(), Customer.class);
        query.setParameter("name", nameList);

        return query.getResultList();
    }
}
