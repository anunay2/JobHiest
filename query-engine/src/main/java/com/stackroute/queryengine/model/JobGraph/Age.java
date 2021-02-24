package com.stackroute.queryengine.model.JobGraph;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Age {

    @Id
    @GeneratedValue
    private Long id;
    private int minAge;
    private int maxAge;

    public Age() {
    }

    public Age(int minAge,int maxAge) {
        this.minAge=minAge;
        this.maxAge=maxAge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age = (Age) o;
        return minAge == age.minAge &&
                maxAge == age.maxAge;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minAge, maxAge);
    }
}