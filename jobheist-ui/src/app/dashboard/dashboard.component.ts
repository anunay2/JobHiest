import { Time } from '@angular/common';
import { ChangeDetectorRef, Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { Job } from '../job';
import { JobApplyComponent } from '../job-apply/job-apply.component';
import { JobSeekerProfileService } from '../job-seeker-profile.service';
import { JobSeekerProfile } from '../JobSeekerProfile';
import { Jwtdecode } from '../jwt-decode';
import { RecommendedJobsService } from '../recommended-jobs.service';


//   const fields: field[] = [
//     {
//     Organisation: 'XYZ',
//     Jobtitle: 'Jobtitle',
//     Location: 'Location here',
//     Experience:'2 years',
//     Skills:['Spring', 'Angular'],
//     Salary: '7 LPA'
//     },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//     {Organisation: 'Organisation', Jobtitle: 'Jobtitle', Location: 'Location here', Experience:'2 years', Skills:['Spring', 'Angular'],Salary: '7 LPA'   },
//   ];

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
  })
  export class DashboardComponent implements OnInit{

    // for navbar
    circleColor: string;
    personalInfo: JobSeekerProfile = new JobSeekerProfile();
    colors = ['#EB7181', '#468547'];
    initial: string;
    // searchTerm: string;
    search: string;
    filters = false;

    filter_location: string;
    filter_role: string;
    filter_skill: string;

    now: Date;
    instant: number;



    // for navbar ends

    field = [];
    dataSource: any;
    dataSourceNS: any;
    dataSourceFS: any;

    loggedinEmail: string;
    constructor(private changeDetectorRef: ChangeDetectorRef, private service: RecommendedJobsService, private dialog: MatDialog,
                private cookie: CookieService, private router: Router,
                private jobSeekerService: JobSeekerProfileService, private _snackBar: MatSnackBar ) {

    }

    @ViewChild(MatPaginator) paginator: MatPaginator;
    @ViewChild(MatPaginator) paginatorNS: MatPaginator;
    @ViewChild(MatPaginator) paginatorFS: MatPaginator;

    jobs: Observable<any>;
    fixed: Observable<any>;
    obs: Observable<any>;

  ngOnInit(): void {
    this.loggedinEmail = new Jwtdecode(this.cookie).getEmailid();

    // for navbar
    let randomIndex = 0;
    if (this.loggedinEmail.charAt(0).toUpperCase() > 'M'){
        randomIndex = 1;
      }
    this.circleColor = this.colors[randomIndex];
    this.jobSeekerService.getJobSeekerByEmail(this.loggedinEmail).subscribe((data) => {

      this.personalInfo = data;
      if (data.appliedJobId === null){
        this.personalInfo.appliedJobId = [];
      }
      this.createInitials();
    }, error => {
      if(error.status === 417){
        this._snackBar.open("Unable to retrieve your profile now, please try again later", "close", {
          duration: 30000,
        });
      }
    }
    );


    // for navbar ends

    this.service.getData(this.loggedinEmail).subscribe(
      data => {
        this.dataSource = new MatTableDataSource<Element>(data);
        this.dataSource.paginator = this.paginator;
        this.field = data;
        // this.fields = this.field;
        // this.dataSource = new MatTableDataSource<any>(this.field);
        // console.log(this.dataSource);
        this.changeDetectorRef.detectChanges();
        this.jobs = this.dataSource.connect();
        // this.jobs._value.forEach(x => this.createdon.push(x.createdOn));
        // this.createdon.forEach(x => this.duration.push((instant - x) / 60));
        // console.log(this.duration);
        this.fixed = this.jobs;
        this.obs = this.jobs;

        // dataSource: MatTableDataSource<Field> = new MatTableDataSource<Field>(this.fields);
      }, error => {
        if(error.status === 417){
          this._snackBar.open("Unable to retrieve recommended profiles, please try again later", "close", {
            duration: 30000,
          });
        }
      }
    );

  }

    // this.dataSource.paginator = this.paginator;

    // navbar with custom search code

    private createInitials(): void{
      if (this.personalInfo.name != null){
        const name = this.personalInfo.name.trim();
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

    logout(): void{
      this.router.navigate(['login'], {queryParams: {state: 'loggedout'}});
      this.cookie.deleteAll();
    }

    // navbar with custom search code ends

    searchnow(): void{

      // this.searchTerm = this.search;
      if (this.search !== undefined && this.search !== ''){
        this.service.searchByName(this.search).subscribe(
          data => {

            this.dataSourceNS = new MatTableDataSource<Element>(data);
            this.dataSourceNS.paginator = this.paginator;

            this.changeDetectorRef.detectChanges();
            this.obs = this.dataSourceNS.connect();

          }, error => {
            if(error.status === 417){
              this._snackBar.open("Unable to search now, please try again later", "close", {
                duration: 30000,
              });
            }
          });
      }
    }

    clear(): void{
      // this.searchTerm = '';
      this.search = '';
      this.filter_location = '';
      this.filter_role = '';
      this.obs = this.fixed;
      this.dataSource.paginator = this.paginator;

    }

    searchnowFilter(): void{

      if (this.filter_location === undefined){
        this.filter_location = '';
      }
      if (this.filter_role === undefined){
        this.filter_role = '';
      }
      if (this.filter_skill === undefined){
        this.filter_skill = '';
      }

      this.service.searchByFiltered(this.filter_role, this.filter_skill, this.filter_location).subscribe(
        data => {

          this.dataSourceFS = new MatTableDataSource<Element>(data);
          this.dataSourceFS.paginator = this.paginator;

          this.changeDetectorRef.detectChanges();
          this.obs = this.dataSourceFS.connect();

        }, error => {
          if(error.status === 417){
            this._snackBar.open("Unable to search now, please try again later", "close", {
              duration: 30000,
            });
          }
        });

    }


    /* Clicking the APPLY NOW CALLS THIS METHOD */

    jobApply(id: number): void{
      if (this.personalInfo.appliedJobId !== null && this.personalInfo.appliedJobId.includes(id)){
        this.router.navigate(['/dashboard'], {queryParams: {id: id, a: 'Y' }});
      }
      else{
        this.router.navigate(['/dashboard'], {queryParams: {id: id, a: 'N'}});
      }
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = false;
      dialogConfig.minWidth = '350px';
      const applied = this.checkIfApplied(id);
      dialogConfig.data = applied;
      const dialogRef = this.dialog.open(JobApplyComponent, dialogConfig);
      dialogRef.afterClosed().subscribe((value) => {
        if (value === undefined){
        }
        else{
          this.personalInfo.appliedJobId.push(id);
          console.log(this.personalInfo.appliedJobId);
          this.jobSeekerService.editJobSeeker(this.personalInfo).subscribe((data) => {
            this.personalInfo = data;
            console.log('after putting jobid:');
            console.log(data);
          });
        }
        this.router.navigate(['/dashboard'] );
      }, error => {
        if(error.status === 417){
          this._snackBar.open("Unable to apply for this job now, please try again later", "close", {
            duration: 30000,
          });
        }
      });

    }

    wasPosted(time: number): string{
      // let temp = this.instant - time ;
      // temp = Math.round(temp / 60);
      // if (temp < 1){return 1; }
      // return temp;

      return moment(time*1000).fromNow();

    }


    checkIfApplied(id: number): boolean{
      if (this.personalInfo.appliedJobId !== null && this.personalInfo.appliedJobId.includes(id)){
        return true;
      }
      return false;
    }
}
