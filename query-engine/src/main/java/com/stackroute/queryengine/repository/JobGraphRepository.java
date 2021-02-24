package com.stackroute.queryengine.repository;

import com.stackroute.queryengine.model.JobGraph.JobGraph;
import com.stackroute.queryengine.model.JobGraph.SkillNode;
import com.stackroute.queryengine.model.JobGraph.WorkingLocation;
import com.stackroute.queryengine.model.JobModel.Job;
import com.stackroute.queryengine.model.JobSeekerGraph.JobSeeker;
import com.stackroute.queryengine.model.JobSeekerGraph.Location;
import com.stackroute.queryengine.model.JobSeekerGraph.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface JobGraphRepository extends Neo4jRepository<JobGraph,Long> {

    Page<JobGraph> findAll(Pageable pageable);

    List<JobGraph> findBySkillsRequiredSkillName(String skillName);
    List<JobGraph> findByLocationsAvailableName(String locationName);
    Page<JobGraph> findByRecruiterEmail(String email,Pageable pageable);

    Page<JobGraph> findByJobNameContaining(String jobName,Pageable pageable);
    @Query(
            value="MATCH (J:JobGraph)-[RS:REQUIRES_SKILLS]->(S:SkillNode) , "+
                    "(J:JobGraph)-[L:IS_AT]->(Loc:WorkingLocation) "+
            "WHERE S.skillName IN $skills or Loc.name IN $locations "+
            "RETURN J, count(RS), count(L) "+
            "ORDER BY count(RS) DESC, count(L) DESC",
            countQuery = "MATCH (J:JobGraph)-[RS:REQUIRES_SKILLS]->(S:SkillNode) , "+
                    "(J:JobGraph)-[L:IS_AT]->(Loc:WorkingLocation)"+
                    "WHERE S.skillName IN $skills or Loc.name IN $locations "+
                    "RETURN count(J)"
    )
    Page<JobGraph> findRecommendedJobs(@Param("skills")List<String> skills,@Param("locations") List<String> locations,Pageable pageable);



    @Query(value="MATCH(J:JobGraph) WHERE toLower(J.jobName) CONTAINS $title " +
            "OR exists{" +
            "(J)-[:REQUIRES_SKILLS]->(S:SkillNode) " +
            "where toLower(S.skillName)=$skill " +
            "} " +
            "OR exists{ " +
            "(J)-[:IS_AT]->(L:WorkingLocation) " +
            "where toLower(L.name)=$location " +
            "} " +
            "RETURN J "+
            "ORDER BY J.createdOn desc  ",
            countQuery ="MATCH(J:JobGraph) WHERE toLower(J.jobName) CONTAINS $title " +
                    "OR exists{" +
                    "(J)-[:REQUIRES_SKILLS]->(S:SkillNode) " +
                    "where toLower(S.skillName)=$skill " +
                    "} " +
                    "OR exists{ " +
                    "(J)-[:IS_AT]->(L:WorkingLocation) " +
                    "where toLower(L.name)=$location " +
                    "} " +
            "RETURN count(J) "
                     )
    Page<JobGraph> searchByTitleLocationSkill(@Param("title") String title,
                                              @Param("skill") String skill,
                                              @Param("location") String location,
                                              Pageable pageable
                                              );


}
