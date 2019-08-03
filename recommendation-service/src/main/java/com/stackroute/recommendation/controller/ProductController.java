package com.stackroute.recommendation.controller;

import com.stackroute.recommendation.domain.Product;
import com.stackroute.recommendation.exception.ProductNotFoundException;
import com.stackroute.recommendation.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/v1")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //method to get all products
    @GetMapping("products")
    public Collection<Product> getAll()throws ProductNotFoundException{
        return productService.getAll();
    }

    //method to save product
    @PostMapping("product")
    public Product saveProduct(@RequestBody Product product) {

        return  productService.saveProduct(product.getProductName(),product.getRating(),product.getPrice(),product.getProductFamily(),product.getSubCategory());
    }

    //method to get product by product family
    @GetMapping("productfamily/{productfamily}")
    public Collection<Product> getProduct(@PathVariable String productfamily) throws ProductNotFoundException {
        return productService.getByFamily(productfamily);
    }

    //method to get product by subcategory
    @GetMapping("productsubcategory/{subcategory}")
    public Collection<Product> getProductBySubCategory(@PathVariable String subcategory) throws ProductNotFoundException{
        return productService.getBySubCategory(subcategory);
    }

    //method to delete product by product name
    @DeleteMapping("productdelete/{productname}")
    public String deleteCourse1(@PathVariable String productname) throws  ProductNotFoundException{
        productService.deleteProduct(productname);
        return "Deleted Product";
    }

    //method to craete relation between product ande subcategory
    @PostMapping("graph/{productname}/{subcategory}")
    public Product saveRelation(@PathVariable String productname,@PathVariable String subcategory){
        return productService.saveRelation(productname,subcategory);
    }

    //method to get all recommended products by rreviewer emailId
    @GetMapping("recommendedproducts/{emailid}")
    public Collection<Product> getRecommendedProduct(@PathVariable String emailid){
        return productService.getProduct(emailid);
    }
}
