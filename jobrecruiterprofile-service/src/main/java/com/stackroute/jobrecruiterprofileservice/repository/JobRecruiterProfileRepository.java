package com.stackroute.jobrecruiterprofileservice.repository;

import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRecruiterProfileRepository extends MongoRepository<JobRecruiterProfile,Integer> {
    public JobRecruiterProfile findByEmail(String email);
}
