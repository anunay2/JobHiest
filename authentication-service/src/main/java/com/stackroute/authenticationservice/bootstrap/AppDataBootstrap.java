package com.stackroute.authenticationservice.bootstrap;

import com.stackroute.authenticationservice.controller.UserController;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class AppDataBootstrap implements CommandLineRunner {

    @NonNull
    private UserController controller;
    private User[] users = new User[20];

    @Override
    public void run(String... args) throws Exception {
        //        JOBSEEKERS
        users[0] = new User("sahil@gmail.com", "sahil", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[1] = new User("shubham@gmail.com", "shubham", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[2] = new User("ayush@gmail.com", "ayush", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[3] = new User("anunay@gmail.com", "anunay", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[4] = new User("abhirit@gmail.com", "abhirit", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[5] = new User("abhishek@gmail.com", "abhishek", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[6] = new User("dinesh@gmail.com", "dinesh", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[7] = new User("deepak@gmail.com", "deepak", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[8] = new User("shreya@gmail.com", "shreya", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());
        users[9] = new User("ximanta@gmail.com", "ximanta", "password", User.Role.JOBSEEKER, Instant.now().getEpochSecond());

//        JOBRECRUITERS
        users[10] = new User("sahil@stackroute.com", "sahil", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[11] = new User("shubham@stackroute.com", "shubham", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[12] = new User("ayush@stackroute.com", "ayush", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[13] = new User("anunay@stackroute.com", "anunay", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[14] = new User("abhirit@stackroute.com", "abhirit", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[15] = new User("abhishek@stackroute.com", "abhishek", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[16] = new User("dinesh@stackroute.com", "dinesh", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[17] = new User("deepak@stackroute.com", "deepak", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[18] = new User("shreya@stackroute.com", "shreya", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());
        users[19] = new User("ximanta@stackroute.com", "ximanta", "password", User.Role.RECRUITER, Instant.now().getEpochSecond());

        for(int i=0; i<20; i++){
            controller.registerUser(users[i]);
        }
    }
}