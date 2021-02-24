package com.stackroute.authenticationservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stackroute.authenticationservice.config.JWTTokenGenerator;
import com.stackroute.authenticationservice.exceptions.GlobalExceptionHandler;
import com.stackroute.authenticationservice.exceptions.UserAlreadyExistsException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.exceptions.UserNotFoundException;
import com.stackroute.authenticationservice.repository.UserRepository;
import com.stackroute.authenticationservice.service.RabbitMQSender;
import com.stackroute.authenticationservice.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    @NonNull
    private UserService userService;
    @NonNull
    private UserRepository repo;
    @NonNull
    RabbitMQSender rabbitMQSender;
    @NonNull
    private JWTTokenGenerator jwtTokenGenerator;
    ResponseEntity<?> responseEntity;

    @HystrixCommand(fallbackMethod = "loginUserFallback")
    @PostMapping("login")
    //@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        try{
            User user1 = userService.fetchUser(user.getEmailid(), Base64.getEncoder().encodeToString(user.getPassword().getBytes()));

            responseEntity = new ResponseEntity<>(jwtTokenGenerator.generateToken(user1), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @HystrixCommand(fallbackMethod = "registerUserFallback")
    @PostMapping("/registeruser")
    //@CrossOrigin(origins="http://localhost:4200")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws UserAlreadyExistsException{

        if(user.getEmailid() !=null && !"".equals(user.getEmailid())){
            User userobj = userService.fetchUserByEmailId(user.getEmailid());
            if(userobj != null)
                throw new UserAlreadyExistsException("user with " + user.getEmailid() + "already exists");
        }


        System.out.println(user.getRole());
        User userObj=null;
        if(user.getRole() == null){
            throw new UserAlreadyExistsException("role not specified, also frontend tampered");
        }

        user.setCreatedon(Instant.now().getEpochSecond());
        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        userObj= repo.save(user);
        rabbitMQSender.send(user);
        return new ResponseEntity<>(userObj,HttpStatus.CREATED);

    }

//    fallbacks below
    public ResponseEntity<?> loginUserFallback(@RequestBody User user) {
        return new ResponseEntity<>("fallback message, backend database is down", HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<User> registerUserFallback(@RequestBody User user){
        return new ResponseEntity<User>(new User("error", "error"), HttpStatus.EXPECTATION_FAILED);
    }
}
