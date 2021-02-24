import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { Skill } from '../Skill';

@Component({
  selector: 'app-edit-skills',
  templateUrl: './edit-skills.component.html',
  styleUrls: ['./edit-skills.component.css']
})
export class EditSkillsComponent implements OnInit {


  jobSeekerProfile: JobSeekerProfile;

  skillsList: Skill[] = [];
  skillsForm: FormGroup;
  isEmpty: boolean;
  loggedinEmail: string;
  newSkill = false;
  emptyMessage = 'Add a new skill ';

  skillOptions: string[] = ['Angular', 'React', 'JavaScript', 'TypeScript', 'HTML',
  'CSS', 'Spring', 'Spring Boot', 'Java', 'Mongo', 'Rest', 'Machine Learning',
  'Data Structures', 'Algorithms', 'Computer Architecture', 'System Design',
  'Data Science', 'Probability & Statistics', 'Programming', 'Packages and Softwares',
  'Database Management', 'SQL', 'RDBMS', 'OODBMS'
  ];

  filteredOptions: Observable<string[]>;


  constructor(private fb: FormBuilder,
              private matDialogRef: MatDialogRef<EditSkillsComponent>,
              @Inject(MAT_DIALOG_DATA) data
              ) {
                this.skillsList = data;
                if (this.skillsList != null && this.skillsList.length !== 0 ){
                  this.isEmpty = false;
                }
                else{
                  this.isEmpty = true;
                }
   }

  ngOnInit(): void {
    for (const x of this.skillsList){
      this.skillOptions.splice(this.skillOptions.indexOf(x.skill), 1);
    }
    this.skillOptions.sort();
    this.skillsForm = this.fb.group({
      skill : ['', Validators.required],
      profiencyLevel : ['', Validators.required]
    });

    this.filteredOptions = this.skillsForm.get('skill').valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
  }

  private _filter(value: string): string[] {
    let filterValue = '';
    if (value != null){
      filterValue = value.toLowerCase();
    }

    return this.skillOptions.filter(option =>
      option.toLowerCase().includes(filterValue));
  }
  addSkill(): void{
    this.newSkill = true;
  }
  clearSkill(): void{
    this.newSkill = false;
    this.skillsForm.reset();
  }

  addConfirm(): void{
    if (this.skillsForm.value.skill !== null && this.skillsForm.value.skill.trim() !== ''
    && this.skillsForm.value.profiencyLevel !== null && this.skillsForm.value.profiencyLevel !== ''){
      this.skillsList.push(this.skillsForm.value);
      const index = this.skillOptions.indexOf(this.skillsForm.controls.skill.value);
      this.skillOptions.splice(index, 1);
      this.isEmpty = false;
      this.skillsForm.reset();

      this.filteredOptions = this.skillsForm.get('skill').valueChanges.pipe(
        startWith(''),
        map(value => this._filter(value))
      );
      this.newSkill = false;
    }
  }

  onDeletingSkill(skill: string): void{
    this.skillOptions.push(skill);
    this.skillOptions.sort();
    this.filteredOptions = this.skillsForm.get('skill').valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value))
    );
    for (let i = 0; i < this.skillsList.length; ++i){
      if (this.skillsList[i].skill === skill){
        this.skillsList.splice(i, 1);
      }
    }
    if (this.skillsList.length === 0){
      this.isEmpty = true;
    }
  }

  saveSkills(): void{
    this.skillsForm.reset();
    this.matDialogRef.close(this.skillsList);
  }

  cancel(): void{
    this.matDialogRef.close();
  }
}
