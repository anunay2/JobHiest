package com.stackroute.profileindexing.model.relations;

import com.stackroute.profileindexing.model.JobSeeker;
import com.stackroute.profileindexing.model.Skill;
import com.stackroute.profileindexing.model.dto.Proficiency;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_SKILLS")
public class SkillProficiency {

    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private JobSeeker jobSeeker;
    @EndNode
    private Skill skill;
    
    @Property
    private Proficiency with_proficiency;

    public SkillProficiency() {
    }

    public SkillProficiency(Skill skill, JobSeeker jobSeeker, Proficiency with_proficiency) {
        this.skill = skill;
        this.jobSeeker = jobSeeker;
        this.with_proficiency = with_proficiency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Proficiency getWith_proficiency() {
        return with_proficiency;
    }

    public void setWith_proficiency(Proficiency with_proficiency) {
        this.with_proficiency = with_proficiency;
    }
}

