//
////
//
//package com.stackroute.reviewerprofile.repository;
//
//import com.stackroute.reviewerprofile.domain.Reviewer;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataMongoTest
//public class ReviewerRepositoryTest {
//    @Autowired
//    private ReviewerRepository reviewerRepository;
//    private Reviewer reviewer;
//
//    @Before
//    public void setUp()
//    {
//        reviewer=new Reviewer();
//        reviewer.setEmailId("ganga@gmail.com");
//        reviewer.setName("ganga");
//        reviewer.setReconfirmPassword("Abcd12345");
//        reviewer.setRole("reviewer");
//
//    }
//
//    @After
//    public void tearDown()
//    {
//        reviewerRepository.deleteAll();
//    }
//
//    @Test
//    public void testSaveReviewers()
//    {
//        reviewerRepository.save(reviewer);
//        Reviewer fetchReviewer = reviewerRepository.findById(reviewer.getEmailId()).get();
//        Assert.assertEquals("ganga@gmail.com", fetchReviewer.getEmailId());
//    }
//
//    @Test
//    public void testSaveReviewersFailure() {
//        Reviewer testReviewer = new Reviewer("agnag@gmail.com", "agnag", "abcd1234", "reviewer","");
//        reviewerRepository.save(reviewer);
//        Reviewer fetchReviewer = reviewerRepository.findById(reviewer.getEmailId()).get();
//        Assert.assertNotSame(testReviewer, reviewer);
//    }
//
//    @Test
//    public void testDisplayAllReviewers(){
//        Reviewer reviewer1= new Reviewer("gayatri@gmail.com","gayatri","gaya303","reviewer","");
//        Reviewer reviewer2 = new Reviewer("januka@gmail.com","januka","akunaj03","reviewer","");
//        reviewerRepository.save(reviewer1);
//        reviewerRepository.save(reviewer2);
//        List<Reviewer> list = reviewerRepository.findAll();
//        Assert.assertEquals("gayatri@gmail.com",list.get(0).getEmailId());
//    }
//
//    @Test
//    public void testDisplayAllReviewersFailure(){
//        Reviewer reviewer1= new Reviewer("gayatri@gmail.com","gayatri","gaya303","reviewer","");
//        Reviewer reviewer2 = new Reviewer("januka@gmail.com","januka","akunaj03","reviewer","");
//        reviewerRepository.save(reviewer1);
//        reviewerRepository.save(reviewer2);
//        List<Reviewer> list = reviewerRepository.findAll();
//        Assert.assertNotEquals("gayatri@gmail.com",list.get(1).getName());
//    }
//
//    @Test
//    public void testGetReviewerByEmailId(){
//        reviewerRepository.save(reviewer);
//        Reviewer foundReviewer=(Reviewer) reviewerRepository.findById(reviewer.getEmailId()).get();
//        Assert.assertEquals(reviewer,foundReviewer);
//    }
//
//    @Test
//    public void testGetReviewerByEmailIdFailure(){
//        reviewerRepository.save(reviewer);
//        Reviewer foundReviewer=reviewerRepository.findById(reviewer.getEmailId()).get();
//        Assert.assertNotEquals(2,foundReviewer.getEmailId());
//    }
//
//    @Test
//    public void testDeleteReviewer(){
//        reviewerRepository.delete(reviewer);
//        List<Reviewer> list=reviewerRepository.findAll();
//
//        Assert.assertEquals(false,list.contains(reviewer));
//    }
//
//    @Test
//    public void testDeleteReviewerFailure(){
//        reviewerRepository.delete(reviewer);
//        List<Reviewer> list=reviewerRepository.findAll();
//        Assert.assertNotEquals(true,list.contains(reviewer));
//    }
//
//}
//
//
