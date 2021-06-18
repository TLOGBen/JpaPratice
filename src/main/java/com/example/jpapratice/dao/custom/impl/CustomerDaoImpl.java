package com.example.jpapratice.dao.custom.impl;

import com.example.jpapratice.dao.custom.ICustomerCustomDao;
import com.example.jpapratice.dao.entity.Customer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Customer> hibernateQueryCustomerByNameList(List<String> nameList) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * FROM CUSTOMER WHERE NAME IN (:name)");

        // 現在最新版是hibernate 5.4.32, 底下的兩個deprecated會在6.0才會有替代方案, 現在只能忽略它><
        org.hibernate.Query query = em.createNativeQuery(sb.toString()).unwrap(org.hibernate.Query.class);
        query.setParameter("name", nameList);

        // 返回 List<Map>
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, ?>> results = query.getResultList();

        // 手動轉換結果
        List<Customer> customerList = new ArrayList<>();
        if (results != null && !results.isEmpty()) {
            for (Map<String, ?> resultObjMap : results) {
                Customer tmpCustomer = new Customer();
                tmpCustomer.setId(((BigInteger) resultObjMap.getOrDefault("ID", null)).longValue());
                tmpCustomer.setName(resultObjMap.getOrDefault("NAME", null).toString());
                tmpCustomer.setRocId(resultObjMap.getOrDefault("ROC_ID", null).toString());
                customerList.add(tmpCustomer);
            }
        }

        return customerList;
    }

    @Override
    public List<Customer> queryByCriteria() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);

        Root<Customer> root = criteria.from(Customer.class);
        criteria.select(root);
        TypedQuery<Customer> q = em.createQuery(criteria);

        return q.getResultList();
    }

    @Override
    public List<Customer> queryUserByUserNameWithCriteria() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);

        Root<Customer> root = criteria.from(Customer.class);
        criteria.select(root);
        ParameterExpression<String> namePara = builder.parameter(String.class);
        // SELECT * FROM Customer Where NAME = "FFF"
        criteria.select(root).where(builder.like(root.get("name"),namePara));

        TypedQuery<Customer> q = em.createQuery(criteria);
        q.setParameter(namePara, "FFF");

        return q.getResultList();
    }
}
