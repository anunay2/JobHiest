package com.stackroute.queryengine.model.JobModel;

import org.neo4j.ogm.annotation.Id;

import java.util.Objects;

public class Pair {
    @Id
    private long id;
    private int minValue;
    private int maxValue;



    public Pair() {
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pair(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return minValue == pair.minValue &&
                maxValue == pair.maxValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(minValue, maxValue);
    }
}
