package com.stackroute.productOwnerservice.service;

import com.stackroute.productOwnerservice.domain.ProductOwner;
import com.stackroute.productOwnerservice.dto.ProductOwnerDTO;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsAlreadyExistsException;
import com.stackroute.productOwnerservice.exception.ProductOwnerDetailsNotFoundException;
import com.stackroute.productOwnerservice.repository.ProductOwnerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOwnerServiceImpl implements ProductOwnerService {

    @Autowired
    private ProductOwnerRepository productownerRepository;

    @Autowired
    public ProductOwnerServiceImpl(ProductOwnerRepository productownerRepository)
    {
        this.productownerRepository=productownerRepository;
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${stackroute.rabbitmq.exchange}")
    private String exchange;

    @Value("${stackroute.rabbitmq.routingkeytwo}")
    private String routingkeytwo;

    @Override
    public ProductOwner saveDetails(ProductOwner productowner) throws ProductOwnerDetailsAlreadyExistsException {
        if(productownerRepository.existsById(productowner.getEmailId()))
        {
            throw new ProductOwnerDetailsAlreadyExistsException("Details already exists");
        }
        ProductOwner savedDetails=productownerRepository.save(productowner);

        ProductOwnerDTO productOwnerDTO=new ProductOwnerDTO(productowner.getEmailId(),productowner.getConfirmPassword(),productowner.getRole());
        sendproductOwnner(productOwnerDTO);

        if(savedDetails==null)
        {
            throw new ProductOwnerDetailsAlreadyExistsException("Details already exists");
        }

        return savedDetails;
    }

    @Override
    public List<ProductOwner> getAllDetails() {
        return productownerRepository.findAll();
    }

    @Override
    public ProductOwner deleteDetails(String emailId) throws ProductOwnerDetailsNotFoundException {

        Optional optional=productownerRepository.findById(emailId);
        ProductOwner productOwner=productownerRepository.findById(emailId).get();
       // System.out.println(productOwner+"\t"+optional.isPresent());
        if(optional.isPresent())
        {
            productownerRepository.deleteById(emailId);
        }
        else
        {
            throw new ProductOwnerDetailsNotFoundException("Details not found");
        }
        return productOwner;

    }

    @Override
    public ProductOwner updateDetails(ProductOwner productowner) throws ProductOwnerDetailsNotFoundException {
        ProductOwner productowner1=null;
        if(productownerRepository.existsById(productowner.getEmailId()))
        {
            productowner.setName(productowner.getName());
            productowner1=productownerRepository.save(productowner);

        }
        else
        {
            throw new ProductOwnerDetailsNotFoundException("Details not found");
        }

        return productowner1;
    }

    @Override
    public void sendproductOwnner(ProductOwnerDTO productOwnerDTO) {

        rabbitTemplate.convertAndSend(exchange, routingkeytwo, productOwnerDTO);
        System.out.println("Send msg = " + productOwnerDTO.toString());
    }
}
