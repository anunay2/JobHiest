package com.skillprocessing.service;


import com.skillprocessing.model.ProfiencyLevel;
import com.skillprocessing.model.SkillSet;
import com.skillprocessing.model.UserSkills;
import com.skillprocessing.repository.SkillSetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkillsProcessing {
    private static final Logger logger= LoggerFactory.getLogger(SkillsProcessing.class);

//    private final String SKILL_API = "http://localhost:9090/api/v1/profile";
    private SkillSetRepository skillSetRepository;


    @Autowired
    public SkillsProcessing(SkillSetRepository skillSetRepository) {
        this.skillSetRepository = skillSetRepository;
    }

    public UserSkills process (UserSkills userSkills){
//        logger.info("inside process");
//        RestTemplate restTemplate = new RestTemplate();
//        JobSeekerProfile jobSeekerProfile = restTemplate.getForObject(SKILL_API+userSkills.getEmail(),JobSeekerProfile.class);
        Map<String, ProfiencyLevel> skills = userSkills.getSkills();
        Map<String, ProfiencyLevel> newSkills = new HashMap<>();
        skills.forEach((k,v) -> {
            SkillSet skillSet = skillSetRepository.getSubSkills(k);
            if (skillSet!=null){
                List<String> subSkills = skillSet.getSecondarySkills();
                subSkills.forEach(s -> {
                    if (!skills.containsKey(s)){
                        if (newSkills.containsKey(s)){
                            if (newSkills.get(s).compareTo(v)<0){
                                newSkills.replace(s,v);
                            }
                        }
                        else{
                            newSkills.put(s,v);
                        }
                    }
                });
            }

        });
        skills.putAll(newSkills);
        logger.info("skills map is "+skills);
        userSkills.setSkills(skills);
        logger.info("user skills is "+userSkills.getSkills());
        return userSkills;
    }

}
