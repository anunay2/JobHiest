import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Job } from '../job';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { JobSeekerProfile } from '../JobSeekerProfile';

@Component({
  selector: 'app-candidate-dialog',
  templateUrl: './candidate-dialog.component.html',
  styleUrls: ['./candidate-dialog.component.css']
})
export class CandidateDialogComponent implements OnInit {

  jobSeekerProfile: JobSeekerProfile = new JobSeekerProfile();
  emailId: string;

  constructor(private matDialogRef: MatDialogRef<CandidateDialogComponent>,
              @Inject(MAT_DIALOG_DATA) data,
              private jobSeekerService: JobSeekerProfileService, private _snackBar: MatSnackBar) {
                this.emailId = data;
              }

  ngOnInit(): void {
    this.jobSeekerService.getJobSeekerByEmail(this.emailId).subscribe(data => {
      this.jobSeekerProfile = data;
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to fetch candidate details now, please try again later", "close", {
          duration: 30000,
        });
      }
    });
  }

  cancel(): void{
    this.matDialogRef.close();
  }

}
