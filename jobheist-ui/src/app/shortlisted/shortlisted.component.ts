import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CandidateDialogComponent } from '../candidate-dialog/candidate-dialog.component';
import { Job } from '../job';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { RecruitersPostingsService } from '../recruiters-postings.service';
import { ShortlistingService } from '../shortlisting.service';

@Component({
  selector: 'app-shortlisted',
  templateUrl: './shortlisted.component.html',
  styleUrls: ['./shortlisted.component.css']
})
export class ShortlistedComponent implements OnInit {

  constructor(private shortlistingService: ShortlistingService,
              private router: Router,
              private route: ActivatedRoute,
              private jobService: RecruitersPostingsService,
              private dialog: MatDialog,
              private jobSeekerService: JobSeekerProfileService,
              private _snackbar: MatSnackBar) { }


  candidatesAuto = [];
  candidatesList: JobSeekerProfile[] = [];
  initialsList: string[] = [];
  colorsList: string[] = [];
  colors = ['#EB7181', '#468547'];
  index: number;
  flag: number;
  id: number;
  id1: string;
  job: Job = new Job();

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => { this.id = params.id; } );
    this.shortlistingService.recommendedprofiles(this.id).subscribe(data => {
      console.log(data);
      this.candidatesAuto = data;
      for (let i = 0; i < this.candidatesAuto.length; ++i){
        this.jobSeekerService.getJobSeekerByEmail(this.candidatesAuto[i].email).subscribe(value => {
          this.candidatesList.push(value);
          this.initialsList.push(this.createInitials(value.name));
          let randomIndex = 0;
          if (value.email.charAt(0).toUpperCase() > 'M'){
        randomIndex = 1;
      }
          this.colorsList.push(this.colors[randomIndex]);
        });
      }
    }, error => {
      if(error.status === 417){
        this._snackbar.open("Unable to fetch shortlisted candidates now, please try again later", "close", {
          duration: 30000,
        });
      }
    }) ;
    this.jobService.getPostingById(this.id).subscribe(value => {
      this.job = value;
    });
  }

  private createInitials(candidateName): string{
    if (candidateName != null && candidateName.trim() !== ''){
      const name = candidateName.trim();
      let initials = name.charAt(0).toUpperCase();
      for (let i = 1; i < name.length; ++i){
        if (name.charAt(i - 1) === ' ' && name.charAt(i) !== ' '){
          initials += name.charAt(i).toUpperCase();
          break;
        }
      }
      return initials;
    }
    return '';
  }

  viewCandidate(email): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = false;
    dialogConfig.minWidth = '350px';
    dialogConfig.minHeight = '500px';
    dialogConfig.data = email;
    this.dialog.open(CandidateDialogComponent, dialogConfig);
  }
}
