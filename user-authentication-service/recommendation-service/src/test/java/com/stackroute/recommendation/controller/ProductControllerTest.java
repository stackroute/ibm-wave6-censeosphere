package com.stackroute.recommendation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendation.domain.Product;
import com.stackroute.recommendation.domain.Reviewer;
import com.stackroute.recommendation.exception.GlobalException;
import com.stackroute.recommendation.service.CategoryService;
import com.stackroute.recommendation.service.ProductService;
import com.stackroute.recommendation.service.ReviewerService;
import com.stackroute.recommendation.service.SubCategoryService;
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

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Product product;

    @MockBean
    private ReviewerService reviewerService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private ProductService productService;
    @MockBean
    private SubCategoryService subCategoryService;

    @InjectMocks
    private ProductController productController;

    private List<Product> list = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).setControllerAdvice(GlobalException.class).build();
        Product product=new Product();
        product.setProductName("Redmi");
        product.setRating(1.2f);
        product.setPrice(20000);
        product.setProductFamily("Redmi");
        product.setSubCategory("Mobile");
        list = new ArrayList<>();
        list.add(product);
    }

    @Test
    public void testGetAll() throws Exception {
        when(productService.getAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetProductByFamily() throws Exception{
        when(productService.getByFamily("Redmi")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productfamily/Redmi")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetProductBySubCategory() throws Exception{
        when(productService.getBySubCategory("Mobile")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/productsubcategory/Mobile")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
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
