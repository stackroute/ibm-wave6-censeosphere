package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Product;
import com.stackroute.recommendation.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;


public interface ProductService {

    public Collection<Product> getAll() throws ProductNotFoundException;

    public Product saveProduct(String productName, float rating, float price, String productFamily,String subCategory);

    public Collection<Product> getByFamily(String productFamily) throws ProductNotFoundException;

    public Collection<Product> getBySubCategory(String subCategory) throws ProductNotFoundException;

    public void deleteProduct(String productName) throws ProductNotFoundException;

    public Product saveRelation(String productName,String subCategory);

    Collection<Product> getProduct(String emailId);
}
