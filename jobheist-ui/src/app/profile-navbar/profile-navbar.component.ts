import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { Jwtdecode } from '../jwt-decode';

@Component({
  selector: 'app-profile-navbar',
  templateUrl: './profile-navbar.component.html',
  styleUrls: ['./profile-navbar.component.css']
})
export class ProfileNavbarComponent implements OnInit {

  circleColor: string;
  personalInfo: JobSeekerProfile = new JobSeekerProfile();
  colors = ['#EB7181', '#468547'];
  decoded: any;
  loggedinEmail: string;
  initial: string = '';

  constructor(private router: Router,
              private jobSeekerService: JobSeekerProfileService,
              private cookie: CookieService) { }

  ngOnInit(): void {
    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();
    let randomIndex = 0;
    if (this.loggedinEmail.charAt(0).toUpperCase() > 'M'){
        randomIndex = 1;
      }
    this.circleColor = this.colors[randomIndex];
    this.jobSeekerService.getJobSeekerByEmail(this.loggedinEmail).subscribe((data) => {
      this.personalInfo = data;
      this.createInitials();
    });

  }
  private createInitials(): void{
    if(this.personalInfo !== null){
    if (this.personalInfo.name !== null){
      const name = this.personalInfo.name.trim();
      let initials = name.charAt(0).toUpperCase();
      for (let i = 1; i < name.length; ++i){
        if (name.charAt(i - 1) === ' ' && name.charAt(i) !== ' '){
          initials += name.charAt(i).toUpperCase();
          break;
        }
      }
      this.initial = initials;
    }
  }
}

  logout(): void{
    this.router.navigate(['login']);
    this.cookie.deleteAll();
  }
}
