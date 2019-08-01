//package com.stackroute.review.controllerTest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.review.controller.ReviewController;
//
//import com.stackroute.review.domain.Review;
//import com.stackroute.review.service.ReviewService;
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
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest
//public class ReviewControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//    private Review review;
//    @MockBean
//    private ReviewService reviewService;
//    @InjectMocks
//    private ReviewController reviewController;
//
//    private List<Review> list=null;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).setControllerAdvice(new Exception()).build();
//        review=new Review();
//        review.setReviewerEmail("Pri");
//        review.setReviewTitle("Bad Product");
//        review.setReviewDescription("It is hanging");
//        review.setProductName("RedMi Note 3 Pro");
//        review.setPrice((float) 10000.00);
//        //review.getImage();
//        //review.getReviewedOn("2019-02-02 02:33:44");
//        list=new ArrayList<>();
//        list.add(review);
//    }
//
//    @Test
//    public void testSaveTrack() throws Exception{
//        when(reviewService.addReview(any())).thenReturn(review);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(review)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//
//    private String asJsonString(Object object) {
//        try{
//            return new ObjectMapper().writeValueAsString(object);
//
//        }catch(Exception e){
//            throw new RuntimeException(e);
//        }
//       // return new byte[0];
//
//    }
///*
//    @Test
//    public void testSaveTrackFailure() throws Exception{
//        when(reviewService.addReview(any())).thenThrow(review);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/review")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(review)))
//                .andExpect(MockMvcResultMatchers.status().isConflict())
//                .andDo(MockMvcResultHandlers.print());
//    }*/
//    @Test
//    public void getAllReviews() throws Exception{
//        when(reviewService.getAllReviews()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/reviews")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(review)))
//                //.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
//    @Test
//    public void getAllReviewsFailure() throws Exception{
//        when(reviewService.getAllReviews()).thenReturn(new ArrayList<Review>(){});
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/allTracks")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(review)))
//                //.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
////    @After
////    public void tearDown(){
////
////    }
//}
