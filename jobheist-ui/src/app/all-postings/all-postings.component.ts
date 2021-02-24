import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { RecruitersPostingsService } from '../recruiters-postings.service';
import { Jwtdecode } from '../jwt-decode';
import { ActivatedRoute, Router } from '@angular/router';
import { Job } from '../job';
import * as moment from 'moment';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-all-postings',
  templateUrl: './all-postings.component.html',
  styleUrls: ['./all-postings.component.css']
})
export class AllPostingsComponent implements OnInit {
  obs = [];
  now: Date;
    instant: number;
  constructor(private service: RecruitersPostingsService, private cookie: CookieService,
              private router: Router,  private route: ActivatedRoute, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.now = new Date();
    this.instant = this.now.getTime();
    this.instant = Math.round(this.instant / 1000);
    this.service.getAllPostings(new Jwtdecode(this.cookie).getEmailid()).subscribe(
      data => {
        console.log(data);
        this.obs = data;
      }, error => {
        if(error.status === 417){
          this._snackBar.open("Unable to get your postings now, please try again later", "close", {
            duration: 30000,
          });
        }
      }
    );
  }
  /*
  showshortlisted(id1: string, id: number): void{
    this.router.navigate(['shortlisted'], {queryParams:{'id1': id1, 'id': id} , relativeTo: this.route});
  }
  */
  /* No need of passing extra query param */
  showshortlisted(id: number): void{
    this.router.navigate(['shortlisted'], {queryParams: { id} , relativeTo: this.route});
  }

  wasPosted(time: number): string{
    // let temp = this.instant - time ;
    // temp = Math.round(temp / 60);
    // if (temp < 1){return 1; }
    // return temp;

    return moment(time*1000).fromNow();

  }

}
