import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { Route } from '@angular/compiler/src/core';
import { RecruitersPostingsService } from '../recruiters-postings.service';
import { Job } from '../job';
import { Jwtdecode } from '../jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import { JobSeekerProfile } from '../JobSeekerProfile';


@Component({
  selector: 'app-job-apply',
  templateUrl: './job-apply.component.html',
  styleUrls: ['./job-apply.component.css']
})
export class JobApplyComponent implements OnInit {

  job: Job = new Job();
  jobId: number;
  personalInfo: JobSeekerProfile = new JobSeekerProfile();
  show = true;

  constructor(private matDialogRef: MatDialogRef<JobApplyComponent>, private snackBar: MatSnackBar,
              private service: JobSeekerProfileService, private router: Router,
              private route: ActivatedRoute, @Inject(MAT_DIALOG_DATA) data,
              private _snackbar: MatSnackBar,
              private jobService: RecruitersPostingsService, private cookie: CookieService) {
                this.show = data;
              }

  ngOnInit(): void {
    this.route.queryParams.subscribe(param => {
      this.jobId = param.id;
      if (param.a === 'Y'){
        this.show = false;
      }
      if (param.a === 'N'){
        this.show = true;
      }
    });
    this.service.getJobSeekerByEmail(new Jwtdecode(this.cookie).getEmailid()).subscribe(
      data => {this.personalInfo = data;
               this.jobService.getPostingById(this.jobId).subscribe(value =>
                {
                this.job = value;
                console.log(this.job);
                }, error => {
                  if (error.status === 417){
                    this._snackbar.open('Unable to fetch candidate profile now, please try again later', 'close', {
                      duration: 30000,
                    });
                  }
                });
              });


  }

  apply(): void{
    // if(this.personalInfo.appliedJobId === null){
    //   this.personalInfo.appliedJobId = [];
    //  }
    // this.personalInfo.appliedJobId.push(this.jobId);
    // this.service.editJobSeeker(this.personalInfo).subscribe((data) => {
    //   console.log(data);
    // });
    this.show = false;
    this.matDialogRef.close(this.show);
    this.snackBar.open('Applied for job Successfully', 'Success', {
      duration: 3000,
    });

  }

  cancel(): void{
    this.matDialogRef.close();
  }

}
