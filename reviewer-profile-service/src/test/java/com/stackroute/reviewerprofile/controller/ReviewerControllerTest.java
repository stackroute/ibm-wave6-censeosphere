//package com.stackroute.reviewerprofile.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.reviewerprofile.domain.Reviewer;
//import com.stackroute.reviewerprofile.exceptions.ReviewerAlreadyExistsException;
//import com.stackroute.reviewerprofile.exceptions.ReviewerExceptionController;
//import com.stackroute.reviewerprofile.exceptions.ReviewerNotFoundException;
//import com.stackroute.reviewerprofile.service.ReviewerService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import java.util.ArrayList;
//import java.util.List;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class ReviewerControllerTest
//{
//    @Autowired
//    private MockMvc mockMvc;
//    private Reviewer reviewer;
//
//    @MockBean
//    private ReviewerService reviewerService;
//
//    @InjectMocks
//    private ReviewerController reviewerController;
//    private List<Reviewer> list = null;
//
//    @Before
//    public void setUp() {
//
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(reviewerController).setControllerAdvice(new ReviewerExceptionController()).build();
//        reviewer = new Reviewer();
//        reviewer.setEmailId("ganga@gmail.com");
//        reviewer.setName("ganga");
//        reviewer.setReconfirmPassword("Abcd12345");
//        reviewer.setRole("reviewer");
//        list = new ArrayList<>();
//        list.add(reviewer);
//    }
//
//    @Test
//    public void testSaveReviewer() throws Exception
//    {
//        when(reviewerService.saveReviewers(any())).thenReturn(reviewer);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviewer")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testSaveReviewerFailure() throws Exception {
//        when(reviewerService.saveReviewers(any())).thenThrow(ReviewerAlreadyExistsException.class);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/reviewer")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void testDisplayAllReviewers() throws Exception {
//        when(reviewerService.displayAllReviewers()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviewers")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    @Test
//    public void testDisplayAllReviewersFailure() throws Exception {
//        when(reviewerService.displayAllReviewers()).thenThrow(ReviewerNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviewers")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//    }

/*
    @Test
    public void testUpdateReviewer() throws Exception{
        when(reviewerService.updateReviewer(reviewer)).thenReturn(reviewer);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reviewer/ganga@gmail.com")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }*/


//
////    @Test
////    public void testGetReviewerByEmailId() throws Exception{
////        when(reviewerService.getReviewerByEmailId("ganga@gmail.com")).thenReturn(reviewer);
////        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviewer/ganga@gmail.com")
////                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andDo(MockMvcResultHandlers.print());
////
////    }
//

//    @Test
//    public void testGetReviewerByEmailIdFailure() throws Exception{
//        when(reviewerService.getReviewerByEmailId("januka@gmail.com")).thenThrow(ReviewerNotFoundException.class);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviewer/januka@gmail.com")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
////    @Test
////    public void testDeleteReviewer() throws Exception{
////        when(reviewerService.deleteReviewer("ganga@gmail.com")).thenReturn(list);
////        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviewer/ganga@gmail.com")
////                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andDo(MockMvcResultHandlers.print());
////    }
////
////    @Test
////    public void testDeleteReviewerFailure() throws Exception{
////        when(reviewerService.deleteReviewer("januka@gmail.com")).thenThrow(ReviewerNotFoundException.class);
////        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/reviewer/januka@gmail.com")
////                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
////                .andExpect(MockMvcResultMatchers.status().isNotFound())
////                .andDo(MockMvcResultHandlers.print());
////    }
//
//    @Test
//    public void testUpdateReviewer() throws Exception{
//        when(reviewerService.updateReviewer(reviewer)).thenReturn(reviewer);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reviewer/ganga@gmail.com")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
////    @Test
////    public void testUpdateReviewerFailure() throws Exception{
////        Reviewer reviewer1=new Reviewer("gango@gmail.com","januka","Abcd12345","reviewer");
////        when(reviewerService.updateReviewer(reviewer1)).thenThrow(ReviewerNotFoundException.class);
////        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/reviewer/gango@gmail.com")
////                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(reviewer1)))
////                .andExpect(MockMvcResultMatchers.status().isNotFound())
////                .andDo(MockMvcResultHandlers.print());
////    }
//
//    private static String asJsonString(final Object obj)
//    {
//        try{
//            return new ObjectMapper().writeValueAsString(obj);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//    }
//}
