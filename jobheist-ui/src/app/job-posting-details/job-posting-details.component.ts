import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, ActivatedRoute } from '@angular/router';
import { ShortlistingService } from '../shortlisting.service';

@Component({
  selector: 'app-job-posting-details',
  templateUrl: './job-posting-details.component.html',
  styleUrls: ['./job-posting-details.component.css']
})
export class JobPostingDetailsComponent implements OnInit {

  constructor(private service: ShortlistingService, private router: Router, private route: ActivatedRoute, private _snackbar: MatSnackBar) { }

  candidates_auto = [];
  index: number;
  flag: number;
  id: number;
  id1: string;

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {this.id1 = params.id1; this.id = params.id; } );
    this.service.recommendedprofiles(this.id).subscribe(data => {console.log(data); this.candidates_auto = data; }, error => {
      if (error.status === 417){
        this._snackbar.open('Unable to fetch recommended profiles now, please try again later', 'close', {
          duration: 30000,
        });
      }
    }) ;
  }
}
