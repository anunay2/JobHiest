package com.stackroute.jobindexing.model.GraphModel;

import com.stackroute.jobindexing.model.Pair;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;

@NodeEntity
public class Salary {

    @Id
    @GeneratedValue
    private Long id;
    private int minSalary;
    private int maxSalary;

    public Salary() {
    }

    public Salary(int minSalary, int maxSalary) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return minSalary == salary.minSalary &&
                maxSalary == salary.maxSalary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minSalary, maxSalary);
    }
}
