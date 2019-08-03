//package com.stackroute.reviewerprofile.service;
//
//import com.stackroute.reviewerprofile.domain.Reviewer;
//import com.stackroute.reviewerprofile.exceptions.ReviewerAlreadyExistsException;
//import com.stackroute.reviewerprofile.exceptions.ReviewerNotFoundException;
//import com.stackroute.reviewerprofile.repository.ReviewerRepository;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class ReviewerServiceTest {
//    private Reviewer reviewer;
//    Optional optional;
//
//    @Mock
//    private ReviewerRepository reviewerRepository;
//
//    @InjectMocks
//    private ReviewerServiceImpl reviewerService;
//    List<Reviewer> list= null;
//
//    @Before
//    public void setUp(){
//        //Initialising the mock object
//        MockitoAnnotations.initMocks(this);
//        reviewer = new Reviewer();
//        reviewer.setEmailId("ganga@gmail.com");
//        reviewer.setName("ganga");
//        reviewer.setReconfirmPassword("Abcd12345");
//        reviewer.setRole("reviewer");
//        list = new ArrayList<>();
//        list.add(reviewer);
//        optional=optional.of(reviewer);
//    }
//
////    @Test
////    public void testSaveReviewers() throws ReviewerAlreadyExistsException
////    {
////        when(reviewerRepository.save((Reviewer) any())).thenReturn(reviewer);
////        Reviewer saveReviewer = reviewerService.saveReviewers(reviewer);
////        Assert.assertEquals(reviewer,saveReviewer);
////        //verify here verifies that userRepository save method is only called once
////        verify(reviewerRepository,times(1)).save(reviewer);
////    }
////
////    @Test
////    public void testSaveReviewersFailure() throws ReviewerAlreadyExistsException
////    {
////        when(reviewerRepository.save((Reviewer) any())).thenReturn(null);
////        Reviewer saveReviewer = reviewerService.saveReviewers(reviewer);
////        Assert.assertNotEquals(reviewer,saveReviewer);
////        //verify here verifies that userRepository save method is only called once
////        //verify(reviewerRepository,times(1)).save(reviewer);
////    }
//
//    @Test
//    public void testDisplayAllReviewers(){
//        reviewerRepository.save(reviewer);
//        when(reviewerRepository.findAll()).thenReturn(list);
//        List<Reviewer> reviewerslist = reviewerService.displayAllReviewers();
//        Assert.assertEquals(list,reviewerslist);
//    }
//
//    @Test
//    public void testGetReviewerByEmailId() throws ReviewerNotFoundException {
//        when(reviewerRepository.save(reviewer)).thenReturn(reviewer);
//        when(reviewerRepository.existsById(reviewer.getEmailId())).thenReturn(true);
//        when(reviewerRepository.findById(reviewer.getEmailId())).thenReturn(optional);
//        Reviewer reviewer1=(Reviewer) optional.get();
//        Reviewer getReviewer=reviewerService.getReviewerByEmailId(reviewer1.getEmailId());
//        Assert.assertEquals(reviewer1,getReviewer);
//    }
//
//    @Test
//    public void testDeleteReviewer() throws ReviewerNotFoundException {
//        when(reviewerRepository.findById("ganga@gmail.com")).thenReturn(optional);
//        List<Reviewer> actualList=reviewerService.deleteReviewer(reviewer.getEmailId());
//        Assert.assertEquals(false,actualList.contains(reviewer));
//    }
//
//    @Test
//    public void testDeleteReviewerFailure() throws ReviewerNotFoundException {
//        when(reviewerRepository.findById("januka@gmail.com")).thenReturn(optional);
//        List<Reviewer> actualList=reviewerService.deleteReviewer(reviewer.getEmailId());
//        Assert.assertNotEquals(true,actualList.contains(reviewer));
//    }
//
///*    @Test
//    public void testUpdateReviewer() throws ReviewerNotFoundException {
//            Reviewer updateReviewers=new Reviewer("ganga@gmail.com","gangak","Abcd12345","reviewer");
//            when(reviewerRepository.existsById("ganga@gmail.com")).thenReturn(true);
//            when(reviewerRepository.findById("ganga@gmail.com")).thenReturn(optional);
//            when(reviewerRepository.save(reviewer)).thenReturn(reviewer);
//            Reviewer actualReviewer=reviewerService.updateReviewer(updateReviewers);
//            Assert.assertEquals(updateReviewers,actualReviewer);
//        }
//
//    @Test
//    public void testUpdateReviewerFailure() throws ReviewerNotFoundException {
//        when(reviewerRepository.save(reviewer)).thenReturn(reviewer);
//        when(reviewerRepository.existsById(reviewer.getEmailId())).thenReturn(true);
//        when(reviewerRepository.findById(reviewer.getEmailId())).thenReturn(optional);
//        Reviewer updateReviewers=new Reviewer("januka@gmail.com","januka","","reviewer");
//        reviewer.setName(updateReviewers.getName());
//        Reviewer actualReviewer=reviewerService.updateReviewer(updateReviewers);
//        Assert.assertNotEquals(updateReviewers,actualReviewer);
//    }*/
//
//}
