package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Product;
import com.stackroute.recommendation.dto.ProductDTO;
import com.stackroute.recommendation.dto.ReviewDTO;
import com.stackroute.recommendation.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Collection<Product> getAll() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product saveProduct(String productName, float rating, float price, String productFamily,String subCategory) {
        Product savedProduct=null;
        savedProduct=productRepository.createNode(productName,rating,price,productFamily,subCategory);
        System.out.println(savedProduct);
        return savedProduct;
    }

    @Override
    public Collection<Product> getByFamily(String productFamily) {

        return productRepository.getNode(productFamily);
    }

    @Override
    public Collection<Product> getBySubCategory(String subCategory) {
        return productRepository.getBysubCategory(subCategory);
    }


    @Override
    public void deleteProduct(String productName) {
        productRepository.deleteNode(productName);
    }


    @Override
    public Product saveRelation(String productName, String subCategory) {
        return productRepository.createRelation(productName,subCategory);
    }



    @RabbitListener(queues="${stackroute.rabbitmq.queuesix}")
    public void  recieveproductowner(ProductDTO productDTO) {

        System.out.println("recieved msg  from productowner= " + productDTO.toString());
        Product product=new Product(productDTO.getProductName(),productDTO.getRating(),productDTO.getPrice(),productDTO.getProductFamily(),productDTO.getSubCategory());
        if(productRepository.existsById(product.getProductName())){
            System.out.println("Already exist");
        }
        else {
            saveProduct(product.getProductName(), product.getRating(), product.getPrice(), product.getProductFamily(), product.getSubCategory());
            System.out.println("Product node creadted");
            saveRelation(product.getProductName(),product.getSubCategory());
            System.out.println("Product to subcategory relation created");
        }
    }



}
