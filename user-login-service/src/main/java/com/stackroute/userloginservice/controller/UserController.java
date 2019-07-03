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

@RestController
@RequestMapping(value="api/user")
public class UserController {
        UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        @PostMapping("/user")
        public ResponseEntity<?>  login(@RequestBody User loginDetails) throws UserNameOrPasswordEmptyException, UserNotFoundException, PasswordNotMatchException {

            String userName = loginDetails.getEmailId();
            String password = loginDetails.getPassword();

            if (userName == null || password == null) {

                throw new UserNameOrPasswordEmptyException();
            }

            User user = userService.findByEmailIdAndPassword(userName,password);

            if (user == null) {
                throw new UserNotFoundException();
            }

            String fetchedPassword = user.getPassword();

            if (!password.equals(fetchedPassword)) {
                throw new PasswordNotMatchException();
            }

            TokenGenerator tokenGenrator = (User userDetails) -> {
                String jwtToken = "";

                jwtToken = Jwts.builder().setId(""+user.getEmailId()).setIssuedAt(new Date())

                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();

                Map<String, String> map1 = new HashMap<>();

                map1.put("token", jwtToken);

                map1.put("message", "User successfully logged in");

                return map1;

            };
            Map<String, String> map = tokenGenrator.generateToken(user);
            return new ResponseEntity<>(map, HttpStatus.OK);

        }
    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody User user)
    {
        System.out.println("***********");
        ResponseEntity responseEntity;

        try
        {
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);

        }
        return responseEntity;
    }


  @GetMapping("/getuser")
    public ResponseEntity<?> getAllUsers() {
           return new ResponseEntity<List<User>> (userService.getAllUsers(),HttpStatus.OK);
        }
}
