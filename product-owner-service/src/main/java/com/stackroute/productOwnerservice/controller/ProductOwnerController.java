package com.stackroute.productOwnerservice.controller;

import com.stackroute.productOwnerservice.domain.ProductOwner;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsAlreadyExistsException;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsNotFoundException;
import com.stackroute.productOwnerservice.service.ProductOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1")
public class ProductOwnerController {
    private ProductOwnerService productownerService;

    @Autowired
    public ProductOwnerController(ProductOwnerService productownerService) {
        this.productownerService = productownerService;
    }

    //method to save product owner profile
    @PostMapping("product")
    public ResponseEntity<String> saveProductOwner(@RequestBody ProductOwner productowner) {
        ResponseEntity responseEntity;
        try {
            productownerService.saveDetails(productowner);
            responseEntity = new ResponseEntity("Details saved successfully", HttpStatus.CREATED);
        } catch (ProductOwnerDetailsAlreadyExistsException ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    //method to delete product owner profile by product owner emailId
    @DeleteMapping("product/{emailId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("emailId") String emailId) {
        try {
            ProductOwner productowner = productownerService.deleteDetails(emailId);
            return new ResponseEntity<String>("Details Deleted", HttpStatus.OK);
        } catch (ProductOwnerDetailsNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }

    //method to get all products
    @GetMapping("product")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<List<ProductOwner>>(productownerService.getAllDetails(), HttpStatus.OK);
    }

    //method to update profile of product owner
    @PutMapping("products/{emailId}")
    public ResponseEntity<?> updateDetails(@RequestBody ProductOwner productowner,@PathVariable("emailId") String emailId) throws ProductOwnerDetailsNotFoundException {
        ProductOwner productowner1 = productownerService.updateDetails(productowner, emailId);
        return new ResponseEntity<ProductOwner>(productowner1, HttpStatus.OK);
    }

    //method to get product owner by emailId
    @GetMapping("product/{emailId}")
    public ResponseEntity<?> getProductOwnerByEmailId(@PathVariable("emailId") String emailId) {
        try {
            ProductOwner productOwner = productownerService.getProductOwnerByEmailId(emailId);
            return new ResponseEntity<ProductOwner>(productOwner, HttpStatus.OK);
        } catch (ProductOwnerDetailsNotFoundException e) {
            return new ResponseEntity<String>("productOwner not found", HttpStatus.NOT_FOUND);
        }
    }
}