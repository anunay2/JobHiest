package com.stackroute.jobprofileservice.repository;

import com.stackroute.jobprofileservice.model.JobSeekerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;

@Repository
public interface JobSeekerProfileRepository extends MongoRepository< JobSeekerProfile,Integer> {
      public JobSeekerProfile findByEmail(String email);
}
