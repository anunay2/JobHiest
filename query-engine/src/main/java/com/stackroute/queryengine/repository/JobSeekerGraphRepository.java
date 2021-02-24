package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.model.JobSeekerGraph.JobSeeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerGraphRepository extends Neo4jRepository<JobSeeker,String> {
    public List<JobSeeker> findByEmail(String email);
    public List<JobSeeker> findBySkillsPossessedSkillName(String skillName);
    public List<JobSeeker> findByLocationsPreferredLocation(String locationName);


    @Query(
            value="MATCH (J:JobSeeker)-[HS:HAS_SKILLS]->(S:Skill) " +
                    "WHERE S.skillName IN $skills " +
                    "RETURN J, count(HS) " +
                    "ORDER BY count(HS) DESC",
            countQuery = "MATCH (J:JobSeeker)-[HS:HAS_SKILLS]->(S:Skill) " +
                    "WHERE S.skillName IN $skills " +
                    "RETURN count(J) "

    )
    Page<JobSeeker> findRecommendedProfiles(@Param("skills") List<String> skills, Pageable pageable);
}
