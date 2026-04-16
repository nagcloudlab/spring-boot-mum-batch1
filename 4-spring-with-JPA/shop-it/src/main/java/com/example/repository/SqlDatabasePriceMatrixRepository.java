package com.example.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

// @Component("sqlDatabasePriceMatrix")
@Repository("sqlDatabasePriceMatrix")
@Scope("singleton")
public class SqlDatabasePriceMatrixRepository implements PriceMatrix {

    @Autowired
    private EntityManager entityManager;

    @Override
    public double getPrice(String productId) {
        String jpql = "SELECT p.price FROM PriceMatrix p WHERE p.productId = :productId";
        Double price = entityManager.createQuery(jpql, Double.class)
                                    .setParameter("productId", Integer.parseInt(productId))
                                    .getSingleResult();
        return price != null ? price : 0.0;
    }
    
}
