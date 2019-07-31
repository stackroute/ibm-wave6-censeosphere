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

    @Override
    public Product saveProduct(String productName, float rating, float price, String productFamily,String subCategory) {
        Product savedProduct=null;
        savedProduct=productRepository.createNode(productName,rating,price,productFamily,subCategory);
        return savedProduct;
    }

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


    @Override
    public void deleteProduct(String productName) throws ProductNotFoundException {
        if(productRepository.existsById(productName)) {
            productRepository.deleteNode(productName);
        }
        else
        {
            throw  new ProductNotFoundException(productNotFound);
        }
    }


    @Override
    public Product saveRelation(String productName, String subCategory) {
        return productRepository.createRelation(productName,subCategory);
    }

    @Override
    public Collection<Product> getProduct(String emailId) {
        return productRepository.getProduct(emailId);
    }


    @RabbitListener(queues="${stackroute.rabbitmq.queuesix}")
    public void  recieveproductowner(ProductDTO productDTO) {
            saveProduct(productDTO.getProductName(),productDTO.getRating(),productDTO.getPrice(),productDTO.getProductFamily(),productDTO.getSubCategory());
            saveRelation(productDTO.getProductName(),productDTO.getSubCategory());

    }



}
