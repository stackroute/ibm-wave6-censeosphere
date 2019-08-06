package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Product;
import com.stackroute.recommendation.dto.ProductDTO;
import com.stackroute.recommendation.dto.ReviewDTO;
import com.stackroute.recommendation.exception.ProductNotFoundException;
import com.stackroute.recommendation.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    Product product;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Value("${productNotFound}")
    String productNotFound;

    //service implementation to get all products
    @Override
    public Collection<Product> getAll()throws ProductNotFoundException {
        Collection<Product> productCollection=productRepository.getAllProducts();
        if(productCollection.isEmpty()){
            throw  new ProductNotFoundException(productNotFound);
        }
        else {
            return productRepository.getAllProducts();
        }
    }

    //service implementation to save product
    @Override
    public Product saveProduct(String productName, float rating, float price, String productFamily,String subCategory) {
        Product savedProduct=null;
        savedProduct=productRepository.createNode(productName,rating,price,productFamily,subCategory);
        return savedProduct;
    }

    //service implementation to get product by product family
    @Override
    public Collection<Product> getByFamily(String productFamily) throws ProductNotFoundException {
       Collection<Product> productCollection=productRepository.getNode(productFamily);
       if(productCollection.isEmpty()){
           throw  new ProductNotFoundException(productNotFound);
       }
       else {
           return productRepository.getNode(productFamily);
       }
    }

    //service implementation to get product by subcategory
    @Override
    public Collection<Product> getBySubCategory(String subCategory) throws ProductNotFoundException {
        Collection<Product> productCollection=productRepository.getBysubCategory(subCategory);
        if(productCollection.isEmpty()){
            throw  new ProductNotFoundException(productNotFound);
        }
        else{
            return productRepository.getBysubCategory(subCategory);
        }
    }

    //service implementation to delete product by product name
    @Override
    public void deleteProduct(String productName) throws ProductNotFoundException {
        if(productRepository.existsById(productName)) {
            productRepository.deleteNode(productName);
        }
        else {
            throw  new ProductNotFoundException(productNotFound);
        }
    }

    //service implementation to save relation between product and subcategory
    @Override
    public Product saveRelation(String productName, String subCategory) {
        return productRepository.createRelation(productName,subCategory);
    }

    //service implementation to get recommended products by reviewer emailId
    @Override
    public Collection<Product> getProduct(String emailId) {
        return productRepository.getProduct(emailId);
    }

    //method to receive product information through rabbitmq and save product and create relation between product and subcategory
    @RabbitListener(queues="${stackroute.rabbitmq.queuesix}")
    public void  recieveproductowner(ProductDTO productDTO) {
        System.out.println("Inside recommnedation product" +
                "ct"+productDTO);
             productRepository.createNode(productDTO.getProductName(),productDTO.getRating(),productDTO.getPrice(),productDTO.getProductFamily(),productDTO.getSubCategory());
            productRepository.createRelation(productDTO.getProductName(),productDTO.getSubCategory());

    }
}
