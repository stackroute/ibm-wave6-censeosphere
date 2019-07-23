package com.stackroute.searchservice.service;

import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import com.stackroute.searchservice.dto.ProductDto;
import com.stackroute.searchservice.exception.SubcategoryAlreadyExistsExceptions;
import com.stackroute.searchservice.exception.SubcategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
//functions declaration

public interface SubcategoryService {

    public Subcategory saveSubcategory(Subcategory subcategory) throws SubcategoryAlreadyExistsExceptions;

    public List<Subcategory> getAllSubcategories();

    public List<Products> findAllProductsBySubcategory(String subcategory) throws SubcategoryNotFoundException;

    public String deleteSubcategory(String subcategory) throws SubcategoryNotFoundException;

    public Subcategory updateSubcategory(ProductDto productDto) throws SubcategoryNotFoundException;

}
