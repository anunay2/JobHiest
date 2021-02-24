import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from '../user';


@Component({
  selector: 'app-choose-role',
  templateUrl: './choose-role.component.html',
  styleUrls: ['./choose-role.component.css']
})
export class ChooseRoleComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit(): void {
    
  }

  hire(){
    this._router.navigate(['/register'], {queryParams:{'role': Role.RECRUITER.valueOf()}} );
  
  }
  gethired(){
    this._router.navigate(['/register'], {queryParams:{'role': Role.JOBSEEKER.valueOf()}} );
  }

}
