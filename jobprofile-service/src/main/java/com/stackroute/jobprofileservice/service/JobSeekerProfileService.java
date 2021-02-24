package com.stackroute.jobprofileservice.service;


import com.stackroute.jobprofileservice.model.JobSeekerProfile;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;

import java.util.List;

public interface JobSeekerProfileService {
    JobSeekerProfile saveProfile(JobSeekerProfile profile);
    JobSeekerProfile createProfile(String email);
    void receiveEmail(String Email);
    List<JobSeekerProfile> getAllProfile();

    JobSeekerProfile getProfileEmail(String email );

    JobSeekerProfile deleteProfileByEmail(String email);

    JobSeekerProfile updateProfile(JobSeekerProfile jobSeekerProfile);



    JobSeekerProfile applyForJob(int id, String email);
}
