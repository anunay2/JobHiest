package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exceptions.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exceptions.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;

public interface UserService {
    public User fetchUserByEmailId(String email);
    public User fetchUser(String emailid, String password) throws UserNotFoundException;
}
