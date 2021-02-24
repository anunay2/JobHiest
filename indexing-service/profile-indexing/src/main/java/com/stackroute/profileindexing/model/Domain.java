package com.stackroute.profileindexing.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Domain {

    private Long id;
    @Id
    private String domainName;

    public Domain(){}

    public Domain(String domainName) {
        this.domainName = domainName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = Long.valueOf(domainName.length());
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainName.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domain that = (Domain) o;
        return Objects.equals(domainName.toLowerCase(), that.domainName.toLowerCase());
    }
}

