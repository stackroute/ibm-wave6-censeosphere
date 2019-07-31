package com.stackroute.productsearchservice.service;

import com.stackroute.productsearchservice.dto.ProductDTO;
import com.stackroute.productsearchservice.dto.ProductRating;
import com.stackroute.productsearchservice.exception.ProductAlreadyExistsException;
import com.stackroute.productsearchservice.exception.ProductNotFoundException;
import com.stackroute.productsearchservice.domain.ProductDetails;
import com.stackroute.productsearchservice.repository.ProductSearchRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    private ProductSearchRepository productSearchRepository;


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeyfour}")
    private String routingkeyfour;


    @Value("${stackroute.rabbitmq.routingkeysix}")
    private String routingkeysix;

    @Value("${stackroute.rabbitmq.routingkeynine}")
    private String routingkeynine;

    @Value("${stackroute.rabbitmq.routingkeyeleven}")
    private String routingkeyeleven;

    private ProductDetails productDetails1;
    private ProductDetails productDetails2;
    private ProductDetails productDetails3;


    @Autowired
    public ProductSearchServiceImpl(ProductSearchRepository productSearchRepository)
    {
        this.productSearchRepository=productSearchRepository;
    }


    @Override
    public ProductDetails saveProduct(ProductDetails productDetails) throws ProductAlreadyExistsException {

        if(productSearchRepository.existsById(productDetails.getProductName()))
        {
         throw new ProductAlreadyExistsException("Product already exists");
        }
        else{

            System.out.println("inside");
            ProductDetails savedProducts=productSearchRepository.save(productDetails);
            System.out.println(savedProducts.toString());
            sendProduct(savedProducts);
            ProductDTO productDTO=new ProductDTO(savedProducts.getProductName(),savedProducts.getRating(),savedProducts.getPrice(),savedProducts.getProductFamily(),savedProducts.getSubCategory());
            sendToRecommendation(productDTO);
            System.out.println("after send");
            return savedProducts;
        }

    }

    @Override
    public List<ProductDetails> getAllProducts() {
        return productSearchRepository.findAll();
    }

    @Override
    public ProductDetails deleteProduct(String productName) throws ProductNotFoundException {
//        Optional optional=productSearchRepository.findById(productName);

        if (productSearchRepository.existsById(productName))
        {
            System.out.println("Hiii");
            productDetails3=productSearchRepository.findById(productName).get();
            productSearchRepository.deleteById(productName);
            sendRemove(productDetails3);
        }
        else
        {
            throw new ProductNotFoundException("Details not found");
        }
        return null;

    }

    @Override
    public ProductDetails updateProduct(ProductDetails productDetails,String productName) throws ProductNotFoundException {
        ProductDetails productDetails1=null;
        if(productSearchRepository.existsById(productName))
        {
            productDetails.setDescription(productDetails.getDescription());
            productDetails1=productSearchRepository.save(productDetails);

        }
        else
        {
            throw new ProductNotFoundException("Details not found");
        }

        return productDetails1;
    }

    @Override
    public ProductDetails getProductByName(String productName) throws ProductNotFoundException {
         Optional optional=null;
         optional=productSearchRepository.findById(productName);
         if(optional.isPresent())
          {

            productDetails1=productSearchRepository.findById(productName).get();

          }
        else
          {
            throw new ProductNotFoundException("Details not found");
          }

        return productDetails1;
    }

    @Override
    public List<ProductDetails> getRecentProducts() throws Exception {
        return productSearchRepository.findAll(Sort.by(Sort.Direction.DESC, "uploadedOn"));
    }

    @Override
    public List<ProductDetails> getTrendingProducts() throws Exception {
        return productSearchRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }


    @Override
    public void sendProduct(ProductDetails productDetails)
    {

        System.out.println("inside send");
        rabbitTemplate.convertAndSend(exchange, routingkeyfour, productDetails);
        System.out.println("Send msg = " + productDetails.toString());

    }
    @Override
    public void sendToSearch(ProductDetails productDetails)
    {

        System.out.println("inside send");
        rabbitTemplate.convertAndSend(exchange, routingkeyeleven, productDetails);
        System.out.println("Send msg = " + productDetails.toString());

    }

    @Override
    public void sendToRecommendation(ProductDTO productDTO) {

        System.out.println("inside send");
        rabbitTemplate.convertAndSend(exchange, routingkeysix,productDTO);
        System.out.println("Send msg = " + productDTO.toString());
    }

    @Override
    public void sendRemove(ProductDetails productDetails) {

        rabbitTemplate.convertAndSend(exchange, routingkeynine,productDetails);
        System.out.println("Send msg = " + productDetails.toString());

    }

    @RabbitListener(queues="${stackroute.rabbitmq.queueeight}")
    public void  recieveRating(ProductRating productRating) {

            System.out.println("recieved msg  from nlpservice= " + productRating.toString());
            Optional optional;
            optional = productSearchRepository.findById(productRating.getProductName());
            System.out.println(optional);
            if (optional != null)
             {
                productDetails2 = productSearchRepository.findById(productRating.getProductName()).get();
                productDetails2.setRating(productRating.getRating());
                productSearchRepository.save(productDetails2);
                sendToSearch(productDetails2);
             }
     }
}
