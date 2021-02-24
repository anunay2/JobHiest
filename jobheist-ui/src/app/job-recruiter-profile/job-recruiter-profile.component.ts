import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { JobRecruiterProfile } from '../JobRecruiterProfile';
import { MatDialog} from '@angular/material/dialog';
import { JobRecruiterProfileService } from '../job-recruiter-profile.service';

import { Jwtdecode } from '../jwt-decode';
import { TitleCasePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-job-recruiter-profile',
  templateUrl: './job-recruiter-profile.component.html',
  styleUrls: ['./job-recruiter-profile.component.css']
})
export class JobRecruiterProfileComponent implements OnInit {

  cinfoUpdateForm: FormGroup;
  jobRecruiterProfile: JobRecruiterProfile = new JobRecruiterProfile();
  disabled = true;
  decoded: any;
  loggedinEmail: string;
  updated = true;

  initial = '';
  circleColor: string;
  colors = ['#EB7181', '#468547'];

  constructor(private formBuilder: FormBuilder,
              private titlecasePipe: TitleCasePipe,
              private jobRecruiterService: JobRecruiterProfileService,
              private cookie: CookieService,
              private router: Router,
              private route: ActivatedRoute,
              private _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    // get emailId from jwt token
    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();
    this.jobRecruiterProfile.email = this.loggedinEmail;
    let randomIndex = 0;
    if (this.loggedinEmail.charAt(0).toUpperCase() > 'M'){
        randomIndex = 1;
      }
    this.circleColor = this.colors[randomIndex];
    this.jobRecruiterService.getJobRecruiterByEmail(this.loggedinEmail).subscribe((data) => {
      this.jobRecruiterProfile = data;
      this.createInitials();
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to retrieve your data now, please try again later", "close", {
          duration: 30000,
        });
      }
    });

    this.buildcInfoForm();
  }

  private buildcInfoForm(): void{
    this.cinfoUpdateForm = this.formBuilder.group({
      email : [{value: this.jobRecruiterProfile.email, disabled: true}, [Validators.required]],
      name : [{value: this.jobRecruiterProfile.name, disabled: this.disabled}, [Validators.required]],
      organisation : [{value: this.jobRecruiterProfile.organisation, disabled: this.disabled}, [Validators.required]],
      pin: [{value: this.jobRecruiterProfile.pin, disabled: this.disabled}],
      gst : [{value: this.jobRecruiterProfile.gst, disabled: this.disabled}, [Validators.required]],
      mob : [{value: this.jobRecruiterProfile.mob, disabled: this.disabled}, [Validators.required]],
      domain : [{value: this.jobRecruiterProfile.domain, disabled: this.disabled }]
    });
  }

  private createInitials(): void{
    if (this.jobRecruiterProfile.name != null){
      const name = this.jobRecruiterProfile.name.trim();
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
  onInfoEdit(): void{
    const enteredName = this.jobRecruiterProfile.name.trim();
    let correctedName = enteredName.charAt(0).toUpperCase();
    for (let i = 1; i < enteredName.length; ++i){
      if (enteredName.charAt(i - 1) === ' '){
        if (enteredName.charAt(i) !== ' '){
          correctedName += enteredName.charAt(i).toUpperCase();
        }
      }
      else{
        correctedName += enteredName.charAt(i);
      }
    }
    console.log('Corrected name ' + correctedName );
    this.jobRecruiterProfile.name = correctedName;

    this.jobRecruiterProfile.organisation = this.titlecasePipe.transform(this.jobRecruiterProfile.organisation);

    this.jobRecruiterService.editJobRecruiter(this.jobRecruiterProfile).subscribe((value) => {
      this.jobRecruiterProfile = value;
      console.log('user edited');
      }, error => {
        if(error.status === 417){
          this._snackBar.open("Unable to edit your profile now, please try again later", "close", {
            duration: 30000,
          });
        }
      });
    console.log(this.jobRecruiterProfile);
    this.disabled = true;
    this.buildcInfoForm();
    this.createInitials();
  }

  onEditClick(): void{
    this.disabled = false;
    this.ngOnInit();
  }

  onEditCancel(): void{
    this.disabled = true;
    this.ngOnInit();
    console.log(this.cinfoUpdateForm.value);
  }

  postvacancy(): void{
    this.router.navigate(['hire'], {relativeTo: this.route});
  }

  allposting(): void{
    this.router.navigate(['all-postings'], {relativeTo: this.route});
  }
}

