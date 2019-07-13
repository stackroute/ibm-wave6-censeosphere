package com.stackroute.productsearchservice.controller;

import com.stackroute.productsearchservice.exception.ProductAlreadyExistsException;
import com.stackroute.productsearchservice.exception.ProductNotFoundException;
import com.stackroute.productsearchservice.domain.ProductDetails;
import com.stackroute.productsearchservice.service.ProductSearchService;
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

    ProductSearchService productSearchService;

    public ProductSearchController(ProductSearchService productSearchService)
    {
        this.productSearchService=productSearchService;
    }

    @PostMapping("product")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDetails productDetails){

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



    @GetMapping("product")
    public ResponseEntity<?> getAllProducts()
    {
        return new ResponseEntity<List<ProductDetails>>(productSearchService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("product/{productName}")
    public ResponseEntity<?> deleteProduct(@PathVariable("productName") String productName) {
        //return new ResponseEntity<String>(trackService.deleteTrack(id),HttpStatus.OK);

        try {
            ProductDetails productDetails = productSearchService.deleteProduct(productName);
            return new ResponseEntity<String>("Details Deleted", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("product/{productName}")
    public ResponseEntity<?> updateComments(@RequestBody ProductDetails productDetails,@PathVariable String productName) {
        try {
            ProductDetails productDetails1 = productSearchService.updateProduct(productDetails, productName);
            return new ResponseEntity<String>("Details updated", HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }

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


}



