package com.stackroute.productOwnerservice.service;

import com.stackroute.productOwnerservice.domain.ProductOwner;
import com.stackroute.productOwnerservice.domain.ProductDetails;
import com.stackroute.productOwnerservice.dto.ProductOwnerDTO;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsAlreadyExistsException;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsNotFoundException;
import com.stackroute.productOwnerservice.repository.ProductOwnerRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductOwnerServiceImpl implements ProductOwnerService {

    private ProductOwnerRepository productownerRepository;

    @Autowired
    public ProductOwnerServiceImpl(ProductOwnerRepository productownerRepository) {
        this.productownerRepository = productownerRepository;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeytwo}")
    private String routingkeytwo;

    //service implementation to save product owner profile
    @Override
    public ProductOwner saveDetails(ProductOwner productowner) throws ProductOwnerDetailsAlreadyExistsException {
        if (productownerRepository.existsById(productowner.getEmailId())) {
            throw new ProductOwnerDetailsAlreadyExistsException("Details already exists");
        }
        List<ProductDetails> myproducts = new ArrayList<ProductDetails>();
        productowner.setProductsadded(myproducts);
        ProductOwner savedDetails = productownerRepository.save(productowner);
        ProductOwnerDTO productOwnerDTO = new ProductOwnerDTO(productowner.getEmailId(), productowner.getReconfirmPassword(), productowner.getRole());
        sendproductOwnner(productOwnerDTO);
        if (savedDetails == null) {
            throw new ProductOwnerDetailsAlreadyExistsException("Details already exists");
        }
        return savedDetails;
    }

    //service implementation to get all product owner
    @Override
    public List<ProductOwner> getAllDetails() {
        return productownerRepository.findAll();
    }

    //service implentation to delete product owner profile by emailId
    @Override
    public ProductOwner deleteDetails(String emailId) throws ProductOwnerDetailsNotFoundException {
        ProductOwner productOwner=null;
        Optional optional=productownerRepository.findById(emailId);
        if(optional.isPresent()) {
            productOwner=productownerRepository.findById(emailId).get();
            productownerRepository.deleteById(emailId);
        } else {
            throw new ProductOwnerDetailsNotFoundException("Details not found");
        }
        return productOwner;
    }

    //service implentattion to get product owner by emailId
    @Override
    public ProductOwner getProductOwnerByEmailId(String emailId) throws ProductOwnerDetailsNotFoundException {
        ProductOwner savedowner = null;
        if (productownerRepository.existsById(emailId)) {
            Optional optional = productownerRepository.findById(emailId);
            if(optional.isPresent()) {
                savedowner = (ProductOwner) optional.get();
            }
        } else {
            throw new ProductOwnerDetailsNotFoundException("productOwner Not found");
        }
        return savedowner;
    }

    //service implementation to update product owner profile
    @Override
    public ProductOwner updateDetails(ProductOwner productowner, String emailId) throws ProductOwnerDetailsNotFoundException {
        ProductOwner productowner1 = null;
        Optional optional;
        optional = productownerRepository.findById(emailId);
        if (optional != null) {
            productowner1 = productownerRepository.findById(emailId).get();
            productowner1.setName(productowner.getName());
            productowner1.setImage(productowner.getImage());
            productowner1.setReconfirmPassword(productowner.getReconfirmPassword());
            productownerRepository.save(productowner1);
            ProductOwnerDTO productOwnerDTO1 = new ProductOwnerDTO(productowner1.getEmailId(), productowner1.getReconfirmPassword(), productowner1.getRole());
            sendproductOwnner(productOwnerDTO1);
        } else {
            throw new ProductOwnerDetailsNotFoundException("Details not found");
        }
        return productowner1;
    }

    //method to send product owner information through rabbitmq
    @Override
    public void sendproductOwnner(ProductOwnerDTO productOwnerDTO) {
        System.out.println("send to authentication service "+ productOwnerDTO);
        rabbitTemplate.convertAndSend(exchange, routingkeytwo, productOwnerDTO);
    }


    @RabbitListener(queues = "${stackroute.rabbitmq.queuenine}")
    public void recieveRemove(ProductDetails productDetails) {
        Optional optional;
        ProductOwner productOwner1;
        optional = productownerRepository.findById(productDetails.getAddedby());
        List<ProductDetails> myproducts;
        if (optional.isPresent()) {
            productOwner1 = productownerRepository.findById(productDetails.getAddedby()).get();
            myproducts = productOwner1.getProductsadded();
            myproducts.remove(productDetails);
            for (int i = 0; i < myproducts.size(); i++) {
                productOwner1.setProductsadded(myproducts);
                productownerRepository.save(productOwner1);
            }
        }
    }

    @RabbitListener(queues = "${stackroute.rabbitmq.queuefour}")
    public void recievproduct (ProductDetails productDetails) {
        System.out.println("from product search service "+productDetails);
        Optional optional;
        ProductOwner productOwner1;
        optional = productownerRepository.findById(productDetails.getAddedby());
        List<ProductDetails> myproducts;
        if (optional.isPresent()) {
            productOwner1 = productownerRepository.findById(productDetails.getAddedby()).get();
            myproducts = productOwner1.getProductsadded();
            myproducts.add(productDetails);
            productOwner1.setProductsadded(myproducts);
            productownerRepository.save(productOwner1);
        }
    }
}
