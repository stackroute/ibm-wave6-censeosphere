package com.stackroute.userloginservice.service;

import com.stackroute.userloginservice.dto.ProductOwnerDTO;
import com.stackroute.userloginservice.dto.ReviewerDTO;
import com.stackroute.userloginservice.domain.User;
import com.stackroute.userloginservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userloginservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    private User user;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //method to get user byemailid
    @Override
    public User findByEmailId(String emailId) {
        Optional optional=null;
        optional=userRepository.findById(emailId);
        if(optional.isPresent()) {
            user=userRepository.findById(emailId).get();
        }
        return  user;
    }

    //method to save user
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.existsById(user.getEmailId())) {
            throw new UserAlreadyExistsException("user already exists");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //method to updateuser
    @Override
    public void updateUser(User user) {
      Optional optional;
      User user1=null;
      optional=userRepository.findById(user.getEmailId());
      if(optional.isPresent()) {
          user1=userRepository.findById(user.getEmailId()).get();
          user1.setPassword(user.getPassword());
          userRepository.save(user1);
      }
    }

    //method to recieve the user from  reviewerprofile
    @RabbitListener(queues="${stackroute.rabbitmq.queueone}")
    public  void recievereviewer(ReviewerDTO reviewerdto)throws  UserAlreadyExistsException
    {
        System.out.println("recieved msg from reviewer = " + reviewerdto.toString());
        User user=new User(reviewerdto.getEmailId(),reviewerdto.getReconfirmPassword(),reviewerdto.getRole());
        if(userRepository.existsByEmailId(user.getEmailId())){
            updateUser(user);
        }
        else{
            saveUser(user);
        }
    }

    //method  to recieve  the user from productownerprofile
    @RabbitListener(queues="${stackroute.rabbitmq.queuetwo}")
    public void  recieveproductowner(ProductOwnerDTO productOwnerDTO)throws  UserAlreadyExistsException
    {
        System.out.println("recieved msg  from productowner= " + productOwnerDTO.toString());
        User user=new User(productOwnerDTO.getEmailId(),productOwnerDTO.getReconfirmPassword(),productOwnerDTO.getRole());
        if(userRepository.existsByEmailId(user.getEmailId())){
            updateUser(user);
        }
        else{
            saveUser(user);
        }
    }

}



