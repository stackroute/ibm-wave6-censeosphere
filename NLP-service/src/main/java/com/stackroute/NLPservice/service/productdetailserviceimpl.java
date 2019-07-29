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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private  ProductRating productRating2;


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeyeight}")
    private String routingkeyeight;

        @Value("${value1}")
        private String value1;
        @Value("${value2}")
        private String value2;
        @Value("${value3}")
        private String value3;
        @Value("${value4}")
        private String value4;
        @Value("${value5}")
        private String value5;
        @Value("${value6}")
        private String value6;
        @Value("${value7}")
        private String value7;



            ProductRating productRating1;

    String review="";
    String result="";
    float rating=0.0f;
    String sentiment="";
    int sentiments;
    int creditpoints;
    @PostConstruct
    public void init(){
        System.out.println("Inside init");
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
        float nvalue1= Float.parseFloat(""+value1);
        System.out.println("after parse"+nvalue1);
        float nvalue2= Float.parseFloat(""+value2);
        System.out.println("after parse"+nvalue2);
        float nvalue3= Float.parseFloat(""+value3);
        System.out.println("after parse"+nvalue3);
        float nvalue4= Float.parseFloat(""+value4);
        System.out.println("after parse"+nvalue4);
        float nvalue5= Float.parseFloat(""+value5);
        System.out.println("after parse"+nvalue5);
        float nvalue6= Float.parseFloat(""+value6);
        System.out.println("after parse"+nvalue6);
        float nvalue7= Float.parseFloat(""+value7);
        System.out.println("after parse"+nvalue7);
        if(value <5)
        {
            if( score>=0 &&  score<=400)
            {
                if(sentiment == "Very negative")
                {
                    rating=value-nvalue2;
                }
               else if(sentiment == "Negative")
               {
                  rating=value-nvalue1;
               }
              else if(sentiment == "Neutral")
               {
                  rating=value-nvalue7;
               }
               else if(sentiment== "Positive")
                {

                   rating=value+nvalue1;
                }
               else if(sentiment == "Very Positive")
                {
                   rating=value+nvalue2;
                }

            }

          else if(score>=400 &&  score<=700)
          {

              if(sentiment == "Very negative")
                {
                    rating=value-nvalue4;
                }
                else if(review == "Negative")
                {
                    rating=value-nvalue2;
                }
                else if(sentiment == "Neutral")
                {
                    rating=value-nvalue7;
                }
                else if(sentiment == "Positive")
                {

                    rating=value+nvalue2;
                }
                else if(sentiment == "Very Positive")
                {
                    rating=value+nvalue4;
                }

            }
           else if( score>=700 &&  score<=1000 )
           {
                if(sentiment== "Very negative")
                {
                rating=value-nvalue5;
                }
            else if(sentiment == "Negative")
               {
                rating=value-nvalue3;
               }
            else if(sentiment == "Neutral")
               {
                rating=value-nvalue7;
               }
            else if(sentiment == "Positive")
               {

                rating=value+nvalue3;
                }
            else if(sentiment== "Very Positive")
                {
                rating=value+nvalue5;
                }

           }


        else if(  score>=1000 )
        {
            if(sentiment == "Very negative")
              {
                rating=value-nvalue6;
              }
            else if(sentiment == "Negative")
              {
                rating=value-nvalue4;
              }
            else if(sentiment == "Neutral")
              {
                rating=value-nvalue7;
              }
            else if(sentiment == "Positive")
              {

                rating=value+nvalue4;
              }
            else if(sentiment == "Very Positive")
              {
                rating=value+nvalue6;
              }
          }

        }
        else if(rating == 5)
        {
            if(   score>=1000 )
            {
                if(sentiment == "very negative")
                {
                    rating=value-nvalue6;
                }
               else if(sentiment == "Negative")
                {
                rating=value-nvalue4;
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
            sendRating(productRating1);

        }

    }


    public String clearReview(String word)
    {
        word=word.toLowerCase();
        return  word;
    }

    @Override
    public void sendRating(ProductRating productRating) {

        rabbitTemplate.convertAndSend(exchange, routingkeyeight, productRating);
        System.out.println("Send msg from nlp = " + productRating.toString());
    }



    @RabbitListener(queues="${stackroute.rabbitmq.queuethree}")
    public  void recieveReview(ProductRatingDTO productRatingDTO)
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
