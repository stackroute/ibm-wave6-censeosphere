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
    float rating=0.0f;
    String sentiment="";
    int sentiments;
    int creditpoints;
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
             rating=generateRating(sentiment,productRating1.getRating(),creditpoints);
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
    public float generateRating(String sentiment,float value,int score) {
        if(value <5)
        {
            if( score>=0 &&  score<=400)
            {
                if(sentiment == "Very negative")
                {
                    rating=value-0.2f;
                }
               else if(sentiment == "Negative")
               {
                  rating=value-0.1f;
               }
              else if(sentiment == "Neutral")
               {
                  rating=value-0.05f;
               }
               else if(sentiment== "Positive")
                {

                   rating=value+0.1f;
                }
               else if(sentiment == "Very Positive")
                {
                   rating=value+0.2f;
                }

            }

          else if(score>=400 &&  score<=700)
          {


                System.out.println("monisha");
                if(sentiment == "Very negative")
                {
                    rating=value-0.4f;
                }
                else if(review == "Negative")
                {
                    rating=value-0.2f;
                }
                else if(sentiment == "Neutral")
                {
                    rating=value-0.05f;
                }
                else if(sentiment == "Positive")
                {

                    rating=value+0.2f;
                }
                else if(sentiment == "Very Positive")
                {
                    rating=value+0.4f;
                }

            }
           else if( score>=700 &&  score<=1000 )
           {
                if(sentiment== "Very negative")
                {
                rating=value-0.6f;
                }
            else if(sentiment == "Negative")
               {
                rating=value-0.3f;
               }
            else if(sentiment == "Neutral")
               {
                rating=value-0.05f;
               }
            else if(sentiment == "Positive")
               {

                rating=value+0.3f;
                }
            else if(sentiment== "Very Positive")
                {
                rating=value+0.6f;
                }

           }


        else if(  score>=1000 )
        {
            if(sentiment == "Very negative")
              {
                rating=value-0.8f;
              }
            else if(sentiment == "Negative")
              {
                rating=value-0.4f;
              }
            else if(sentiment == "Neutral")
              {
                rating=value-0.05f;
              }
            else if(sentiment == "Positive")
              {

                rating=value+0.4f;
              }
            else if(sentiment == "Very Positive")
              {
                rating=value+0.8f;
              }
        }

        }

        else if(rating == 5)
        {
            if(   score>=100 )
            {
                if(sentiment == "very negative")
                {
                    rating=value-0.8f;
                }
               else if(sentiment == "Negative")
                {
                rating=value-0.4f;
                }
            }
        }


        System.out.println("inside rating"+rating);
        return rating;


    }

    @Override
    public void updaterating(String productname, float rating)
    {

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
            creditpoints=productRatingDTO.getCreditpoints();
            System.out.println("credit points:"+creditpoints);
            sentiments =findSentiment(productRatingDTO.getProductName(),review);
            System.out.println(sentiments);
            sentiment=sentimentResult(sentiments);
            System.out.println(sentiment);
            rating=generateRating(sentiment,productRating2.getRating(),creditpoints);
            System.out.println("from rabbitmq"+rating);
            updaterating(productRatingDTO.getProductName(),rating);
            }
          else
           {
            productRating.setProductName(productRatingDTO.getProductName());
            review = productRatingDTO.getReviewDescription();
            creditpoints=productRatingDTO.getCreditpoints();
            System.out.println("credit points:"+creditpoints);
            saveRating(productRating);
           }

    }

}
