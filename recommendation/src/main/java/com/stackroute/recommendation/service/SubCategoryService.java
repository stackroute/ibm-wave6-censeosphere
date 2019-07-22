package com.stackroute.recommendation.service;

import com.stackroute.recommendation.domain.SubCategory;

import java.util.Collection;

public interface SubCategoryService {
    public SubCategory saveSubCategory(SubCategory subCategory);

    public Collection<SubCategory> getAll();

    public void deleteSubCategory(String subCategory);

}
