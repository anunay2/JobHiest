package com.stackroute.profileindexing.repository;

import com.stackroute.profileindexing.model.JobSeeker;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface JobSeekerRepository extends Neo4jRepository<JobSeeker,String> {
    public List<JobSeeker> findByEmail(String email);

    //for shortlisting candidate based on location, where recruiter is offering job
    @Query("match (m:Location {preferredLocation:$location})<-[:PREFERS_LOCATION]-(n:JobSeeker) return n")
    public List<JobSeeker> findByLocation(String location);
}
