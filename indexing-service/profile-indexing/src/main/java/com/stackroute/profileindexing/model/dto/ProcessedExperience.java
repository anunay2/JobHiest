package com.stackroute.profileindexing.model.dto;

public class ProcessedExperience {
    private int id;
    private String role;
    private String organisation;
    private String domain;
    private String responsibilities;
    private String project;
    private String startDate;
    private String endDate;
    private int months;

    public ProcessedExperience() {
    }

    public ProcessedExperience(int id, String role, String organisation, String domain, String responsibilities, String project, String startDate, String endDate, int months) {
        this.id = id;
        this.role = role;
        this.organisation = organisation;
        this.domain = domain;
        this.responsibilities = responsibilities;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
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
}
