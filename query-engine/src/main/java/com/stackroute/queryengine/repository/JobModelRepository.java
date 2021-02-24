package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.model.JobModel.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobModelRepository extends MongoRepository<Job,Long> {
}
