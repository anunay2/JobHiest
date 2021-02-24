package com.stackroute.jobindexing.service;

import com.stackroute.jobindexing.exception.JobAlreadyExistsException;
import com.stackroute.jobindexing.model.GraphModel.JobGraph;

import java.util.List;

public interface JobGraphService {

    JobGraph addJobGraphNode(JobGraph jobGraph) throws JobAlreadyExistsException;
    List<JobGraph> findAllJobGraphNodes();

}
