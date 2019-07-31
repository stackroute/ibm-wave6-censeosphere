package com.stackroute.recommendation.controller;

import com.stackroute.recommendation.domain.Category;
import com.stackroute.recommendation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/category")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public Collection<Category> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("categorysave")
    public Category saveCategory(@RequestBody Category categoryName) {
        return  categoryService.saveCategory(categoryName);
    }

    @DeleteMapping("categorydelete/{category}")
    public String deleteCategory(@PathVariable String category) {
        categoryService.deleteCategory(category);
        return "Deleted Category";
    }

    @PostMapping("graph/{category}/{subcategory}")
    public Category saveRelation(@PathVariable String category,@PathVariable String subcategory){
        return categoryService.saveRelation(category,subcategory);
    }

}
