package com.stackroute.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "tab2")
public class User {
    @Id
    @Column(name = "EMAIL")
    private String emailid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    public enum Role{
        JOBSEEKER, RECRUITER;
    }
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "CREATEDON")
    private Long createdon;

    public User() {
    }

    //new constructor with only emailid and username created for fallback reasons


    public User(String emailid, String username) {
        this.emailid = emailid;
        this.username = username;
    }

    public User(String emailid, String username, String password, Role role, Long createdon) {
        this.emailid = emailid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdon = createdon;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Long createdon) {
        this.createdon = createdon;
    }
}
