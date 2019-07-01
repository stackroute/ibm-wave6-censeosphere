package com.stackroute.userloginservice.service;

import com.stackroute.userloginservice.domain.User;
import com.stackroute.userloginservice.exceptions.UserAlreadyExistsException;

import java.util.List;

public interface UserService {

    User findByEmailIdAndPassword(String emailId,String password);
    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();
}