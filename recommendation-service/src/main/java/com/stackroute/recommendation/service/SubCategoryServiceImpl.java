package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.Category;
import com.stackroute.recommendation.domain.SubCategory;
import com.stackroute.recommendation.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        SubCategory savedSubCategory=null;
        savedSubCategory=subCategoryRepository.createNode(subCategory.getSubCategoryName());
        return savedSubCategory;
    }

    @Override
    public Collection<SubCategory> getAll() {
        return subCategoryRepository.getAllSubCategories();
    }

    @Override
    public void deleteSubCategory(String subCategory) {
        subCategoryRepository.deleteNode(subCategory);
    }
}
