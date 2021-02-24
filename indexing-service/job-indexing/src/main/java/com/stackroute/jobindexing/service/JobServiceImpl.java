package com.stackroute.jobindexing.service;

import com.stackroute.jobindexing.model.Job;
import com.stackroute.jobindexing.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository)
    {
        this.jobRepository=jobRepository;
    }

    @Override
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs(int pageNumber,int pageSize) {
        Pageable paging= PageRequest.of(pageNumber,pageSize);
        Page<Job> pagedResult=jobRepository.findAll(paging);
        if(pagedResult.hasContent())
            return pagedResult.getContent();
        else
            return new ArrayList<Job>();
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.save(job);

    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).get();
    }

    @Override
    public Job deleteJobById(Long id) {
        Job deletedJob=jobRepository.findById(id).get();
        jobRepository.deleteById(id);
        return deletedJob;
    }

    @Override
    public List<Job> getJobsByEmailID(String email) {
        return jobRepository.findByRecruiterEmail(email);
    }
}
