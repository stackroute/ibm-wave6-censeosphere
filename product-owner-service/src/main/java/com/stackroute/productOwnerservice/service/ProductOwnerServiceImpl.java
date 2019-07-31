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
        List<ProductDetails> myproducts=new ArrayList<ProductDetails>();
        productowner.setProductsadded(myproducts);
        ProductOwner savedDetails=productownerRepository.save(productowner);

        ProductOwnerDTO productOwnerDTO=new ProductOwnerDTO(productowner.getEmailId(),productowner.getReconfirmPassword(),productowner.getRole());
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
        ProductOwner productOwner=null;
        Optional optional=productownerRepository.findById(emailId);

       //System.out.println(productOwner+"\t"+optional.isPresent());
        if(optional.isPresent())
        {
            productOwner=productownerRepository.findById(emailId).get();
            productownerRepository.deleteById(emailId);
        }
        else
        {
            throw new ProductOwnerDetailsNotFoundException("Details not found");
        }
        return productOwner;

    }

    @Override
    public ProductOwner getProductOwnerByEmailId(String emailId) throws ProductOwnerDetailsNotFoundException {
        ProductOwner savedowner=null;
        if(productownerRepository.existsById(emailId)){
            Optional optional=productownerRepository.findById(emailId);
            if(optional.isPresent()) {
                savedowner = (ProductOwner) optional.get();
            }
        }
        else {
            throw new ProductOwnerDetailsNotFoundException("productOwner Not found");
        }
        return savedowner;
    }

    @Override
    public ProductOwner updateDetails(ProductOwner productowner,String emailId) throws ProductOwnerDetailsNotFoundException {
        ProductOwner productowner1=null;
        Optional optional;
        optional=productownerRepository.findById(emailId);
        if(optional != null)
        {
            productowner1=productownerRepository.findById(emailId).get();

            System.out.println("from update method "+productowner1);
            productowner1.setName(productowner.getName());
            productowner1.setImage(productowner.getImage());
            productowner1.setReconfirmPassword(productowner.getReconfirmPassword());

            System.out.println("After updating "+productowner1);
            productownerRepository.save(productowner1);

            ProductOwnerDTO productOwnerDTO1=new ProductOwnerDTO(productowner1.getEmailId(),productowner1.getReconfirmPassword(),productowner1.getRole());
            sendproductOwnner(productOwnerDTO1);

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
    @RabbitListener(queues="${stackroute.rabbitmq.queuenine}")
    public  void recieveRemove(ProductDetails productDetails)
    {
        System.out.println("recieved msg from delete function = " + productDetails.toString());
        System.out.println(productDetails);
        Optional optional;
        ProductOwner productOwner1;
        optional=productownerRepository.findById(productDetails.getAddedby());
        List<ProductDetails> myproducts;
        if(optional.isPresent())
        {
            System.out.println("hai");
            productOwner1=productownerRepository.findById(productDetails.getAddedby()).get();
            System.out.println("Reviewer 1:"+productOwner1);

            myproducts =productOwner1.getProductsadded();
            System.out.println("list "+myproducts);
//            myproducts=new ArrayList<>();
            myproducts.remove(productDetails);
            for (int i = 0; i < myproducts.size(); i++) {
                System.out.println("inside list"+myproducts.get(i));
            }
            productOwner1.setProductsadded(myproducts);
            System.out.println(productOwner1);
            productownerRepository.save(productOwner1);
        }
    }


    @RabbitListener(queues="${stackroute.rabbitmq.queuefour}")
    public  void recievproduct(ProductDetails productDetails)
    {
        System.out.println("recieved msg from reviewer = " + productDetails.toString());
        System.out.println(productDetails);
        Optional optional;
        ProductOwner productOwner1;
        optional=productownerRepository.findById(productDetails.getAddedby());
        List<ProductDetails> myproducts;


        if(optional.isPresent())
        {
            System.out.println("hai");
            productOwner1=productownerRepository.findById(productDetails.getAddedby()).get();
            System.out.println("Reviewer 1:"+productOwner1);

            myproducts =productOwner1.getProductsadded();
            System.out.println("list "+myproducts);
//            myproducts=new ArrayList<>();
            myproducts.add(productDetails);
            for (int i = 0; i < myproducts.size(); i++) {
                System.out.println("inside list"+myproducts.get(i));
            }
            productOwner1.setProductsadded(myproducts);
            System.out.println(productOwner1);
            productownerRepository.save(productOwner1);
        }
    }

}
