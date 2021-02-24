package com.stackroute.jobindexing.model;

import java.util.List;

public class DesiredCandidateProfile {

    private Pair totalExperience;
    private List<String> domains;
    private List<String> roles;
    private Pair age;
    private List<Skill> skill;

    public DesiredCandidateProfile() {
    }

    public DesiredCandidateProfile(Pair totalExperience, List<String> domains, List<String> roles, Pair age, List<Skill> skill) {
        this.totalExperience = totalExperience;
        this.domains = domains;
        this.roles = roles;
        this.age = age;
        this.skill = skill;
    }

    public Pair getTotalExperience() {
        return totalExperience;
    }

    public void setTotalExperience(Pair totalExperience) {
        this.totalExperience = totalExperience;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Pair getAge() {
        return age;
    }

    public void setAge(Pair age) {
        this.age = age;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
}
