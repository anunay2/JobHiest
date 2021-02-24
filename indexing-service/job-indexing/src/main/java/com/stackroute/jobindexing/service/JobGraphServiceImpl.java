package com.stackroute.jobindexing.service;

import com.stackroute.jobindexing.exception.JobAlreadyExistsException;
import com.stackroute.jobindexing.model.GraphModel.JobGraph;
import com.stackroute.jobindexing.repository.JobGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobGraphServiceImpl implements JobGraphService {
    private JobGraphRepository jobGraphRepository;

    @Autowired
    public JobGraphServiceImpl(JobGraphRepository jobGraphRepository)
    {
        this.jobGraphRepository=jobGraphRepository;
    }

    @Override
    public JobGraph addJobGraphNode(JobGraph jobGraph) throws JobAlreadyExistsException {
        List<JobGraph> jobGraphNodes=(List<JobGraph>)jobGraphRepository.findAll();
        if(jobGraphNodes.contains(jobGraph))
            throw new JobAlreadyExistsException();
        return jobGraphRepository.save(jobGraph);
    }

    @Override
    public List<JobGraph> findAllJobGraphNodes() {
        return (List<JobGraph>)jobGraphRepository.findAll();
    }
}
