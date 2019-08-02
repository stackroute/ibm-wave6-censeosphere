package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Category;

import java.util.Collection;

public interface CategoryService {
    public Category saveCategory(Category categoryName);

    public Collection<Category> getAll();

    public void deleteCategory(String category);

    public Category saveRelation(String category,String subCategory);

}
