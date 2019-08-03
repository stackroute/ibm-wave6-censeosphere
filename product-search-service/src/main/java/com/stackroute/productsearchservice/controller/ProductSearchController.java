package com.stackroute.productsearchservice.controller;

import com.stackroute.productsearchservice.exception.ProductAlreadyExistsException;
import com.stackroute.productsearchservice.exception.ProductNotFoundException;
import com.stackroute.productsearchservice.domain.ProductDetails;
import com.stackroute.productsearchservice.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("api/v1")
public class ProductSearchController {

    private ProductSearchService productSearchService;

    @Autowired
    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService=productSearchService;
    }

    //method to save product added by product owner
    @PostMapping("product")
    public ResponseEntity<String> saveProduct(@RequestBody ProductDetails productDetails){
        ResponseEntity responseEntity;

       Date date=new Date();

        long millies=date.getTime();
        Timestamp timestamp=new Timestamp(millies);
        productDetails.setUploadedOn(timestamp);
        try {
            productSearchService.saveProduct(productDetails);
            responseEntity = new ResponseEntity("Product saved successfully", HttpStatus.CREATED);
        } catch (ProductAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    //method to get all products
    @GetMapping("product")
    public ResponseEntity<List<ProductDetails>> getAllProducts() {
        return new ResponseEntity<>(productSearchService.getAllProducts(), HttpStatus.OK);
    }

    //method to delete product by product name
    @DeleteMapping("products/{productName}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productName") String productName) {
        try {
            ProductDetails productDetails = productSearchService.deleteProduct(productName);
            return new ResponseEntity<String>("Details Deleted", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }

    //method to update product
    @PutMapping("product/{productName}")
    public ResponseEntity<String> updateComments(@RequestBody ProductDetails productDetails,@PathVariable String productName) {
        try {
            ProductDetails productDetails1 = productSearchService.updateProduct(productDetails, productName);
            return new ResponseEntity<String>("Details updated", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }

    //method to get product by product name
    @GetMapping("product/{productName}")
    public ResponseEntity<?> getProductByName(@PathVariable String productName)
    {
        try {
            ProductDetails productDetails = productSearchService.getProductByName(productName);
            return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }

    //method to get recently added products
    @GetMapping("recentproducts")
    public ResponseEntity<?> getRecentProducts() {
        try {
            return new ResponseEntity<List<ProductDetails>>(productSearchService.getRecentProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("No products", HttpStatus.NOT_FOUND);
        }
    }

    //method to get trending products
    @GetMapping("trendingproducts")
    public ResponseEntity<?> getTrendingProducts() {
        try {
            return new ResponseEntity<List<ProductDetails>>(productSearchService.getTrendingProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("No products", HttpStatus.NOT_FOUND);
        }
    }

    //method to get product by product owner emailId
    @GetMapping("search/{emailId}/{prodcutName}")
    public ResponseEntity<?> getProductByEmailId(@PathVariable("emailId") String emailId,@PathVariable("prodcutName") String prodcutName) {
        ProductDetails productDetails = productSearchService.searchProductByProductOwner(emailId, prodcutName);
        return new ResponseEntity<ProductDetails>(productDetails, HttpStatus.OK);
    }
}



