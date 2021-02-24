package com.stackroute.jobprofileservice.service;


import com.stackroute.jobprofileservice.model.JobSeekerProfile;
import com.stackroute.jobprofileservice.model.ProfiencyLevel;
import com.stackroute.jobprofileservice.model.Skills;
import com.stackroute.jobprofileservice.model.processing_model.ExperienceProcessing;
import com.stackroute.jobprofileservice.model.processing_model.PersonalInfo;
import com.stackroute.jobprofileservice.model.processing_model.skillsProcessing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RabbitMqSender implements RabbitListenerConfigurer {
    private RabbitTemplate rabbitTemplate;
    String email;
    private static final Logger logger= LoggerFactory.getLogger(RabbitMqSender.class);

    //recieving email from the authentication service



    public RabbitMqSender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;

    }

    @Value("${spring.rabbitmq.exchange_experience}")
    private String exchange_experience;

    @Value("${spring.rabbitmq.routingkey_experience}")
    private String routingkey_experience;



    @Value("${spring.rabbitmq.exchange_personal}")
    private String exchange_personal;


    @Value("${spring.rabbitmq.routingkey_personal}")
    private String routingkey_personal;

    @Value("${spring.rabbitmq.routingkey_skills}")
    String routingkey_skills;

    @Value("${spring.rabbitmq.exchange_skills}")
    String exchange_skills;



    public void sendExperienceProcessing(JobSeekerProfile jobSeekerProfile){
        ExperienceProcessing ep1=new ExperienceProcessing(jobSeekerProfile.getEmail(),jobSeekerProfile.getExperiences());
        rabbitTemplate.convertAndSend(exchange_experience,routingkey_experience,ep1);

    }

    public void sendPersonalProcessing(JobSeekerProfile js){
        PersonalInfo pi1=new PersonalInfo(js.getEmail(),js.getName(),js.getGender(), js.getDob(), js.getCurrentLocation(),js.getPreferredLocation());
        rabbitTemplate.convertAndSend(exchange_personal,routingkey_personal,pi1);
    }

    public void sendSkillProcessing(JobSeekerProfile jobSeekerProfile){

        skillsProcessing sp1= new skillsProcessing();
        sp1.setEmail(jobSeekerProfile.getEmail());
        List< Skills > sk1= jobSeekerProfile.getSkills();
        Map<String, ProfiencyLevel > mp1=new HashMap<>() {};
        for(int i=0;i<sk1.size();i++){
            mp1.put(sk1.get(i).getSkill(), sk1.get(i).getProfiencyLevel());
        }
        sp1.setSkills(mp1);
        rabbitTemplate.convertAndSend(exchange_skills,routingkey_skills,sp1);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar){

    }

}
