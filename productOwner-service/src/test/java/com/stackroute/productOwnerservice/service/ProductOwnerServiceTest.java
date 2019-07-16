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
        productOwner.setEmailId("rainarohith@gmail.com");
        productOwner.setReconfirmPassword("abcdef1234");
        list = new ArrayList<>();
        list.add(productOwner);
    }


    @After
    public void tearDown(){
        productOwnerRepository.deleteAll();
    }
//
//    @Test
//    public void saveDetailsTestSuccess() throws ProductOwnerDetailsAlreadyExistsException {
//
//
//        when(productOwnerRepository.save((ProductOwner) any())).thenReturn(productOwner);
//        ProductOwner savedDetails = productOwnerService.saveDetails(productOwner);
//        Assert.assertEquals(productOwner, savedDetails);
//
//        //verify here verifies that trackRepository save method is only called once
//
//        verify(productOwnerRepository, times(1)).save(productOwner);
//
//    }
//
//
//    @Test(expected = ProductOwnerDetailsAlreadyExistsException.class)
//    public void saveDetailsTestFailure() throws Exception {
//
//        when(productOwnerRepository.save((ProductOwner) any())).thenReturn(null);
//        ProductOwner savedDetails = productOwnerService.saveDetails(productOwner);
//        System.out.println("savedDetails" + savedDetails);
//        Assert.assertEquals(productOwner, savedDetails);
//    }

    @Test
    public void getAllDetails() throws ProductOwnerDetailsNotFoundException {

        productOwnerRepository.save(productOwner);
        //stubbing the mock to return specific data
        when(productOwnerRepository.findAll()).thenReturn(list);
        List<ProductOwner> userlist = productOwnerService.getAllDetails();
        Assert.assertEquals(list, userlist);
    }

   /* @Test                                                                                           //test for deleteTrack method in music service
    public void deleteTestSuccess() throws ProductOwnerDetailsNotFoundException {

        when(productOwnerRepository.existsById(productOwner.getEmailId())).thenReturn(true);
        ProductOwner deleteDetails = productOwnerService.deleteDetails(productOwner.getEmailId());
        Assert.assertEquals(null,deleteDetails);

        //verify here verifies that userRepository save method is only called once
        //verify(trackRepository,times(1));

    }*/
    /*
    @Test(expected = ProductOwnerDetailsNotFoundException.class)
    public void deleteTestFailure() throws ProductOwnerDetailsNotFoundException {
        when(productOwnerRepository.existsById(productOwner.getEmailId())).thenReturn(false);
        ProductOwner deleteDetails = productOwnerService.deleteProduct(productOwner.getEmailId());

    }*/
/*
    @Test
    public void testUpdateOwnerDetails() throws ProductOwnerDetailsNotFoundException{

        when(productOwnerRepository.existsById(productOwner.getEmailId())).thenReturn(true);
        when(productOwnerRepository.save((ProductOwner) any())).thenReturn(productOwner);
        productOwner.setName("balayya");
        ProductOwner productOwner1=productOwnerService.updateDetails(productOwner,emailId);

        Assert.assertEquals("balayya",productOwner1.getName());
    }


    @Test(expected = ProductOwnerDetailsNotFoundException.class)
    public void testUpdateTrackCommentsFailure() throws ProductOwnerDetailsNotFoundException{

        when(productOwnerRepository.findById(productOwner.getEmailId())).thenReturn(Optional.empty());
        productOwner.setName("mahesh");
        ProductOwner productOwner1=productOwnerService.updateDetails(productOwner,emailId);
    }*/

}
