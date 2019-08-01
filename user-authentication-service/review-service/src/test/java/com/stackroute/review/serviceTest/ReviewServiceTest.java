//package com.stackroute.review.serviceTest;
//
//import com.stackroute.review.domain.Review;
//import com.stackroute.review.repository.ReviewRepository;
//import com.stackroute.review.service.ReviewServiceImpl;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class ReviewServiceTest {
//    private Review review;
//    @Mock
//    private ReviewRepository reviewRepository;
//
//    //Inject the mocks as dependencies into ReviewService
//    @InjectMocks
//    private ReviewServiceImpl reviewServiceImpl;
//    List<Review> list=null;
//
//    @Before
//    public void setUp(){
//        //Initialising mock object
//        MockitoAnnotations.initMocks(this);
//        review=new Review();
//        review.setReviewerEmail("Pri");
//        review.setReviewTitle("Bad Product");
//        review.setReviewDescription("It is hanging");
//        review.setProductName("RedMi Note 3 Pro");
//        review.setPrice((float) 10000.00);
//        //review1.getImage();
//        //review1.getReviewedOn(2019-02-02.23:34:45);
//        reviewRepository.save(review);
//    }
//    @Test
//    public void addReviewTestSuccess() {
//        when(reviewRepository.save((Review)any())).thenReturn(review);
//        Review savedReview=reviewServiceImpl.addReview(review);
//        Assert.assertEquals(review,savedReview);
//
//        //verify here verifies that trackRepository save method only called once
//        //verify(reviewRepository,times(1)).save(review);
//    }
//    @Test
//    public void addReviewTestFailure() {
//        when(reviewRepository.save((Review) any())).thenReturn(review);
//        Review savedReview = reviewServiceImpl.addReview(review);
//        Assert.assertEquals(review, savedReview);
//    }
//
//    @Test
//    public void getAllReviewsTestSuccess() {
//        reviewRepository.save(review);
//        // stubbing the mock to return specific data
//        when(reviewRepository.findAll()).thenReturn(list);
//        List<Review> trackList=reviewRepository.findAll();
//        Assert.assertEquals(list,trackList);
//    }
//    @Test
//    public void getAllReviewsTestFailure(){
//        reviewRepository.save(review);
//        when(reviewRepository.findAll()).thenReturn(list);
//        List<Review> reviewList=new ArrayList<Review>(){};
//        reviewList=reviewRepository.findAll();
//        Assert.assertEquals(list,reviewList);
//    }
//    @After
//    public  void tearDown() {
//        reviewRepository.deleteAll();
//    }
//
//}
