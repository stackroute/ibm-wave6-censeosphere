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

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmailIdAndPassword(String emailId, String password) {
        return userRepository.findByEmailIdAndPassword(emailId, password);
    }

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

    @RabbitListener(queues="${stackroute.rabbitmq.queueone}")
    public  void recievereviewer(ReviewerDTO reviewerdto)throws  UserAlreadyExistsException
    {
        System.out.println("recieved msg from reviewer = " + reviewerdto.toString());
        User user=new User(reviewerdto.getEmailId(),reviewerdto.getReconfirmPassword(),reviewerdto.getRole());
        saveUser(user);
    }
    @RabbitListener(queues="${stackroute.rabbitmq.queuetwo}")
    public void  recieveproductowner(ProductOwnerDTO productOwnerDTO)throws  UserAlreadyExistsException
    {

        System.out.println("recieved msg  from productowner= " + productOwnerDTO.toString());
        User user=new User(productOwnerDTO.getEmailId(),productOwnerDTO.getReconfirmPassword(),productOwnerDTO.getRole());
        saveUser(user);
    }

}



