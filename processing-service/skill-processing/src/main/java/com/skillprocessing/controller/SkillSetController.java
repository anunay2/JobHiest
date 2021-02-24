package com.skillprocessing.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.skillprocessing.model.SkillSet;
import com.skillprocessing.repository.SkillSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SkillSetController {

//    @Autowired
    private SkillSetRepository skillSetRepository;

    @Autowired
    public SkillSetController(SkillSetRepository skillSetRepository) {
        this.skillSetRepository = skillSetRepository;
    }

    @HystrixCommand(fallbackMethod = "saveFallback")
    @PostMapping("skill")
    public SkillSet save(@RequestBody SkillSet skillSet){
        return skillSetRepository.save(skillSet);
    }

    @HystrixCommand(fallbackMethod = "getSubSkillsFallback")
    @GetMapping("skill/{skill}")
    public SkillSet getSubSkills(@PathVariable String skill){
        return skillSetRepository.getSubSkills(skill);
    }

//    fallbacks below

    public SkillSet saveFallback(@RequestBody SkillSet skillSet){
        return new SkillSet("");
    }

    public SkillSet getSubSkillsFallback(@PathVariable String skill){
        return new SkillSet("");
    }

    }
