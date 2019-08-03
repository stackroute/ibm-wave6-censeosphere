package com.stackroute.searchservice.controller;

import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import com.stackroute.searchservice.dto.ProductDetails;
import com.stackroute.searchservice.exception.SubcategoryAlreadyExistsExceptions;
import com.stackroute.searchservice.exception.SubcategoryNotFoundException;
import com.stackroute.searchservice.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*")
public class SubcategoryController {
    private SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService1) {
        this.subcategoryService=subcategoryService1;
    }

    //method to save subcategory
    @PostMapping("subcategory")
    public ResponseEntity<Subcategory> saveSubcategory(@RequestBody Subcategory subcategory){
        ResponseEntity responseEntity;
        try {
            subcategoryService.saveSubcategory(subcategory);
            responseEntity = new ResponseEntity("Subcategory saved successfully", HttpStatus.CREATED);
        } catch (SubcategoryAlreadyExistsExceptions subcategoryAlreadyExistsExceptions) {
            responseEntity = new ResponseEntity<String>(subcategoryAlreadyExistsExceptions.getMessage(), HttpStatus.CONFLICT);
            subcategoryAlreadyExistsExceptions.printStackTrace();
        }
        return responseEntity;
    }

    //method to get all subcategories
    @GetMapping("subcategories")
    public ResponseEntity<List<Subcategory>> getAllSubcategory() {
        return new ResponseEntity<List<Subcategory>>(subcategoryService.getAllSubcategories(), HttpStatus.OK);
    }

    //method to get all products by subcategory
    @GetMapping("products/{subCategory}")
    public ResponseEntity<List<Products>> findAllProductsBySubcategory(@PathVariable("subCategory") String subCategory)throws SubcategoryNotFoundException
    {
        ResponseEntity responseEntity;
        try {
            responseEntity= new ResponseEntity<List<Products>>(subcategoryService.findAllProductsBySubcategory(subCategory), HttpStatus.OK);
        } catch (SubcategoryNotFoundException s) {
            responseEntity = new ResponseEntity<String>(s.getMessage(), HttpStatus.CONFLICT);
            s.printStackTrace();
        }
        return responseEntity;
    }

    //method to update subcategory
    @PostMapping("products")
    public  ResponseEntity<String> updateSubcategory(@RequestBody ProductDetails productDetails) {
        Date date=new Date();
        long millies=date.getTime();
        Timestamp timestamp=new Timestamp(millies);
        productDetails.setUploadedOn(timestamp);
        subcategoryService.updateSubcategory(productDetails);
        return new ResponseEntity<>("Subcategory updated successfully!", HttpStatus.OK);
    }
}
