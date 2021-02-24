package com.stackroute.jobindexing.bootstrap;

import com.stackroute.jobindexing.controller.JobController;
import com.stackroute.jobindexing.model.GraphModel.JobGraph;
import com.stackroute.jobindexing.model.GraphModel.WorkingLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AppDataBootstrap implements CommandLineRunner {

    private JobController jobController;
    @Autowired
    public AppDataBootstrap(JobController jobController){
        this.jobController = jobController;
    }

    @Override
    public void run(String... args) throws Exception {
//        through constructors add seed data and use the controller to post them to the backend
//        Set<WorkingLocation> workingLocationSet1 = new HashSet<WorkingLocation>(Arrays.asList("bangalore", "pune", "chennai"));
//        workingLocationSet1.add("Bangalore");
//        JobGraph job1 = new JobGraph("Software Developer", "shreya@stackroute.com", , )
    }
}
