import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Education } from '../Education';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { Jwtdecode } from '../jwt-decode';
import { TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.css']
})
export class EducationComponent implements OnInit {
  jobSeekerProfile: JobSeekerProfile;
  education: Education = new Education();
  degree: string;
  old = false;
  educationsList: Education[] = [];
  educationForm: FormGroup;
  loggedinEmail: string;

  constructor(private fb: FormBuilder,
              private titlecasePipe: TitleCasePipe,
              private matDialogRef: MatDialogRef<EducationComponent>,
              @Inject(MAT_DIALOG_DATA) data,
              private route: ActivatedRoute,
              private cookie: CookieService) {
                this.educationsList = data;
               }

  ngOnInit(): void {
    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();
    this.route.queryParams.subscribe(param => {
      // this.loggedinEmail = param.email;
      this.degree = param.degree;
    });

    if (this.degree !== undefined){
      this.old = true;
      this.education = this.educationsList.filter(exp => {
        return exp.degree === this.degree;
      })[0];
    }


    this.educationForm = this.fb.group({
    school: [{value: this.education.school} , Validators.required],
    degree: [{value: this.education.degree} , Validators.required],
    field: [{value: this.education.field}],
    startyear: [{value: this.education.startyear}, Validators.required],
    endyear: [{value: this.education.endyear}, Validators.required]
    });
  }

  onSaveEducation(): void{
    if (this.degree !== undefined){
      this.education = this.educationForm.value;
      for (let i = 0; i < this.educationsList.length; ++i){
        if (this.educationsList[i].degree === this.degree){
          this.educationsList[i] = this.education;
          break;
        }
      }
    }
    else{
      this.education = this.educationForm.value;
      this.education.school = this.education.school.toUpperCase();
      this.education.degree = this.titlecasePipe.transform(this.education.degree);
      this.education.field = this.titlecasePipe.transform(this.education.field);
      this.educationsList.push(this.education);
      this.educationsList.sort(this.compare);
    }

    this.matDialogRef.close(this.educationsList);
  }
  deleteEducation(): void{
    for (let i = 0; i < this.educationsList.length; ++i){
      if (this.educationsList[i].degree === this.degree){
        this.educationsList.splice(i, 1);
      }
    }
    this.matDialogRef.close(this.educationsList);
  }
  cancelEducation(): void{
    this.matDialogRef.close();
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
