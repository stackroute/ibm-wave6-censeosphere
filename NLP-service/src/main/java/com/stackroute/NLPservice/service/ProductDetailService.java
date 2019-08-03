package com.stackroute.NLPservice.service;

import com.stackroute.NLPservice.Domain.ProductRating;

import java.util.List;

public interface ProductDetailService {



    public ProductRating saveRating(ProductRating productRating) ;
    public List<ProductRating> getAllRatings();
    public int findSentiment(String productName,String review);
    public String sentimentResult(int sentiments);
    public float generateRating(String sentiment,ProductRating productRating,int score);
    public void  updaterating(String productname,float rating);
    public void sendRating(ProductRating productRating);
}
