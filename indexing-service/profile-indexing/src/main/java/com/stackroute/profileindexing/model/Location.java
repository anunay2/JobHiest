package com.stackroute.profileindexing.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Location {

    private Long id;
    @Id
    private String preferredLocation;

    public Location(){}

    public Location(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = Long.valueOf(preferredLocation.length());
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferredLocation.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return Objects.equals(preferredLocation.toLowerCase(), that.preferredLocation.toLowerCase());

    }
}

