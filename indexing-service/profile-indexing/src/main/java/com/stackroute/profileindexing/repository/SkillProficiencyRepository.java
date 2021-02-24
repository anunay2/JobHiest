package com.stackroute.profileindexing.repository;

import com.stackroute.profileindexing.model.relations.SkillProficiency;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillProficiencyRepository extends Neo4jRepository<SkillProficiency,Long> {
    public SkillProficiency findByJobSeekerEmailAndSkillSkillName(String email,String skillName);

    @Query("MATCH (j:JobSeeker{email: $email})-[r:HAS_SKILLS]->(s:Skill)\n" +
            "DELETE r")
    public void deleteAllByJobSeekerEmail(String email);
}
