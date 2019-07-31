package com.stackroute.productOwnerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.productOwnerservice.domain.ProductOwner;
import com.stackroute.productOwnerservice.exception.GlobalException;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsAlreadyExistsException;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsNotFoundException;
import com.stackroute.productOwnerservice.service.ProductOwnerService;
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
public class ProductOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ProductOwner productOwner;

    @MockBean
    private ProductOwnerService productOwnerService;
    @InjectMocks
    private ProductOwnerController productOwnerController;

    private List<ProductOwner> list = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productOwnerController).setControllerAdvice(GlobalException.class).build();
        productOwner = new ProductOwner();
        productOwner.setName("venky");
        productOwner.setRole("productowner");
        productOwner.setEmailId("rainarohith@gmail.com");
        productOwner.setReconfirmPassword("abcdef1234");
        productOwner.setProductsadded(null);
        list = new ArrayList<>();
        list.add(productOwner);
    }

    @Test
    public void saveDetailsSuccess() throws Exception {

        when(productOwnerService.saveDetails((ProductOwner) any())).thenReturn(productOwner);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productOwner)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void saveDetailsFailure() throws Exception {
        when(productOwnerService.saveDetails((ProductOwner) any())).thenThrow(ProductOwnerDetailsAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productOwner)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getAllDetails() throws Exception {
        when(productOwnerService.getAllDetails()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productOwner)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void getDeleteDetails() throws Exception {
        when(productOwnerService.deleteDetails(productOwner.getEmailId())).thenReturn(productOwner);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/product/rainarohith@gmail.com")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productOwner)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testGetProductOwnerByEmailId() throws Exception{
        when(productOwnerService.getProductOwnerByEmailId("rainarohith@gmail.com")).thenReturn(productOwner);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/rainarohith@gmail.com")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(productOwner)))
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
