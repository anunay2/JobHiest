import { TitleCasePipe } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { Experience } from '../Experience';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { Jwtdecode } from '../jwt-decode';

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  jobSeekerProfile: JobSeekerProfile;
  experience: Experience = new Experience();
  organisation: string;
  old = false;
  experiencesList: Experience[] = [];
  experienceForm: FormGroup;
  loggedinEmail: string;

  maxDate = new Date();

  domainOptions: string[] = ['STEM', 'Software'];
  filteredOptions: Observable<string[]>;

  constructor(private fb: FormBuilder,
              private titlecasePipe: TitleCasePipe,
              private matDialogRef: MatDialogRef<ExperienceComponent>,
              @Inject(MAT_DIALOG_DATA) data,
              private route: ActivatedRoute,
              private cookie: CookieService) {
                this.experiencesList = data;
               }

  ngOnInit(): void {
    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();
    this.route.queryParams.subscribe(param => {
      this.organisation = param.company;
    });
    if (this.organisation !== undefined){
    this.old = true;
    this.experience = this.experiencesList.filter(exp => {
      return exp.organisation === this.organisation;
    })[0];
  }

    this.experienceForm = this.fb.group({
      role: [{value: this.experience.role} , Validators.required],
    organisation: [{value: this.experience.organisation} , Validators.required],
    domain: [{value: this.experience.domain}],
    responsibilities: [{value: this.experience.responsibilities}],
    project: [{value: this.experience.project}],
    startDate: [{value: this.experience.startDate} , Validators.required],
    endDate: [{value: this.experience.endDate}]
    });

    this.filteredOptions = this.experienceForm.get('domain').valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  private _filter(value: string): string[] {
    let filterValue = '';
    if (value != null){
      filterValue = value.toLowerCase();
    }
    return this.domainOptions.filter(option =>
      option.toLowerCase().includes(filterValue));
  }

  onSaveExperience(): void{
    if (this.organisation !== undefined){
      this.experience = this.experienceForm.value;
      for (let i = 0; i < this.experiencesList.length; ++i){
        if (this.experiencesList[i].organisation === this.organisation){
          this.experiencesList[i] = this.experience;
          break;
        }
      }
    }
    else{
      this.experience = this.experienceForm.value;
      this.experience.organisation = this.experience.organisation.toUpperCase();
      this.experience.role = this.titlecasePipe.transform(this.experience.role);
      this.experiencesList.push(this.experience);
    }
    this.matDialogRef.close(this.experiencesList);
  }
  deleteExperience(): void{
    for (let i = 0; i < this.experiencesList.length; ++i){
      if (this.experiencesList[i].organisation === this.organisation){
        this.experiencesList.splice(i, 1);
      }
    }
    this.matDialogRef.close(this.experiencesList);
  }
  cancelExperience(): void{
    this.matDialogRef.close();
  }

}
