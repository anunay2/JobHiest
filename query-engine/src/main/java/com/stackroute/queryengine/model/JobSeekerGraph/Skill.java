package com.stackroute.queryengine.model.JobSeekerGraph;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Skill {

    private Long id;
    @Id
    private String skillName;

    public Skill(String skillName){
        this.skillName = skillName;
    }

    public Skill(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = (long)skillName.length();
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillName.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill that = (Skill) o;
        return Objects.equals(skillName.toLowerCase(), that.skillName.toLowerCase());
    }
}
