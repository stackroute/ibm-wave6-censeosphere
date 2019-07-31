package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Category;
import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        Category savedCategory=null;
        savedCategory=categoryRepository.createNode(category.getCategoryName());
        return savedCategory;
    }

    @Override
    public Collection<Category> getAll() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public void deleteCategory(String category) {
        categoryRepository.deleteNode(category);
    }

    @Override
    public Category saveRelation(String category, String subCategory) {
        return categoryRepository.createRelation(category,subCategory);
    }
}
