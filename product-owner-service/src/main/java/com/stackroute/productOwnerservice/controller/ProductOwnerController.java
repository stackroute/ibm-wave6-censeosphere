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


    ProductOwnerService productownerService;

    public ProductOwnerController(ProductOwnerService productownerService) {
        this.productownerService = productownerService;
    }

    @PostMapping("product")
    public ResponseEntity<?> saveTrack(@RequestBody ProductOwner productowner) {
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

    @DeleteMapping("product/{emailId}")
    public ResponseEntity<?> deleteProduct(@PathVariable("emailId") String emailId) {
        //return new ResponseEntity<String>(trackService.deleteTrack(id),HttpStatus.OK);

        try {
            ProductOwner productowner = productownerService.deleteDetails(emailId);
            return new ResponseEntity<String>("Details Deleted", HttpStatus.OK);
        } catch (ProductOwnerDetailsNotFoundException e) {
            return new ResponseEntity<String>("Details not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("product")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<List<ProductOwner>>(productownerService.getAllDetails(), HttpStatus.OK);
    }

    @PutMapping("products/{emailId}")
    public ResponseEntity<?> updateComments(@RequestBody ProductOwner productowner,@PathVariable("emailId") String emailId) throws ProductOwnerDetailsNotFoundException {

         ProductOwner productowner1 = productownerService.updateDetails(productowner, emailId);
          return new ResponseEntity<String>("Details updated", HttpStatus.OK);

    }


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