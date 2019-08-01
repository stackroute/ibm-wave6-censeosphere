package com.stackroute.recommendation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ReviewerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Reviewer reviewer;

    @MockBean
    private ReviewerService reviewerService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private ProductService productService;
    @MockBean
    private SubCategoryService subCategoryService;

    @InjectMocks
    private ReviewerController reviewerController;

    private List<Reviewer> list = null;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reviewerController).setControllerAdvice(GlobalException.class).build();
        Reviewer reviewer=new Reviewer();
        reviewer.setEmailId("monisha@gmail.com");
        list = new ArrayList<>();
        list.add(reviewer);
    }

    @Test
    public void testGetAll() throws Exception {
        when(reviewerService.getAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviewers")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testGetReviewerByEmailId() throws Exception{
        when(reviewerService.getByName("monisha@gmail.com")).thenReturn(reviewer);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/emailid/monisha@gmail.com")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
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
