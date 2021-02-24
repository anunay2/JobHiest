package com.stackroute.jobindexing.repository;

import com.stackroute.jobindexing.model.GraphModel.JobGraph;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobGraphRepository extends Neo4jRepository<JobGraph,Long> {
}
