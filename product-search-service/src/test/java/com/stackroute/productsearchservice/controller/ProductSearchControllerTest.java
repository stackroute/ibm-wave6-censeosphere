package com.stackroute.productsearchservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.productsearchservice.domain.ProductDetails;
import com.stackroute.productsearchservice.exception.GlobalException;
import com.stackroute.productsearchservice.service.ProductSearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductSearchControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private ProductDetails productDetails;

    @MockBean
    private ProductSearchService productSearchService;
    @InjectMocks
    private ProductSearchController productSearchController;

    private List<ProductDetails> list = null;

    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productSearchController).
                setControllerAdvice(GlobalException.class).build();
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
    }

    @Test
    public void saveProductSuccess() throws Exception {

        when(productSearchService.saveProduct((ProductDetails) any())).thenReturn(productDetails);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productDetails)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getAllProducts() throws Exception {
        when(productSearchService.getAllProducts()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productDetails)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getDeleteProductDetails() throws Exception {
        when(productSearchService.deleteProduct(productDetails.getProductName())).thenReturn(productDetails);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/Honor 1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productDetails)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testGetProductByName() throws Exception{
        when(productSearchService.getProductByName("Honor 1")).thenReturn(productDetails);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/Honor 1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productDetails)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
