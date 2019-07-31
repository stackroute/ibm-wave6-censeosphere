package com.stackroute.recommendation.controller;

import com.stackroute.recommendation.domain.SubCategory;
import com.stackroute.recommendation.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/subcategory")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("subcategories")
    public Collection<SubCategory> getAll(){
        return subCategoryService.getAll();
    }

    @PostMapping("subcategorysave")
    public SubCategory saveSubCategory(@RequestBody SubCategory subCategoryName) {
        return subCategoryService.saveSubCategory(subCategoryName);
    }

    @DeleteMapping("subcategorydelete/{subcategory}")
    public String deleteSubCategory(@PathVariable String subcategory) {
        subCategoryService.deleteSubCategory(subcategory);
        return "Deleted SubCategory";
    }
}
