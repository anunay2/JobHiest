import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import jwt_decode from 'jwt-decode';
import { JobRecruiterProfileService } from '../job-recruiter-profile.service';
import { JobRecruiterProfile } from '../JobRecruiterProfile';

@Component({
  selector: 'app-navbar-profile-recruiter',
  templateUrl: './navbar-profile-recruiter.component.html',
  styleUrls: ['./navbar-profile-recruiter.component.css']
})
export class NavbarProfileRecruiterComponent implements OnInit {
  circleColor: string;
  companyInfo: JobRecruiterProfile = new JobRecruiterProfile();
  colors = ['#EB7181', '#468547'];
  decoded: any;
  loggedinEmail: string;
  initial: string = '';

  constructor(private router: Router,
    private jobRecruiterService: JobRecruiterProfileService,
    private cookie: CookieService) { }

  ngOnInit(): void {
    this.decoded = jwt_decode(this.cookie.get('Authorization'));
    this.loggedinEmail = this.decoded.sub;
    let randomIndex = 0;
    if (this.loggedinEmail.charAt(0).toUpperCase() > 'M'){
        randomIndex = 1;
      }
    this.circleColor = this.colors[randomIndex];
    this.jobRecruiterService.getJobRecruiterByEmail(this.loggedinEmail).subscribe((data) => {
      this.companyInfo = data;
      this.createInitials();
    });
  }
  private createInitials(): void{
    if (this.companyInfo !== null){
    if (this.companyInfo.name !== null){
      const name = this.companyInfo.name.trim();
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
    this.router.navigate(['login'], {queryParams: {state: 'loggedout'}});
    this.cookie.delete('Authorization');
  }

}
