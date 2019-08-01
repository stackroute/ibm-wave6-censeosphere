package com.stackroute.searchservice.repository;

import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubcategoryRepository extends MongoRepository<Subcategory,String> {
    Subcategory findBySubCategoryName(String subCategoryName);

}
