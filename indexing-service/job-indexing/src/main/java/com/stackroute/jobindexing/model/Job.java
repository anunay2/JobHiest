package com.stackroute.jobindexing.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "Job")
public class Job {
    @Id
    @GeneratedValue
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private String organisation;
    private String orgDescription;
    private String workingLocation;
    private Pair salary;
    private DesiredCandidateProfile candidateProfile;

    private String recruiterEmail;
    private Long createdOn;

    public Job() {
    }

//    constructor for fallback purpose


    public Job(String jobTitle, String jobDescription) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

    public Job(String jobTitle, String jobDescription, String organisation, String orgDescription, String workingLocation, Pair salary, DesiredCandidateProfile candidateProfile) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.organisation = organisation;
        this.orgDescription = orgDescription;
        this.workingLocation = workingLocation;
        this.salary = salary;
        this.candidateProfile = candidateProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getWorkingLocation() {
        return workingLocation;
    }

    public void setWorkingLocation(String workingLocation) {
        this.workingLocation = workingLocation;
    }

    public Pair getSalary() {
        return salary;
    }

    public void setSalary(Pair salary) {
        this.salary = salary;
    }

    public DesiredCandidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public void setCandidateProfile(DesiredCandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }
    public String getRecruiterEmail() {
        return recruiterEmail;
    }

    public void setRecruiterEmail(String recruiterEmail) {
        this.recruiterEmail = recruiterEmail;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Long createdOn) {
        this.createdOn = createdOn;
    }

}

