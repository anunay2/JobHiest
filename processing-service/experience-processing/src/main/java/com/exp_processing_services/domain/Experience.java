package com.exp_processing_services.domain;//package com.jobheist.demo.model;

//import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

@Component
public class Experience {

    int id;
    String role;
    String organisation;
    String domain;
    String responsibilities;
    String project;
    String startDate;
    String endDate;
    int months;


    public Experience(int id, String role, String organisation, String domain, String  project, String responsibilities, String startDate, String endDate, int months) {
        this.id=id;
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
                "id=" + id +
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
