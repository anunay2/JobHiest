package com.stackroute.jobprofileservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "experience")
public class Experience{


    String role;
    String organisation;
    String domain;
    String responsibilities;
    //@OneToMany(cascade=CascadeType.ALL)
    String project;
    String startDate;
    String endDate;
    int months;


    public Experience(String role, String organisation, String domain, String  project, String responsibilities, String startDate,String endDate,int months) {

        this.role = role;
        this.organisation = organisation;
        this.domain = domain;
        this.project = project;
        this.responsibilities = responsibilities;
        this.startDate = startDate;
        this.endDate=endDate;
        this.months=months;
    }

    public Experience(){

    }

    @Override
    public String toString() {
        return "Experience{" +

                ", role='" + role + '\'' +
                ", organisation='" + organisation + '\'' +
                ", domain='" + domain + '\'' +
                ", responsibilities='" + responsibilities + '\'' +
                ", project='" + project + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", months=" + months +
                '}';
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }



    public  String  getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities( String  responsibilities) {
        this.responsibilities = responsibilities;
    }


}
