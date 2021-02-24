package com.stackroute.jobprofileservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


//@Document(collection = "education")
public class Education{


    String school;
    String degree;
    String field;
    int startyear;
    int endyear;

    public Education(){

    }

    public Education( String school, String degree, String field, int startyear, int endyear) {
        this.school = school;
        this.degree = degree;
        this.field = field;
        this.startyear = startyear;
        this.endyear = endyear;
    }



    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getStartyear() {
        return startyear;
    }

    public void setStartyear(int startyear) {
        this.startyear = startyear;
    }

    @Override
    public String toString() {
        return "Education{" +

                ", school='" + school + '\'' +
                ", degree='" + degree + '\'' +
                ", field='" + field + '\'' +
                ", startyear=" + startyear +
                ", endyear=" + endyear +
                '}';
    }

    public int getEndyear() {
        return endyear;
    }

    public void setEndyear(int endyear) {
        this.endyear = endyear;
    }


}

