package com.stackroute.userloginservice.controller;

import com.stackroute.userloginservice.domain.User;
import com.stackroute.userloginservice.exceptions.PasswordNotMatchException;
import com.stackroute.userloginservice.exceptions.UserAlreadyExistsException;
import com.stackroute.userloginservice.exceptions.UserNameOrPasswordEmptyException;
import com.stackroute.userloginservice.exceptions.UserNotFoundException;
import com.stackroute.userloginservice.jwt.TokenGenerator;
import com.stackroute.userloginservice.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //method to generate token for reviewer and product owner profile
    @PostMapping("/user")
    public ResponseEntity<?>  login(@RequestBody User loginDetails) throws UserNameOrPasswordEmptyException, UserNotFoundException, PasswordNotMatchException {
        String userName = loginDetails.getEmailId();
        String password = loginDetails.getPassword();
        if (userName == null || password == null) {
            throw new UserNameOrPasswordEmptyException();
        }
        User user = userService.findByEmailId(userName);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String fetchedPassword = user.getPassword();
        if (!password.equals(fetchedPassword)) {
            throw new PasswordNotMatchException();
        }
        TokenGenerator tokenGenrator = (User userDetails) -> {
            String jwtToken = "";
            Map<String, String> map1 = new HashMap<>();
            jwtToken = Jwts.builder().setId(""+user.getEmailId()).setIssuedAt(new Date()).setSubject(user.getRole())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
            map1.put("token", jwtToken);
            map1.put("message", "User successfully logged in");
            return map1;
        };
        Map<String, String> map = tokenGenrator.generateToken(user);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    //method to save the user
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException ex) {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //method to get the list of users
    @GetMapping("/getuser")
    public ResponseEntity<List<User>> getAllUsers() {
           return new ResponseEntity<List<User>> (userService.getAllUsers(),HttpStatus.OK);
    }
}
