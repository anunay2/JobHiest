package com.stackroute.jobrecruiterprofileservice.service;

import com.stackroute.jobrecruiterprofileservice.model.JobRecruiterProfile;

import java.util.List;

public interface JobRecruiterProfileService {
    JobRecruiterProfile updateProfile(JobRecruiterProfile jobRecruiterProfile);
    JobRecruiterProfile getProfileEmail(String email );
    JobRecruiterProfile saveProfile(JobRecruiterProfile profile);
    JobRecruiterProfile createProfile(String email);
    List<JobRecruiterProfile> getAllProfile();
    void receiveEmail(String Email);
}
