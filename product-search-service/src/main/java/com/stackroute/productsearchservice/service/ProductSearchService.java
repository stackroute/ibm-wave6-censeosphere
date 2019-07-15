package com.stackroute.productsearchservice.service;

import com.stackroute.productsearchservice.exception.ProductAlreadyExistsException;
import com.stackroute.productsearchservice.exception.ProductNotFoundException;
import com.stackroute.productsearchservice.domain.ProductDetails;

import java.util.List;

public interface ProductSearchService {

    public ProductDetails saveProduct(ProductDetails productDetails) throws ProductAlreadyExistsException;

    public List<ProductDetails> getAllProducts();


    public ProductDetails deleteProduct(String productName) throws ProductNotFoundException;

    public ProductDetails updateProduct(ProductDetails productDetails,String productName) throws ProductNotFoundException;

    public ProductDetails getProductByName(String productName) throws ProductNotFoundException;
}
