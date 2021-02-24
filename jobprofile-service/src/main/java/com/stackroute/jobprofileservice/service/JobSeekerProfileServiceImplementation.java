package com.stackroute.jobprofileservice.service;

import com.stackroute.jobprofileservice.service.RabbitMqSender;

import com.stackroute.jobprofileservice.model.JobSeekerProfile;
import com.stackroute.jobprofileservice.repository.JobSeekerProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class JobSeekerProfileServiceImplementation implements JobSeekerProfileService,RabbitListenerConfigurer{
    private JobSeekerProfileRepository jobSeekerProfileRepository;
    RabbitMqSender rabbitMqSender;
    private static final Logger logger= LoggerFactory.getLogger(JobSeekerProfileServiceImplementation.class);

    @Override
    @RabbitListener(queues="${spring.rabbitmq.queue_username}")
    public void receiveEmail(String Email) {
        JobSeekerProfile jobSeekerProfilefresh=new JobSeekerProfile();
        jobSeekerProfilefresh.setEmail(Email);
        //jobSeekerProfilefresh.setCreatedOnTs((long)Instant.now().getEpochSecond());
        jobSeekerProfileRepository.save(jobSeekerProfilefresh);
        logger.info("Created profile email"+jobSeekerProfilefresh);
    }


//    RabbitMqSender rabbitMqSender;

//    @Value("${app.message}")
//    private String message;

//    @PostMapping(value="jobSeekerProfile")
//    public String publishEmployeeDetails(@RequestBody JobSeekerProfile jobSeekerProfile){
//        rabbitMqSender.send(jobSeekerProfile);
//        return message;
//    }

    @Autowired
    public JobSeekerProfileServiceImplementation(JobSeekerProfileRepository jobSeekerProfileRepository, RabbitMqSender rabbitMqSender)
    {   this.rabbitMqSender=rabbitMqSender;
        this.jobSeekerProfileRepository=jobSeekerProfileRepository;
    }

    @Override
    public JobSeekerProfile saveProfile(JobSeekerProfile profile) {

        return jobSeekerProfileRepository.save(profile);
    }

    @Override
    public JobSeekerProfile createProfile(String email) {
        JobSeekerProfile jobSeekerProfilefresh=new JobSeekerProfile();
        jobSeekerProfilefresh.setEmail(email);
        return jobSeekerProfilefresh;
    }




    @Override
    public List< JobSeekerProfile > getAllProfile() {
        return (List<JobSeekerProfile>) jobSeekerProfileRepository.findAll();
    }


    @Override
    public JobSeekerProfile getProfileEmail(String email) {
        JobSeekerProfile jobSeekerProfile=null;
        jobSeekerProfile =jobSeekerProfileRepository.findByEmail(email);
        return jobSeekerProfile;
    }

    @Override
    public JobSeekerProfile deleteProfileByEmail(String email) {
        JobSeekerProfile jobSeekerProfile = null;
        jobSeekerProfile = jobSeekerProfileRepository.findByEmail(email);
        if(jobSeekerProfile!=null){
            jobSeekerProfileRepository.delete(jobSeekerProfile);
            return jobSeekerProfile;
        }
        return null;

    }


    @Override
    public JobSeekerProfile updateProfile(JobSeekerProfile jobSeekerProfilenew) {
        JobSeekerProfile jobSeekerProfileold = null;
        jobSeekerProfileold=jobSeekerProfileRepository.findByEmail(jobSeekerProfilenew.getEmail());
        if(jobSeekerProfilenew.getCurrentLocation()!=null){
            jobSeekerProfileold.setCurrentLocation(jobSeekerProfilenew.getCurrentLocation());
        }
        if(jobSeekerProfilenew.getPreferredLocation()!=null){
            jobSeekerProfileold.setPreferredLocation(jobSeekerProfilenew.getPreferredLocation());
        }

        if(jobSeekerProfilenew.getGender()!=null){
            jobSeekerProfileold.setGender(jobSeekerProfilenew.getGender());
        }

        if(jobSeekerProfilenew.getDob()!=null){
            jobSeekerProfileold.setDob(jobSeekerProfilenew.getDob());
        }

        if(jobSeekerProfilenew.getName()!=null){
            jobSeekerProfileold.setName(jobSeekerProfilenew.getName());
        }
        if(jobSeekerProfilenew.getSkills()!=null){
            jobSeekerProfileold.setSkills(jobSeekerProfilenew.getSkills());
            rabbitMqSender.sendSkillProcessing(jobSeekerProfileold);
            logger.info("GET SKILLS "+jobSeekerProfileold);
        }

        if(jobSeekerProfilenew.getExperiences()!=null){
            jobSeekerProfileold.setExperiences(jobSeekerProfilenew.getExperiences());
            rabbitMqSender.sendExperienceProcessing(jobSeekerProfileold);
        }

        if(jobSeekerProfilenew.getCreatedOnTs()!=null){
            jobSeekerProfileold.setCreatedOnTs(jobSeekerProfilenew.getCreatedOnTs());
        }

        if(jobSeekerProfilenew.getEducations()!=null){
            jobSeekerProfileold.setEducations(jobSeekerProfilenew.getEducations());
        }

        if(jobSeekerProfilenew.getEmail()!=null){
            jobSeekerProfileold.setEmail(jobSeekerProfilenew.getEmail());
        }

        if(jobSeekerProfilenew.getAppliedJobId()!=null){
            jobSeekerProfileold.setAppliedJobId(jobSeekerProfilenew.getAppliedJobId());
            /*
            List<Integer> oldjobids=jobSeekerProfileold.getAppliedJobId();
            if(oldjobids==null){
                oldjobids=new ArrayList<>();
            }
            List<Integer> newjobids=jobSeekerProfilenew.getAppliedJobId();
            oldjobids.addAll(newjobids);
            jobSeekerProfileold.setAppliedJobId(oldjobids);*/
        }

        if(jobSeekerProfilenew.getName()!=null || jobSeekerProfilenew.getDob()!=null || jobSeekerProfileold.getGender()!=null ||
                jobSeekerProfilenew.getPreferredLocation()!=null || jobSeekerProfilenew.getCurrentLocation()!=null){
            rabbitMqSender.sendPersonalProcessing(jobSeekerProfileold);
        }

        return jobSeekerProfileRepository.save(jobSeekerProfileold);

    }



   /* @Override
    public JobSeekerProfile applyForJob(JobSeekerProfile jobSeekerProfilenew) {
        JobSeekerProfile jobSeekerProfileold = null;
        jobSeekerProfileold=jobSeekerProfileRepository.findByEmail(jobSeekerProfilenew.getEmail());
        if(jobSeekerProfileold!=null) {
            Set< Integer > jobid = jobSeekerProfilenew.getAppliedJobId();
            Set< Integer > oldjobids = jobSeekerProfileold.getAppliedJobId();
            if(oldjobids==null){
                oldjobids=new HashSet<>();
            }
            oldjobids.addAll(jobid);
            jobSeekerProfileold.setAppliedJobId(oldjobids);
        }
        return jobSeekerProfileRepository.save(jobSeekerProfileold);
    }*/

    @Override
    public JobSeekerProfile applyForJob(int id, String email){
        JobSeekerProfile profile=jobSeekerProfileRepository.findByEmail(email);
        if(profile!=null){
            List<Integer> jobids=profile.getAppliedJobId();
            if(jobids==null){
                jobids=new ArrayList<>();
            }
            jobids.add(id);
            profile.setAppliedJobId(jobids);
        }
        return jobSeekerProfileRepository.save(profile);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

}
