package com.stackroute.jobrecruiterprofileservice.service;

import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;
import com.stackroute.jobrecruiterprofileservice.repository.JobRecruiterProfileRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobRecruiterProfileServiceImpl implements JobRecruiterProfileService, RabbitListenerConfigurer {

    @NonNull
    JobRecruiterProfileRepository jobRecruiterProfileRepository;

    private static final Logger logger= LoggerFactory.getLogger(JobRecruiterProfileServiceImpl.class);

    @Override
    @RabbitListener(queues="${spring.rabbitmq.queue_recruitername}")
    public void receiveEmail(String Email) {
        JobRecruiterProfile jobRecruiterProfilefresh=new JobRecruiterProfile();
        jobRecruiterProfilefresh.setEmail(Email);
        jobRecruiterProfilefresh.setCreatedOnTS(Instant.now().getEpochSecond());
        jobRecruiterProfileRepository.save(jobRecruiterProfilefresh);
        logger.info("Created profile email"+jobRecruiterProfilefresh);
    }

    @Override
    public JobRecruiterProfile createProfile(String email) {
        JobRecruiterProfile jobRecruiterProfilefresh=new JobRecruiterProfile();
        jobRecruiterProfilefresh.setEmail(email);
        jobRecruiterProfilefresh.setCreatedOnTS(Instant.now().getEpochSecond());
        return jobRecruiterProfilefresh;
    }

    @Override
    public List<JobRecruiterProfile> getAllProfile() {
        return ((List<JobRecruiterProfile>) jobRecruiterProfileRepository.findAll());
    }

    @Override
    public JobRecruiterProfile saveProfile(JobRecruiterProfile profile) {
        profile.setCreatedOnTS(Instant.now().getEpochSecond());
        return jobRecruiterProfileRepository.save(profile);
    }

    @Override
    public JobRecruiterProfile getProfileEmail(String email) {
        JobRecruiterProfile jobRecruiterProfile=null;
        jobRecruiterProfile =jobRecruiterProfileRepository.findByEmail(email);
        return jobRecruiterProfile;

    }



    @Override
    public JobRecruiterProfile updateProfile(JobRecruiterProfile jobRecruiterProfileNew) {
        JobRecruiterProfile jobRecruiterProfileOld = null;
        jobRecruiterProfileOld=jobRecruiterProfileRepository.findByEmail(jobRecruiterProfileNew.getEmail());

        if(jobRecruiterProfileNew.getName()!=null){
            jobRecruiterProfileOld.setName(jobRecruiterProfileNew.getName());
        }
        if(jobRecruiterProfileNew.getOrganisation()!=null){
            jobRecruiterProfileOld.setOrganisation(jobRecruiterProfileNew.getOrganisation());
        }
        if(jobRecruiterProfileNew.getMob()!=null){
            jobRecruiterProfileOld.setMob(jobRecruiterProfileNew.getMob());
        }
        if(jobRecruiterProfileNew.getPin()!=null){
            jobRecruiterProfileOld.setPin(jobRecruiterProfileNew.getPin());
        }
        if(jobRecruiterProfileNew.getGst()!=null){
            jobRecruiterProfileOld.setGst(jobRecruiterProfileNew.getGst());
        }
        if(jobRecruiterProfileNew.getDomain()!=null){
            jobRecruiterProfileOld.setDomain(jobRecruiterProfileNew.getDomain());
        }

        return jobRecruiterProfileRepository.save(jobRecruiterProfileOld);

    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
