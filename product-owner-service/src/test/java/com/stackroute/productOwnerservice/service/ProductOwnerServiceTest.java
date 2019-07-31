
package com.stackroute.productOwnerservice.service;

import com.stackroute.productOwnerservice.domain.ProductOwner;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsAlreadyExistsException;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsNotFoundException;
import com.stackroute.productOwnerservice.repository.ProductOwnerRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ProductOwnerServiceTest {

    private ProductOwner productOwner;
Optional optional;
    @Mock
    private ProductOwnerRepository productOwnerRepository;

    @InjectMocks
    private ProductOwnerServiceImpl productOwnerService;
    List<ProductOwner> list=null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        productOwner = new ProductOwner();
        productOwner.setName("venky");
        productOwner.setRole("productowner");
        productOwner.setEmailId("rohith1@gmail.com");
        productOwner.setReconfirmPassword("abcdef1234");
        productOwner.setProductsadded(null);
        list = new ArrayList<>();
        list.add(productOwner);
        optional=optional.of(productOwner);
    }


    @After
    public void tearDown(){
        productOwnerRepository.deleteAll();
    }


    @Test
    public void getAllDetails() throws ProductOwnerDetailsNotFoundException {

        productOwnerRepository.save(productOwner);
        //stubbing the mock to return specific data
        when(productOwnerRepository.findAll()).thenReturn(list);
        List<ProductOwner> userlist = productOwnerService.getAllDetails();
        Assert.assertEquals(list, userlist);
    }



    @Test
    public void testGetProductOwnerByEmailId() throws ProductOwnerDetailsNotFoundException {
        when(productOwnerRepository.save(productOwner)).thenReturn(productOwner);
        when(productOwnerRepository.existsById(productOwner.getEmailId())).thenReturn(true);


        when(productOwnerRepository.findById(productOwner.getEmailId())).thenReturn(optional);
       ProductOwner productOwner1=(ProductOwner) optional.get();
       ProductOwner getProductOwner=productOwnerService.getProductOwnerByEmailId(productOwner1.getEmailId());
        Assert.assertEquals(productOwner1,getProductOwner);
    }


}