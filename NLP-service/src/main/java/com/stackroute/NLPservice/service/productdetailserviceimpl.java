package com.stackroute.NLPservice.service;


import com.stackroute.NLPservice.Domain.ProductRating;
import com.stackroute.NLPservice.dto.ProductRatingDTO;
import com.stackroute.NLPservice.repository.Productdetailrepository;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class productdetailserviceimpl implements  Productdetailservice {

    @Autowired
    private Productdetailrepository productdetailrepository;


    private StanfordCoreNLP pipeline;
    Properties properties=new Properties();

    ProductRating productRating1;

    String review="";
    String result="";
    int rating;
    String sentiment="";
    int sentiments;
    @PostConstruct
    public void init(){
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,parse,natlog,openie,sentiment");
        pipeline=new StanfordCoreNLP(properties);
    }

    @Override
    public ProductRating saveRating(ProductRating productRating) {
         productRating1= productdetailrepository.save(productRating);
         System.out.println(productRating1);
         System.out.println(review);
            sentiments =findSentiment(productRating1.getProductName(),review);
             System.out.println(sentiments);
             sentiment=sentimentResult(sentiments);
             System.out.println(sentiment);
             rating=generateRating(sentiment,productRating1.getRating());
             System.out.println("from saveRating "+rating);
             updaterating(productRating1.getProductName(),rating);
             return  productRating1;
    }

    @Override
    public List<ProductRating> getAllRatings() {
        List<ProductRating> ratingsList=productdetailrepository.findAll();
        return ratingsList;
    }



    @Override
    public int findSentiment(String productName,String desc) {

        System.out.println((desc));
        int mainSentiment=0;
        String clearReviewText=clearReview(desc);
        if(clearReviewText != null && clearReviewText.length()>0)
        {
            int longest=0;
            Annotation annotation=pipeline.process(clearReviewText);
            for(CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class))
            {
                Tree tree=sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                int sentiment= RNNCoreAnnotations.getPredictedClass(tree);
                String parseText=sentence.toString();
                if(parseText.length()>longest){
                    mainSentiment=sentiment;
                    longest=parseText.length();
                }
            }
        }
        System.out.println(mainSentiment);
        return mainSentiment;
    }

    @Override
    public String sentimentResult(int sentiments) {

        if(sentiments == 0)
        {
            result="Very negative";
        }
        else if(sentiments == 1)
        {
            result ="Negative";
        }
        else  if(sentiments == 2)
        {
            result="Neutral";
        }
        else if(sentiments == 3)
        {
            result="Positive";
        }
        else {
            result="Very Positive";
        }
        return  result;
    }

    @Override
    public int generateRating(String sentiment,int value) {
        if(sentiment == "Very negative")
        {
            rating=value-5;
        }
        else if(sentiment == "Negative")
        {
           rating=value-3;
        }
        else  if(sentiment == "Neutral")
        {
            rating=value+2;
        }
        else if(sentiment == "Positive")
        {
            rating=value+3;
        }
        else {
            rating=value+5;
        }
        System.out.println("inside rating"+rating);
        return rating;


    }

    @Override
    public void updaterating(String productname, int rating)
    {
//        if(productdetailrepository.existsById(productname)) {
//            productRating1.setRating(rating);
//            productdetailrepository.save(productRating1);
//        }
        System.out.println("product name : "+productname);
        System.out.println("updated rating : "+rating);
        Optional optional;
        optional=productdetailrepository.findById(productname);
        System.out.println(optional);
        if(optional != null)
        {
            productRating1=productdetailrepository.findById(productname).get();
            productRating1.setRating(rating);
            productdetailrepository.save(productRating1);
        }

    }


    public String clearReview(String word)
    {
        word=word.toLowerCase();
        return  word;
    }

    ProductRating productRating2;
    @RabbitListener(queues="${stackroute.rabbitmq.queuethree}")
    public  void recieverating(ProductRatingDTO productRatingDTO)
    {
        System.out.println("recieved msg from write a review service = " + productRatingDTO.toString());
        ProductRating productRating=new ProductRating();
        if(productdetailrepository.existsById(productRatingDTO.getProductName())) {


            Optional optional;

            optional=productdetailrepository.findById(productRatingDTO.getProductName());
            System.out.println(optional);
            if(optional != null)
            {
                productRating2=productdetailrepository.findById(productRatingDTO.getProductName()).get();

            }
            review = productRatingDTO.getReviewDescription();
            sentiments =findSentiment(productRatingDTO.getProductName(),review);
            System.out.println(sentiments);
            sentiment=sentimentResult(sentiments);
            System.out.println(sentiment);
            rating=generateRating(sentiment,productRating2.getRating());
            System.out.println("from rabbitmq"+rating);
            updaterating(productRatingDTO.getProductName(),rating);

           }
        else
        {
            productRating.setProductName(productRatingDTO.getProductName());
            review = productRatingDTO.getReviewDescription();
            saveRating(productRating);
        }

    }

}
