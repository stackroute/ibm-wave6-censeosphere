package com.stackroute.NLPservice.controller;

import com.stackroute.NLPservice.Domain.ProductRating;
import com.stackroute.NLPservice.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "api/v1")
public class Productservicecontroller {


    private ProductDetailService productdetailservice;

    ProductRating productRating;
    @Autowired
    public Productservicecontroller(ProductDetailService productdetailservice) {
        this.productdetailservice = productdetailservice;
    }

        @PostMapping("/save")
         public ResponseEntity<?> saveRating(@RequestBody ProductRating productRating)
        {
             System.out.println(productRating);
             ResponseEntity responseEntity;
             productRating=productdetailservice.saveRating(productRating);
             responseEntity=new ResponseEntity<String>( "successfully created", HttpStatus.CREATED);
             return  responseEntity;
        }

}
