package com.stackroute.profileindexing.service;

import com.stackroute.profileindexing.model.*;
import com.stackroute.profileindexing.model.dto.JobSeekerExperienceInfo;
import com.stackroute.profileindexing.model.dto.PersonalInfo;
import com.stackroute.profileindexing.model.dto.ProcessedExperience;
import com.stackroute.profileindexing.model.dto.ProcessedSkill;
import com.stackroute.profileindexing.model.relations.SkillProficiency;
import com.stackroute.profileindexing.repository.JobSeekerRepository;
import com.stackroute.profileindexing.repository.SkillProficiencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerIndexingService {

    private JobSeekerRepository jobSeekerRepo;
    private SkillProficiencyRepository skillProficiencyRepo;

    public JobSeekerIndexingService(){}

    @Autowired
    public JobSeekerIndexingService(JobSeekerRepository jobSeekerRepo, SkillProficiencyRepository skillProficiencyRepo) {
        this.jobSeekerRepo = jobSeekerRepo;
        this.skillProficiencyRepo = skillProficiencyRepo;
    }

    public void personalInfo(PersonalInfo personalInfo){

        //Check whether job seeker is already present or not and write an if condition
        JobSeeker jobSeeker = new JobSeeker(personalInfo.getEmail(),personalInfo.getDob(), personalInfo.getName(), personalInfo.getGender());
        Location preferred = new Location(personalInfo.getPreferredLocation());
        jobSeeker.prefersLocationIn(preferred);
        //jobSeekerRepo.save(jobSeeker);

        // uncomment if duplicates are forming
        if(jobSeeker.getUsername()!=null){
            jobSeekerRepo.save(jobSeeker);
        }
        //Query to Create Relationhip between jobseeker and preferred location
        //if location already exists, just create a relation
    }

    public void experienceInfo(JobSeekerExperienceInfo processedExperience) {
        //fetch Job Seeker through emailId
        JobSeeker jobSeeker;
        Role role;
        Domain domain;
        //WorkingExperience workingExperience = new WorkingExperience((double)processedExperience.getTotalExperience());
        jobSeeker = jobSeekerRepo.findByEmail(processedExperience.getEmail()).get(0); //Check if optional or not
        if(jobSeeker == null){
            jobSeeker = new JobSeeker();
            jobSeeker.setEmail(processedExperience.getEmail());
        }else{
            jobSeeker.removeExperiences();
        }
        jobSeeker.setTotal_exp(processedExperience.getTotalExperience());
        for(ProcessedExperience experience : processedExperience.getExperiences()){
            role = new Role(experience.getRole());
            domain = new Domain(experience.getDomain());
            jobSeeker.addNewRole(role);
            jobSeeker.addNewDomain(domain);
        }
        jobSeekerRepo.save(jobSeeker);

        //Create a WorkExperience Node if its not present and then create a relation
        //Create a Domain Node if its not present and then create a relation
        //Create a Role Node if its not present and then create a relation

    }

    public void skillInfo(ProcessedSkill skillMap){
        JobSeeker jobSeeker;
        Skill newSkill;
        SkillProficiency proficiencyLevel;
        jobSeeker = jobSeekerRepo.findByEmail(skillMap.getEmail()).get(0); //Check if optional or not
        if(jobSeeker == null){
            jobSeeker = new JobSeeker();
            jobSeeker.setEmail(skillMap.getEmail());
        }
        String email = skillMap.getEmail();
        skillProficiencyRepo.deleteAllByJobSeekerEmail(email);
        for(String skill : skillMap.getSkills().keySet()){
            newSkill = new Skill(skill);
            proficiencyLevel = new SkillProficiency(newSkill,jobSeeker,skillMap.getSkills().get(skill));
            skillProficiencyRepo.save(proficiencyLevel);
        }
    }

    //shortlisting candidate for recruiter based on preferred location of candidate
    public List<JobSeeker> searchByLocation(String location){
        return jobSeekerRepo.findByLocation(location);
    }

}
