package com.stackroute.productsearchservice.service;

import com.stackroute.productsearchservice.domain.ProductDetails;
import com.stackroute.productsearchservice.exception.ProductAlreadyExistsException;
import com.stackroute.productsearchservice.exception.ProductNotFoundException;
import com.stackroute.productsearchservice.repository.ProductSearchRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProductSearchServiceTest {
    private ProductDetails productDetails;
    Optional optional;

    @Mock
    private ProductSearchRepository productSearchRepository;

    @InjectMocks
    private ProductSearchServiceImpl productSearchService;
    List<ProductDetails> list = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productDetails = new ProductDetails();
        productDetails.setAddedby("r@gmail.com");
        productDetails.setCategory("Electronic Device");
        productDetails.setSubCategory("Mobile");
        productDetails.setProductName("Honor 1");
        productDetails.setProductFamily("Honor");
        productDetails.setImage("");
        productDetails.setPrice(11000f);
        productDetails.setRating(3.5f);
        productDetails.setSpecifications("RAM-3GB,ROM-32GB");
        productDetails.setDescription("support Pubg Game");
        productDetails.setUploadedOn(null);

        list = new ArrayList<>();
        list.add(productDetails);
        optional = optional.of(productDetails);
    }

    @After
    public void tearDown() {
        productSearchRepository.deleteAll();
    }



    @Test
    public void getAllProducts() throws ProductNotFoundException {

        productSearchRepository.save(productDetails);

        when(productSearchRepository.findAll()).thenReturn(list);
        List<ProductDetails> userlist = productSearchService.getAllProducts();
        Assert.assertEquals(list, userlist);
    }


   @Test
    public void testGetProductByName() throws ProductNotFoundException {
        when(productSearchRepository.save(productDetails)).thenReturn(productDetails);
        when(productSearchRepository.existsById(productDetails.getProductName())).thenReturn(true);


        when(productSearchRepository.findById(productDetails.getProductName())).thenReturn(optional);
        ProductDetails product1=(ProductDetails) optional.get();
        ProductDetails getProduct=productSearchService.getProductByName(product1.getProductName());
        Assert.assertEquals(product1,getProduct);
    }

}
