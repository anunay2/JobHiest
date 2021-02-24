package com.stackroute.jobindexing.service;

import com.stackroute.jobindexing.model.Job;

import java.util.List;

public interface JobService {

    Job saveJob(Job job);
    List<Job> getAllJobs(int pageNumber,int pageSize);
    Job updateJob(Job job);
    Job getJobById(Long id);
    Job deleteJobById(Long id);
    List<Job> getJobsByEmailID(String email);


}
