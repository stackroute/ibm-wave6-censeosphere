package com.stackroute.searchservice.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.searchservice.controller.SubcategoryController;
import com.stackroute.searchservice.domain.Products;
import com.stackroute.searchservice.domain.Subcategory;
import com.stackroute.searchservice.exception.SubcategoryNotFoundException;
import com.stackroute.searchservice.service.SubcategoryService;
import org.junit.After;
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
public class SearchServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private Subcategory subcategory;
    @MockBean
    private SubcategoryService subcategoryService;
    @InjectMocks
    private SubcategoryController subcategoryController;

    private List<Subcategory> list=null;

    private List<Products> productsList=null;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subcategoryController).setControllerAdvice(new Exception()).build();
        subcategory=new Subcategory();
        subcategory.setSubCategoryName("Mobile");
        list=new ArrayList<>();
        list.add(subcategory);
    }

    @Test
    public void saveSubcategoryTest() throws Exception{
        when(subcategoryService.saveSubcategory(any())).thenReturn(subcategory);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/subcategory")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(subcategory)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

    }

    private String asJsonString(Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
       // return new byte[0];

    }

    @Test
    public void getAllSubcategories() throws Exception{
        when(subcategoryService.getAllSubcategories()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/subcategories")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(subcategory)))
                //.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void getAllProductsBySubcategoryTest() throws Exception {
        when(subcategoryService.findAllProductsBySubcategory(subcategory.getSubCategoryName())).thenReturn(productsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/Mobile")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(subcategory)))
                //.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @After
    public void tearDown()
    {

    }
}
