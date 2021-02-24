package com.stackroute.queryengine.model.JobGraph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Experience {

    @Id
    @GeneratedValue
    private Long id;

    private int minExperience;
    private int maxExperience;

    public Experience() {
    }

    public Experience(int minExperience, int maxExperience) {
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(int maxExperience) {
        this.maxExperience = maxExperience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return minExperience == that.minExperience &&
                maxExperience == that.maxExperience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minExperience, maxExperience);
    }
}