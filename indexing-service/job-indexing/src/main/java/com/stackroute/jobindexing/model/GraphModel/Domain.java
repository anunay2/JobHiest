package com.stackroute.jobindexing.model.GraphModel;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Domain {

    @Id
    @GeneratedValue
    private Long id;
    private String domainName;

    public Domain() {
    }
    public Domain(String domainName) {
        this.domainName = domainName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domain domain = (Domain) o;
        return Objects.equals(domainName, domain.domainName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domainName);
    }
}
