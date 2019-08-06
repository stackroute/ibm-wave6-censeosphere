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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = {"product"})
@Service
public class ProductSearchServiceImpl implements ProductSearchService {
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

    @Autowired
    public ProductSearchServiceImpl(ProductSearchRepository productSearchRepository)
    {
        this.productSearchRepository=productSearchRepository;
    }

   //service implementation to save product
    @CacheEvict(allEntries = true)
    @Override
    public ProductDetails saveProduct(ProductDetails productDetails) throws ProductAlreadyExistsException {
        if(productSearchRepository.existsById(productDetails.getProductName())) {
         throw new ProductAlreadyExistsException("Product already exists");
        }
        else
            {
            ProductDetails savedProducts=productSearchRepository.save(productDetails);
            sendProduct(savedProducts);
            sendToSearch(savedProducts);
            ProductDTO productDTO=new ProductDTO(savedProducts.getProductName(),savedProducts.getRating(),savedProducts.getPrice(),savedProducts.getProductFamily(),savedProducts.getSubCategory());
            sendToRecommendation(productDTO);
            return savedProducts;
        }
    }

    //service implmentation to get all products
    @Cacheable(value="product")
    @Override
    public List<ProductDetails> getAllProducts() {
        return productSearchRepository.findAll();
    }

    //service implementation to delete product
    @CacheEvict(allEntries = true)
    @Override
    public ProductDetails deleteProduct(String productName) throws ProductNotFoundException {

        ProductDetails productDetails3=null;
        Optional optional;
        if (productSearchRepository.existsById(productName))
        {
            optional=productSearchRepository.findById(productName);
            if(optional.isPresent()) {
                productDetails3 = productSearchRepository.findById(productName).get();
                productSearchRepository.deleteById(productName);
                sendRemove(productDetails3);
            }
        }
        else
        {
            throw new ProductNotFoundException("Details not found");
        }
        return null;

    }

    //service implementation to update product
    @CacheEvict(allEntries = true)
    @Override
    public ProductDetails updateProduct(ProductDetails productDetails,String productName) throws ProductNotFoundException {
        ProductDetails productDetails1=null;
        if(productSearchRepository.existsById(productName)) {
            productDetails.setDescription(productDetails.getDescription());
            productDetails1=productSearchRepository.save(productDetails);
        }
        else {
            throw new ProductNotFoundException("Details not found");
        }
        return productDetails1;
    }

    //service implementation for get product by product name
    @Override
    public ProductDetails getProductByName(String productName) throws ProductNotFoundException {
         Optional optional=null;
         optional=productSearchRepository.findById(productName);
         ProductDetails productDetails1=null;
         if(optional.isPresent()) {
              productDetails1=productSearchRepository.findById(productName).get();
          }
        else {
            throw new ProductNotFoundException("Details not found");
          }
        return productDetails1;
    }

    //service implementation to get recent products
    @Cacheable(value="recentproduct")
    @Override
    public List<ProductDetails> getRecentProducts() throws ProductNotFoundException {
        return productSearchRepository.findAll(Sort.by(Sort.Direction.DESC, "uploadedOn"));
    }

    //service implementation get trending products
    @Override
    @Cacheable(value="trendingproduct")
    public List<ProductDetails> getTrendingProducts() throws ProductNotFoundException {
        return productSearchRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }


    @Override
    public void sendProduct(ProductDetails productDetails)
    {

        System.out.println("send to productownerprofile"+productDetails);
        rabbitTemplate.convertAndSend(exchange, routingkeyfour, productDetails);
    }
    @Override
    public void sendToSearch(ProductDetails productDetails)
    {
        System.out.println("send to serach service "+productDetails);
        rabbitTemplate.convertAndSend(exchange, routingkeyeleven, productDetails);
    }

    @Override
    public void sendToRecommendation(ProductDTO productDTO) {
        System.out.println("send to recommendation "+productDTO);
        rabbitTemplate.convertAndSend(exchange, routingkeysix,productDTO);
    }

    @Override
    public void sendRemove(ProductDetails productDetails) {
        rabbitTemplate.convertAndSend(exchange, routingkeynine,productDetails);
    }

    @RabbitListener(queues="${stackroute.rabbitmq.queueeight}")
    public void  recieveRating(ProductRating productRating) {

        ProductDetails productDetails2=null;
        Optional optional;
            optional = productSearchRepository.findById(productRating.getProductName());
            if (optional.isPresent())
             {
                productDetails2 = productSearchRepository.findById(productRating.getProductName()).get();
                productDetails2.setRating(productRating.getRating());
                productSearchRepository.save(productDetails2);
                sendToSearch(productDetails2);
             }
     }

    @Override
    public ProductDetails searchProductByProductOwner(String emailId, String ProductName) {
        ProductDetails productDetails=null;
        Optional optional=null;
        optional=productSearchRepository.findById(ProductName);


          if(optional.isPresent() ){
            productDetails=productSearchRepository.findById(ProductName).get();
            System.out.println(productDetails);
            if(productDetails.getAddedby().equals(emailId)){

                return productDetails;
            }
        }
        return null;
    }
}
