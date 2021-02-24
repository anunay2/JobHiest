package com.stackroute.jobindexing.model.GraphModel;

import com.stackroute.jobindexing.model.Level;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class SkillNode {

    @Id
    @GeneratedValue
    private Long id;

    private String skillName;
    private Level proficiency;

    public SkillNode() {
    }

    public SkillNode(String skillName, Level proficiency) {
        this.skillName = skillName;
        this.proficiency = proficiency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Level getProficiency() {
        return proficiency;
    }

    public void setProficiency(Level proficiency) {
        this.proficiency = proficiency;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", skillName='" + skillName + '\'' +
                ", proficiency='" + proficiency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillNode skillNode = (SkillNode) o;
        return skillName.equals(skillNode.skillName) &&
                proficiency.equals(skillNode.proficiency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillName, proficiency);
    }
}
