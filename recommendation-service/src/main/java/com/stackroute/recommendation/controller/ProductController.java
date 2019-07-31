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

    @GetMapping("products")
    public Collection<Product> getAll()throws Exception{
        return productService.getAll();
    }


    @PostMapping("product")
    public Product saveProduct(@RequestBody Product product) {

        return  productService.saveProduct(product.getProductName(),product.getRating(),product.getPrice(),product.getProductFamily(),product.getSubCategory());
    }

    @GetMapping("productfamily/{productfamily}")
    public Collection<Product> getProduct(@PathVariable String productfamily) throws ProductNotFoundException {
        return productService.getByFamily(productfamily);
    }

    @GetMapping("productsubcategory/{subcategory}")
    public Collection<Product> getProductBySubCategory(@PathVariable String subcategory) throws ProductNotFoundException{
        return productService.getBySubCategory(subcategory);
    }

    @DeleteMapping("productdelete/{productname}")
    public String deleteCourse1(@PathVariable String productname) throws  ProductNotFoundException{
        productService.deleteProduct(productname);
        return "Deleted Product";
    }

    @PostMapping("graph/{productname}/{subcategory}")
    public Product saveRelation(@PathVariable String productname,@PathVariable String subcategory){
        return productService.saveRelation(productname,subcategory);
    }

    @GetMapping("recommendedproducts/{emailid}")
    public Collection<Product> getRecommendedProduct(@PathVariable String emailid){
        return productService.getProduct(emailid);
    }


}
