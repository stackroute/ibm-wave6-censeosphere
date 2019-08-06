package com.stackroute.NLPservice.service;


import com.stackroute.NLPservice.Domain.ProductRating;
import com.stackroute.NLPservice.dto.ProductRatingDTO;
import com.stackroute.NLPservice.repository.ProductDetailRepository;
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
public class productDetailServiceImpl implements ProductDetailService {


    private ProductDetailRepository productdetailrepository;


    @Autowired
    public productDetailServiceImpl(ProductDetailRepository productdetailrepository) {
        this.productdetailrepository = productdetailrepository;
    }

    private StanfordCoreNLP pipeline;
    Properties properties=new Properties();
    private  ProductRating productRating2;


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeyeight}")
    private String routingkeyeight;

    @Value("${string1}")
    private String  string1;
    @Value("${string2}")
    private String string2;
    @Value("${string3}")
    private String string3;
    @Value("${string4}")
    private String string4;
    @Value("${string5}")
    private String string5;


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
    float finalrating=0.0f;
    String sentiment="";
    int sentiments;
    int creditpoints;
    float sum;
    int count;
    @PostConstruct
    public void init(){

        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,depparse,parse,natlog,openie,sentiment");
        pipeline=new StanfordCoreNLP(properties);

    }

    @Override
    public ProductRating saveRating(ProductRating productRating) {
            productRating1= productdetailrepository.save(productRating);
             sentiments =findSentiment(productRating1.getProductName(),review);
             sentiment=sentimentResult(sentiments);
             finalrating=generateRating(sentiment,productRating1,creditpoints);
             updaterating(productRating1.getProductName(),finalrating);
             return  productRating1;
    }

    @Override
    public List<ProductRating> getAllRatings() {
        List<ProductRating> ratingsList=productdetailrepository.findAll();
        return ratingsList;
    }



    @Override
    public int findSentiment(String productName,String desc) {


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
        return mainSentiment;
    }

    @Override
    public String sentimentResult(int sentiments) {

        if(sentiments == 0)
        {
            result=string2;
        }
        else if(sentiments == 1)
        {
            result =string4;
        }
        else  if(sentiments == 2)
        {
            result=string5;
        }
        else if(sentiments == 3)
        {
            result=string3;
        }
        else {
            result=string1;
        }
        return  result;
    }

    @Override
    public float generateRating(String sentiment,ProductRating productRating,int score) {
        float nvalue1= Float.parseFloat(""+value1);
        float nvalue2= Float.parseFloat(""+value2);
        float nvalue3= Float.parseFloat(""+value3);
        float nvalue4= Float.parseFloat(""+value4);
        float nvalue5= Float.parseFloat(""+value5);
        float nvalue6= Float.parseFloat(""+value6);
        float nvalue7= Float.parseFloat(""+value7);
        float value=productRating.getRating();
        System.out.println("recieved value"+value);
        if(sentiment.equals(string1))
        {
            value=5;

        }
        else if(sentiment.equals(string3))
        {
            value=4;

        }
        else if(sentiment.equals(string5))
        {
            value=3;
        }
        else if(sentiment.equals(string4))
        {
            value=2;
        }
        else  if(sentiment.equals(string2))
        {
            value=1;
        }

        if(value <5)
        {
            if( score>=0 &&  score<=400)
            {
                if((sentiment).equals(string2))
                {
                    rating=value-nvalue2;
                }
               else if((sentiment).equals( string4))
               {
                  rating=value-nvalue1;
               }
              else if((sentiment).equals(string5))
               {
                  rating=value-nvalue7;

               }
               else if((sentiment).equals( string3))
                {

                   rating=value+nvalue1;
                }
               else if((sentiment).equals( string1))
                {
                   rating=value+nvalue2;
                }

            }

          else if(score>=400 &&  score<=700)
          {

              if((sentiment).equals(string2))
                {
                    rating=value-nvalue4;
                }
                else if((sentiment).equals( string4))
                {
                    rating=value-nvalue2;
                }
                else if((sentiment).equals( string5))
                {
                    rating=value-nvalue7;
                }
                else if((sentiment).equals(string3))
                {

                    rating=value+nvalue2;
                    System.out.println(rating);
                }
                else if((sentiment).equals(string1))
                {
                    rating=value+nvalue4;
                }

            }
           else if( score>=700 &&  score<=1000 )
           {
                if((sentiment).equals( string2))
                {
                rating=value-nvalue5;
                }
            else if((sentiment).equals( string4))
               {
                rating=value-nvalue3;
               }
            else if((sentiment ).equals(string5))
               {
                rating=value-nvalue7;
               }
            else if((sentiment).equals( string3))
               {

                rating=value+nvalue3;
                }
            else if((sentiment).equals(string1))
                {
                rating=value+nvalue5;
                }

           }


        else if(  score>=1000 )
        {
            if((sentiment) .equals( string2))
              {
                rating=value-nvalue6;
              }
            else if((sentiment) .equals(string4))
              {
                rating=value-nvalue4;
              }
            else if(sentiment .equals( string5))
              {
                rating=value-nvalue7;
              }
            else if((sentiment).equals( string3))
              {

                rating=value+nvalue4;
              }
            else if((sentiment).equals( string1))
              {
                rating=value+nvalue6;
              }
          }

        }
        else if (value<0)
        {
          rating=0;
        }
        else if(value>5)
        {
            rating=5;
        }
        else if(value == 5)
        {
            if(   score>=1000  &&  (sentiment).equals(string2))
            {

                rating = value - nvalue6;

            }
            else if(score>=1000 && (sentiment).equals(string4))
                {

                   rating=value-nvalue4;
                }
        }
        System.out.println(productRating.getSum());
        sum=productRating.getSum()+rating;
        count=productRating.getCount()+1;
        productRating.setSum(sum);
        productRating.setCount(count);
        productdetailrepository.save(productRating);
        finalrating=(sum)/(float)(productRating.getCount());
        return finalrating;
    }

    @Override
    public void updaterating(String productname, float rating)
    {
        Optional optional;
        optional=productdetailrepository.findById(productname);

        if(optional.isPresent())
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

    }



    @RabbitListener(queues="${stackroute.rabbitmq.queuethree}")
    public  void recieveReview(ProductRatingDTO productRatingDTO)
    {
        System.out.println("inside nlpservice "+productRatingDTO);
        ProductRating productRating=new ProductRating();
        if(productdetailrepository.existsById(productRatingDTO.getProductName())) {

            Optional optional;
            optional=productdetailrepository.findById(productRatingDTO.getProductName());

            if(optional != null)
            {
                productRating2=productdetailrepository.findById(productRatingDTO.getProductName()).get();

            }

            review = productRatingDTO.getReviewDescription();
            creditpoints=productRatingDTO.getCreditpoints();
            sentiments =findSentiment(productRatingDTO.getProductName(),review);
            sentiment=sentimentResult(sentiments);
            finalrating=generateRating(sentiment,productRating2,creditpoints);
            updaterating(productRatingDTO.getProductName(),finalrating);
            }
          else
           {
            productRating.setProductName(productRatingDTO.getProductName());
            review = productRatingDTO.getReviewDescription();
            creditpoints=productRatingDTO.getCreditpoints();
            saveRating(productRating);
           }

    }

}
