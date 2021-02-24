package com.stackroute.jobindexing.model.GraphModel;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class JobGraph {

    @Id
    @GeneratedValue
    private Long id;

    private String jobName;
    private String recruiterEmail;
    private String organisation;
    private Long createdOn;

    private JobGraph(){}

    public JobGraph(String jobName,String recruiterEmail,String organisation,Long createdOn)
    {
        this.jobName=jobName;
        this.recruiterEmail=recruiterEmail;
        this.organisation=organisation;
        this.createdOn=createdOn;
    }



    @Relationship(type="IS_AT", direction=Relationship.UNDIRECTED)
    public Set<WorkingLocation> locationsAvailable; // can have one job at multiple locations

    @Relationship(type="REQUIRES_EXP_IN", direction=Relationship.UNDIRECTED)
    public Set<Role> rolesRequired;

    @Relationship(type="REQUIRES_EXP_OF", direction = Relationship.UNDIRECTED)
    public Experience experienceRequired;

    @Relationship(type="PREFERRED_AGE_IS",direction = Relationship.UNDIRECTED)
    public Age ageRangeRequired;

    @Relationship(type="REQUIRES_SKILLS",direction = Relationship.UNDIRECTED)
    public Set<SkillNode> skillsRequired;

    @Relationship(type="PAY_IS",direction = Relationship.UNDIRECTED)
    public Salary expectedSalary;

    @Relationship(type="IS_IN",direction = Relationship.UNDIRECTED)
    public Set<Domain> domainRequired;


    public void isLocatedAt(WorkingLocation location)
    {
        if(locationsAvailable==null)
        {
            locationsAvailable=new HashSet<>();
        }
        locationsAvailable.add(location);
    }
    public void requiresRole(Role role)
    {
        if(rolesRequired==null)
        {
            rolesRequired=new HashSet<>();
        }
        rolesRequired.add(role);
    }
    public void requiresExperience(Experience experience)
    {
        experienceRequired= experience;
    }
    public void requiresAgeRange(Age age)
    {
        ageRangeRequired=age;
    }
    public void requiresSkill(SkillNode skillNode)
    {
        if(skillsRequired==null)
        {
            skillsRequired=new HashSet<>();
        }
        skillsRequired.add(skillNode);
    }
    public void setExpectedSalary(Salary salary)
    {
        expectedSalary=salary;
    }
    public void requiresDomain(Domain domain)
    {
        if(domainRequired==null)
        {
            domainRequired=new HashSet<>();
        }
        domainRequired.add(domain);
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", jobName='" + jobName + '\'' +
                ", cities=" + locationsAvailable +
                '}';
    }

    public Set<WorkingLocation> getLocationsAvailable() {
        return locationsAvailable;
    }

    public void setLocationsAvailable(Set<WorkingLocation> locationsAvailable) {
        this.locationsAvailable = locationsAvailable;
    }

    public Set<SkillNode> getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(Set<SkillNode> skillsRequired) {
        this.skillsRequired = skillsRequired;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobGraph jobGraph = (JobGraph) o;
        return jobName.equals(jobGraph.jobName) &&
                recruiterEmail.equals(jobGraph.recruiterEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobName, recruiterEmail);
    }
}