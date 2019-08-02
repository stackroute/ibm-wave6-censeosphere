package com.stackroute.searchservice.serviceTest;

import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import com.stackroute.searchservice.exception.SubcategoryAlreadyExistsExceptions;
import com.stackroute.searchservice.exception.SubcategoryNotFoundException;
import com.stackroute.searchservice.repository.SubcategoryRepository;
import com.stackroute.searchservice.service.SubcategoryServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SearchServiceImplTest {
    private Subcategory subcategory;
    private Products products;
    @Mock
    private SubcategoryRepository subcategoryRepository;

    //Inject the mocks as dependencies into ReviewService
    @InjectMocks
    private SubcategoryServiceImpl subcategoryServiceImpl;
    List<Subcategory> list=new ArrayList<>();

    @Before
    public void setUp(){
        //Initialising mock object
        MockitoAnnotations.initMocks(this);
        subcategory=new Subcategory();
        subcategory.setSubCategoryName("Mobile");
        subcategoryRepository.save(subcategory);
    }
    @Test
    public void saveSubcategoryTestSuccess() throws SubcategoryAlreadyExistsExceptions {
        when(subcategoryRepository.save((Subcategory)any())).thenReturn(subcategory);
        Subcategory savedSub=subcategoryServiceImpl.saveSubcategory(subcategory);
        Assert.assertEquals(subcategory,savedSub);

        //verify here verifies that trackRepository save method only called once
        //verify(reviewRepository,times(1)).save(review);
    }

    @Test
    public void findAllProductsBySubcategoryTestSuccess() throws SubcategoryNotFoundException {
        subcategoryRepository.save(subcategory);
        List<Products> list1=subcategory.getProducts();
        when(subcategoryRepository.findBySubCategoryName("Mobile")).thenReturn(subcategory);
        List<Products> list2=subcategoryServiceImpl.findAllProductsBySubcategory("Mobile");
        Assert.assertEquals(list1,list2);
    }

    @Test
    public void getAllSubcategoriesTestSuccess() {
        subcategoryRepository.save(subcategory);
        // stubbing the mock to return specific data
        when(subcategoryRepository.findAll()).thenReturn(list);
        List<Subcategory> trackList=subcategoryRepository.findAll();
        Assert.assertEquals(list,trackList);
    }
    @After
    public void tearDown()
    {
        subcategoryRepository.deleteAll();
    }
}
