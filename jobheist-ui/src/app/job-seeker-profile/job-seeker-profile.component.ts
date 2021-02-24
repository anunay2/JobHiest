import {  Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EditSkillsComponent } from '../edit-skills/edit-skills.component';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { Skill } from '../Skill';
import { Router } from '@angular/router';
import { ExperienceComponent } from '../experience/experience.component';
import { Experience } from '../Experience';
import { Education } from '../Education';
import { EducationComponent } from '../education/education.component';
import { Jwtdecode } from '../jwt-decode';
import { CookieService } from 'ngx-cookie-service';
import { TitleCasePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-job-seeker-profile',
  templateUrl: './job-seeker-profile.component.html',
  styleUrls: ['./job-seeker-profile.component.css']
})
export class JobSeekerProfileComponent implements OnInit{

  skillsList: Skill[] = [];
  experiencesList: Experience[] = [];
  educationsList: Education[] = [];
  infoUpdateForm: FormGroup;
  jobSeekerProfile: JobSeekerProfile = new JobSeekerProfile();
  disabled = true;
  decoded: any;
  loggedinEmail: string;
  updated = true;

  initial = '';
  circleColor: string;
  colors = ['#EB7181', '#468547'];

  maxDate = new Date();

  constructor(private formBuilder: FormBuilder,
              private titlecasePipe: TitleCasePipe,
              private dialog: MatDialog,
              private jobSeekerService: JobSeekerProfileService,
              private cookie: CookieService,
              private router: Router,
              private _snackBar: MatSnackBar) { }


  ngOnInit(): void {
    // get emailid from jwt token
    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();
    this.jobSeekerProfile.email = this.loggedinEmail;
    let randomIndex = 0;
    if (this.loggedinEmail.charAt(0).toUpperCase() > 'M'){
        randomIndex = 1;
      }
    this.circleColor = this.colors[randomIndex];
    this.jobSeekerService.getJobSeekerByEmail(this.loggedinEmail).subscribe((data) => {
      if (data.skills !== null){
        this.skillsList = data.skills;
      }
      else{
        data.skills = [];
      }

      if (data.experiences !== null){
        this.experiencesList = data.experiences;
      }
      else{
        data.experiences = [];
      }
      if (data.educations !== null){
        this.educationsList = data.educations;
      }
      else{
        data.educations = [];
      }
      this.jobSeekerProfile = data;
      console.log(this.jobSeekerProfile);
      this.createInitials();
    }, error => {
      
        this._snackBar.open("Unable to retrieve the data now, please try again later", "close", {
          duration: 30000,
        });
      
    });

    this.buildInfoForm();
  }

  private buildInfoForm(): void{
    this.infoUpdateForm = this.formBuilder.group({
      email : [{value: this.jobSeekerProfile.email, disabled: true}, [Validators.required]],
      name : [{value: this.jobSeekerProfile.name, disabled: this.disabled}],
      dob: [{value: this.jobSeekerProfile.dob, disabled: this.disabled}],
      currentLocation : [{value: this.jobSeekerProfile.currentLocation, disabled: this.disabled}],
      preferredLocation : [{value: this.jobSeekerProfile.preferredLocation, disabled: this.disabled}]
    });
  }

  private createInitials(): void{
    if (this.jobSeekerProfile.name != null){
      const name = this.jobSeekerProfile.name.trim();
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
    const enteredName = this.jobSeekerProfile.name.trim();
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
    this.jobSeekerProfile.name = correctedName;
    this.jobSeekerProfile.currentLocation = this.titlecasePipe.transform(this.jobSeekerProfile.currentLocation);
    this.jobSeekerProfile.preferredLocation = this.titlecasePipe.transform(this.jobSeekerProfile.preferredLocation);
    this.jobSeekerService.editJobSeeker(this.jobSeekerProfile).subscribe((value) => {
      this.jobSeekerProfile = value;
      console.log('user edited');
      }, error => {
        if(error.status === 417){
          this._snackBar.open("Unable to edit your profile now, please try again later", "close", {
            duration: 30000,
          });
        }
      });
    console.log(this.jobSeekerProfile);
    this.disabled = true;
    this.buildInfoForm();
    this.createInitials();
  }

  onEditClick(): void{
    this.disabled = false;
    this.buildInfoForm();
  }

  onEditCancel(): void{
    this.disabled = true;
    this.ngOnInit();
    console.log(this.infoUpdateForm.value);
  }

  onEditSkillsClick(): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = false;
    dialogConfig.minWidth = '350px';
    dialogConfig.data = this.skillsList;
    const dialogRef = this.dialog.open(EditSkillsComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined){
        this.jobSeekerProfile.skills = result;
        this.jobSeekerService.editJobSeeker(this.jobSeekerProfile).subscribe((value) => {
        this.jobSeekerProfile = value;
        console.log('user skills edited');
        }, error => {
          if(error.status === 417){
            this._snackBar.open("Unable to edit your skills now, please try again later", "close", {
              duration: 30000,
            });
          }
        });
      }
      else{
       this.jobSeekerService.getJobSeekerByEmail(this.loggedinEmail).subscribe((data) => {

        if (data.skills !== null){
          this.skillsList = data.skills;
        }
        else{
          data.skills = [];
        }
        this.jobSeekerProfile = data;
        this.createInitials();
      });
      }
    });
  }

  addExperience(): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = false;
    dialogConfig.data = this.experiencesList;
    const dialogRef = this.dialog.open(ExperienceComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      this.jobSeekerProfile.experiences = result;
      this.jobSeekerService.editJobSeeker(this.jobSeekerProfile).subscribe(() => {
      console.log('experience updated for ' + this.loggedinEmail);
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to add your experience now, please try again later", "close", {
          duration: 30000,
        });
      }
    });
    });
  }
  editExperience(role, company): void{
    this.router.navigate(['/job-seeker'], {queryParams: {company}} );
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = false;
    dialogConfig.data = this.experiencesList;
    const dialogRef = this.dialog.open(ExperienceComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined){
        this.jobSeekerProfile.experiences = result;
        this.jobSeekerService.editJobSeeker(this.jobSeekerProfile).subscribe((data) => {
        this.experiencesList = data.experiences;
        this.jobSeekerProfile = data;
        console.log('experience updated for ' + this.loggedinEmail);
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to update your experience now, please try again later", "close", {
          duration: 30000,
        });
      }
    });
      }
      else{
        this.jobSeekerService.getJobSeekerByEmail(this.loggedinEmail).subscribe((data) => {
          this.experiencesList = data.experiences;
          this.jobSeekerProfile = data;
          console.log(this.jobSeekerProfile);
        });
      }
      this.router.navigate(['/job-seeker'] );
    });
  }

  addEducation(): void{
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = false;
    dialogConfig.data = this.educationsList;
    const dialogRef = this.dialog.open(EducationComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      this.jobSeekerProfile.educations = result;
      this.jobSeekerService.editJobSeeker(this.jobSeekerProfile).subscribe(() => {
      console.log('education updated for ' + this.loggedinEmail);
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to add your education now, please try again later", "close", {
          duration: 30000,
        });
      }
    });
    });
  }
  editEducation(degree): void{
    this.router.navigate(['/job-seeker'], {queryParams: {degree}} );
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = false;
    dialogConfig.data = this.educationsList;
    const dialogRef = this.dialog.open(EducationComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      if (result !== undefined){
        this.jobSeekerProfile.educations = result;
        this.jobSeekerService.editJobSeeker(this.jobSeekerProfile).subscribe((data) => {
        this.educationsList = data.educations;
        this.jobSeekerProfile = data;
        this.educationsList.sort(this.compare);
        console.log('education updated for ' + this.loggedinEmail);
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to update your education now, please try again later", "close", {
          duration: 30000,
        });
      }
    });
      }
      else{
        this.jobSeekerService.getJobSeekerByEmail(this.loggedinEmail).subscribe((data) => {
          this.educationsList = data.educations;
          this.jobSeekerProfile = data;
          console.log(this.jobSeekerProfile);
        });
      }
      this.router.navigate(['/job-seeker'] );
    });

  }
  private compare(a, b): number{
    if (a.endyear > b.endyear){
      return -1;
    }
    else{
      return 1;
    }
  }

}
