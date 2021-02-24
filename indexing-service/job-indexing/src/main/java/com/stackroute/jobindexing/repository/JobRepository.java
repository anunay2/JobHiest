package com.stackroute.jobindexing.repository;

import com.stackroute.jobindexing.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends MongoRepository<Job,Long> {
    List<Job> findByRecruiterEmail(String email);
}
