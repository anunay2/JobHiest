package com.stackroute.profileindexing.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class JobSeeker {

    
    @Id
    private String email;
    private String dob;
    private String username;
    private Gender gender;
    private double total_exp;

    public double getTotal_exp() {
        return total_exp;
    }

    public void setTotal_exp(double total_exp) {
        this.total_exp = total_exp;
    }
    
    @Relationship(type="PREFERS_LOCATION", direction=Relationship.UNDIRECTED)
    private Set<Location> locations = new HashSet<>(); // can prefer multiple locations

    @Relationship(type="HAS_ROLE_OF", direction=Relationship.UNDIRECTED)
    private Set<Role> rolesWorkedIn = new HashSet<>();

    public JobSeeker(WorkingExperience experiencedIn) {
        this.experiencedIn = experiencedIn;
    }

    @Relationship(type="HAS_EXP_OF", direction = Relationship.UNDIRECTED)
    private WorkingExperience experiencedIn;

    @Relationship(type="HAS_SKILLS")
    private Set<Skill> skillsPossessed = new HashSet<>();

    @Relationship(type="IS_SKILLED_IN",direction = Relationship.UNDIRECTED)
    private Set<Domain> domainsWorked = new HashSet<>();

    public void removeExperiences(){
        rolesWorkedIn.clear();
        domainsWorked.clear();
    }

    public void prefersLocationIn(Location location){
        if(location!=null){
            if(location.getPreferredLocation() != null){
                locations.clear();
                locations.add(location);
            }
        }

    }

    public void addNewRole(Role role){
        if(role!=null)
            if(role.getRoleName()!=null)
                rolesWorkedIn.add(role);
    }

    public void hasExperience(WorkingExperience workingExperience){
        experiencedIn = workingExperience;
    }

    public void addNewDomain(Domain domain){
        if(domain!=null)
            if(domain.getDomainName()!=null)
                domainsWorked.add(domain);
    }

    public void addNewSkill(Skill skill){
        skillsPossessed.add(skill);
    }

    

    public Set<Location> getLocations() {
        return locations;
    }

    public Set<Role> getRolesWorkedIn() {
        return rolesWorkedIn;
    }

    public WorkingExperience getExperiencedIn() {
        return experiencedIn;
    }

    public Set<Skill> getSkillsPossessed() {
        return skillsPossessed;
    }

    public Set<Domain> getDomainsWorked() {
        return domainsWorked;
    }

    public JobSeeker() {
    }

    public JobSeeker(String email, String dob, String username, Gender gender) {
        this.email = email;
        this.dob = dob;
        this.username = username;
        this.gender = gender;
    }

//    constructor for fallback purposes

    public JobSeeker(String dob, String username) {

        this.dob = dob;
        this.username = username;
    }


    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
