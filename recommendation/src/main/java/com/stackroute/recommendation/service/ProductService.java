package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;


public interface ProductService {

    public Collection<Product> getAll();

    public Product saveProduct(String productName, float rating, float price, String productFamily,String subCategory);

    public Collection<Product> getByFamily(String productFamily);

    public Collection<Product> getBySubCategory(String subCategory);

    public void deleteProduct(String productName);

    public Product saveRelation(String productName,String subCategory);

}
