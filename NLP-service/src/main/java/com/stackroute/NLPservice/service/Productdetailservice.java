package com.stackroute.NLPservice.service;

import com.stackroute.NLPservice.Domain.ProductRating;

import java.util.List;

public interface Productdetailservice {



    public ProductRating saveRating(ProductRating productRating) ;
    public List<ProductRating> getAllRatings();
    public int findSentiment(String productName,String review);
    public String sentimentResult(int sentiments);
    public int generateRating(String sentiment,int rating);
    public void  updaterating(String productname,int rating);
}
