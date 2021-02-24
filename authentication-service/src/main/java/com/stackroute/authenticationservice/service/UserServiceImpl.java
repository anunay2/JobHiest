package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.exceptions.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exceptions.UserNotFoundException;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private UserRepository repo;

    @Override
    public User fetchUserByEmailId(String email) {
        return repo.findByEmailid(email);

    }
    public User fetchUser(String emailid, String password) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(repo.findByEmailidAndPassword(emailid, password));
        if(!user.isPresent())
            throw new UserNotFoundException("user with this set of credentials not found");
        return user.get();
    }
}