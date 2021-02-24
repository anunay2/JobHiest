package com.stackroute.queryengine.model.JobSeekerGraph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class WorkingExperience {
    @Id
    @GeneratedValue
    private Long id;
    private Double totalExperience;

    public WorkingExperience(){}

    public WorkingExperience(Double totalExperience) {
        this.totalExperience = totalExperience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(Double totalExperience) {
        this.totalExperience = totalExperience;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalExperience);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingExperience that = (WorkingExperience) o;
        return Objects.equals(totalExperience, that.totalExperience);

    }
}
