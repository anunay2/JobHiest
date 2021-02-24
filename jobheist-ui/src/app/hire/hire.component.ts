import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { DesiredCandidateProfile, Job, Pair } from '../job';
import { JobUpdateService } from '../job-update.service';
import { MatAutocomplete, MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatChipInputEvent } from '@angular/material/chips';
import { domain } from 'process';
import { ProficiencySelectComponent } from '../proficiency-select/proficiency-select.component';
import { CookieService } from 'ngx-cookie-service';
import { Jwtdecode } from '../jwt-decode';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TitleCasePipe } from '@angular/common';

@Component({
  selector: 'app-hire',
  templateUrl: './hire.component.html',
  styleUrls: ['./hire.component.css']
})
export class HireComponent implements OnInit {
  [x: string]: any;

  get skills() {
    return this.jobUpdateForm.get('skillArray') as FormArray;
  }
  get roles() {
    return this.jobUpdateForm.get('roleArray') as FormArray;
  }
  get domains() {
    return this.jobUpdateForm.get('domainArray') as FormArray;
  }

  job: Job = new Job();

  candidateProfile: DesiredCandidateProfile = new DesiredCandidateProfile();
  salary: Pair = new Pair();

  totalExperience: Pair = new Pair();
  age: Pair = new Pair();
  jobUpdateForm: FormGroup;
  msg = '';

  loggedinEmail: string;


  minDate = new Date();
  currentYear = new Date().getFullYear();
  maxDate = new Date(this.currentYear + 1, 6, 31);

  min_age = 0;
  min_exp = 0;
  min_salary = 0;

  disableSelect = new FormControl(false);
  disableSubmit = new FormControl(false);

  step = 0;

  visible = true;
  selectable = true;
  removable = true;
  disable = false;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  skillCtrl = new FormControl();
  domainCtrl = new FormControl();
  roleCtrl = new FormControl();
  // skills : string[] = ['HTML'];
  // domains : string[] = [];
  // roles : string[] = [];
  filteredskills: Observable<string[]>;
  filtereddomains: Observable<string[]>;
  filteredroles: Observable<string[]>;

  skillOption: string[] = ['Angular', 'React', 'JavaScript', 'TypeScript',
  'CSS', 'Spring', 'Spring Boot', 'Java', 'Mongo', 'Rest', 'Machine Learning',
  'Data Structures', 'Algorithms', 'Computer Architecture', 'System Design',
  'Data Science', 'Probability & Statistics', 'Programming', 'Packages and Softwares',
  'Database Management', 'SQL', 'RDBMS', 'OODBMS'
  ];

  skillOptions: string[] = this.skillOption.sort();

  domainOptions: string[] = ['STEM', 'Software'];

  roleOptions: string[] = ['Data Scientist', 'Software Engineer'];

  @ViewChild('skillInput') skillInput: ElementRef<HTMLInputElement>;
  @ViewChild('domainInput') domainInput: ElementRef<HTMLInputElement>;
  @ViewChild('roleInput') roleInput: ElementRef<HTMLInputElement>;
  @ViewChild('autoskill') matAutocompleteSkill: MatAutocomplete;
  @ViewChild('autodomain') matAutocompleteDomain: MatAutocomplete;
  @ViewChild('autorole') matAutocompleteRole: MatAutocomplete;
  @ViewChild('auto') matAutocomplete: MatAutocomplete;

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  ngOnInit(): void {

    // console.log(this.skills);

    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();

    this.job.recruiterEmail = this.loggedinEmail;
    this.jobUpdateForm = this.formBuilder.group({
      'recruiterEmail' : [{value: this.job.recruiterEmail, disabled: true}],
      'jobTitle' : [this.job.jobTitle, [Validators.required]],
      'jobDescription' : [this.job.jobDescription,[Validators.required]],
      'joiningDate' : [this.job.joiningDate, [Validators.required]],
      'organisation' : [this.job.organisation, [Validators.required]],
      'orgDescription' : [this.job.orgDescription, [Validators.required]],
      'workingLocation' : [this.job.workingLocation, [Validators.required]],
      'maxSalary' : [{value: this.job.salary.maxValue, disabled: this.disableSelect.value},
        [Validators.min(this.min_salary)]],
      'minSalary' : [{value: this.job.salary.minValue, disabled: this.disableSelect.value}],
      'maxExperience' : [{value: this.job.candidateProfile.totalExperience.maxValue},
        [Validators.required, Validators.min(this.min_exp)]],
      'minExperience' : [{value: this.job.candidateProfile.totalExperience.minValue},
        [Validators.required]],
      'minAge' : [this.job.candidateProfile.age.minValue, [Validators.required, Validators.min(18)]],
      'maxAge' : [this.job.candidateProfile.age.maxValue,
        [Validators.required, Validators.min(this.min_age)]],
      // 'skillCtrl' : [this.job.candidateProfile.skill,[Validators.required]],
      skillArray : this.formBuilder.array([]),
      roleArray : this.formBuilder.array([]),
      domainArray : this.formBuilder.array([])
      // 'domainCtrl' : [this.job.candidateProfile.roles,[Validators.required]],
      // 'roleCtrl' : [this.job.candidateProfile.domains,[Validators.required]],
    }, { validators: [validateAge, validateExperience, validateSalary]});


  }

  constructor(private dialog: MatDialog,
              private titlecasePipe: TitleCasePipe,
              private formBuilder: FormBuilder,
              private _service: JobUpdateService, private _router: Router, private cookie: CookieService,
              private _snackBar: MatSnackBar) {

                this.filteredskills = this.skillCtrl.valueChanges.pipe(
                  startWith(null),
                  map((skill: string | null) => skill ? this._filter_skill(skill) : this.skillOptions.slice()));
            
                this.filtereddomains = this.domainCtrl.valueChanges.pipe(
                  startWith(null),
                  map((domain: string | null) => domain ? this._filter_domain(domain) : this.domainOptions.slice()));
            
                this.filteredroles = this.roleCtrl.valueChanges.pipe(
                  startWith(null),
                  map((role: string | null) => role ? this._filter_role(role) : this.roleOptions.slice()));


  }

  dis(){
    this.disable = true;
  }

  createSkill(): FormGroup {
    return this.formBuilder.group({
      skill: ''
    });
  }

  add_skill(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    console.log(value);

    // Add our skill
    if ((value || '').trim()) {
      this.skills.push(
        this.formBuilder.control(value)
      );
    }
    // findDuplicate(name, p): boolean {
   
    //   let test = myArray.filter(data => data.controls.name.value == name && name != null)
   
    //   if (test.length > 1) {
    //      return true;
    //   } else {
    //     return false
    // }}
   
    // if ((value || '').trim()) {
    //   if(this.skills.filter(data => data.controls.name.value == name && name != null)) {
    //     this.skills.push(this.formBuilder.control(value));
    //   }
    // }

    // Reset the input value
    if (input) {
      input.value = '';
    }

    // this.skills.formBuilder.setValue(null);
  }

  add_domain(event: MatChipInputEvent): void {

    const input = event.input;
    const value = event.value;

    // Add our skill
    if ((value || '').trim()) {
      this.domains.push(
        this.formBuilder.control(value));
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }

    // this.jobUpdateForm.controls.domainCtrl.setValue(null);
  }

  add_role(event: MatChipInputEvent): void {

    const input = event.input;
    const value = event.value;

    // Add our skill
    if ((value || '').trim()) {
      this.roles.push(
        this.formBuilder.control(value));
    }

    // Reset the input value
    // if (input) {
    //   input.value = '';
    // }

    // this.jobUpdateForm.controls.roleCtrl.setValue(null);
  }

  remove_skill(skill, skill_index): void {
    // const skill_index = this.skills.indexOf(skill);


    if (skill_index >= 0) {
      this.skills.removeAt(skill_index);
    }
    this.skillOption.push(skill.value);

  }

  remove_role(role, role_index): void {

    if (role_index >= 0) {
      this.roles.removeAt(role_index);
    }
    this.roleOptions.push(role.value);
  }

  remove_domain(domain, domain_index): void {

    // const domain_index = this.domains.indexOf(domain);

    if (domain_index >= 0) {
      this.domains.removeAt(domain_index);
    }
    this.domainOptions.push(domain.value);

  }


  selected_skill(event: MatAutocompleteSelectedEvent): void {
    // console.log(this.filteredskills);
    // this.openDialog();
    this.skills.push(this.formBuilder.control(event.option.viewValue));
    this.skillOptions.splice(this.skillOptions.indexOf(event.option.viewValue), 1);

    this.skillInput.nativeElement.value = '';
    this.skillCtrl.setValue(null);
  }

  selected_domain(event: MatAutocompleteSelectedEvent): void {

    this.domains.push(this.formBuilder.control(event.option.viewValue));
    this.domainOptions.splice(this.domainOptions.indexOf(event.option.viewValue), 1);

    this.domainInput.nativeElement.value = '';
    this.domainCtrl.setValue(null);
  }

  selected_role(event: MatAutocompleteSelectedEvent): void {

    this.roles.push(this.formBuilder.control(event.option.viewValue));
    this.roleOptions.splice(this.roleOptions.indexOf(event.option.viewValue), 1);

    this.roleInput.nativeElement.value = '';
    this.roleCtrl.setValue(null);
  }

  private _filter_skill(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.skillOptions.filter(skill => skill.toLowerCase().indexOf(filterValue)===0);
  }

  private _filter_domain(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.domainOptions.filter(domain => domain.toLowerCase().indexOf(filterValue) === 0);
  }

  private _filter_role(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.roleOptions.filter(role => role.toLowerCase().indexOf(filterValue) === 0);
  }


  onJobPost(){
    // this.job.createdOn = Date.now();
    this.job.candidateProfile.domains = this.jobUpdateForm.controls.domainArray.value;
    this.job.candidateProfile.roles = this.jobUpdateForm.controls.roleArray.value;
    const arr: string[] = this.jobUpdateForm.controls.skillArray.value;
    this.job.candidateProfile.skill = [];
    for (let i = 0; i < arr.length; i++){
      this.job.candidateProfile.skill.push({skill: arr[i], proficiencyLevel: 'BASIC'});
    }
    // this.job.candidateProfile.skill = this.jobUpdateForm.controls.skillCtrl.value;
    this.job.candidateProfile.totalExperience.maxValue = this.jobUpdateForm.controls.maxExperience.value;
    this.job.candidateProfile.totalExperience.minValue = this.jobUpdateForm.controls.minExperience.value;
    this.job.candidateProfile.age.maxValue = this.jobUpdateForm.controls.maxAge.value;
    this.job.candidateProfile.age.minValue = this.jobUpdateForm.controls.minAge.value;

    this.job.recruiterEmail = this.jobUpdateForm.controls.recruiterEmail.value;
    this.job.jobTitle = this.titlecasePipe.transform(this.jobUpdateForm.controls.jobTitle.value);
    
    this.job.jobDescription = this.jobUpdateForm.controls.jobDescription.value;
    this.job.joiningDate = this.jobUpdateForm.controls.joiningDate.value;
    this.job.organisation = this.titlecasePipe.transform(this.jobUpdateForm.controls.organisation.value);
    this.job.orgDescription = this.jobUpdateForm.controls.orgDescription.value;
    this.job.workingLocation = this.titlecasePipe.transform(this.jobUpdateForm.controls.workingLocation.value);
    this.job.salary.maxValue = this.jobUpdateForm.controls.maxSalary.value;
    this.job.salary.minValue = this.jobUpdateForm.controls.minSalary.value;



    console.log(this.jobUpdateForm.value);

    if (this.disableSelect.value){
      this.job.salary.maxValue = 0;
      this.job.salary.minValue = 0;
    }
    console.log(this.job);
    this._service.updateJob(this.job).subscribe(
      data => {
        console.log(this.job);
        console.log('response received');
        this.msg ='Job posted Successfully';

        this._snackBar.open('Job Posted Successfully', 'Success', {
            duration: 3000,
          });

        this._router.navigate(['job-recruiter/all-postings']);
      },
      error => {
        console.log('exception occured');
        this.msg = error.error;
        this._snackBar.open('Job Posting Failed', 'Failed!!', {
          duration: 3000,
        });
      }
    );
  }
}


export function validateSalary(
  control: AbstractControl
): ValidationErrors | null {
  if (control && control.get("minSalary") && control.get("maxSalary")) {
    const highscore = control.get("maxSalary").value;
    const lowscore = control.get("minSalary").value;
    return (highscore < lowscore) ? { salaryError: true } : null;
  }
  return null;
}
export function validateExperience(
  control: AbstractControl
): ValidationErrors | null {
  if (control && control.get("minExperience") && control.get("maxExperience")) {
    const highscore = control.get("maxExperience").value;
    const lowscore = control.get("minExperience").value;
    return (highscore < lowscore) ? { experienceError: true } : null;
  }
  return null;
}
export function validateAge(
  control: AbstractControl
): ValidationErrors | null {
  if (control && control.get("minAge") && control.get("maxAge")) {
    const highscore = control.get("maxAge").value;
    const lowscore = control.get("minAge").value;
    return (highscore < lowscore) ? { ageError: true } : null;
  }
  return null;
}
