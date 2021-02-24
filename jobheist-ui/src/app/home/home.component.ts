import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Jwtdecode } from '../jwt-decode';
import { Role } from '../user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  notLogged = true;
  isSeeker = false;
  isRecruiter = false;
  email: string;
  role: string;
  constructor(private cookie: CookieService, private _router: Router) { }

  ngOnInit(): void {
    this.email = new Jwtdecode(this.cookie).getEmailid();
    if (this.email === null){
      this.notLogged = true;
    }
    else{
      this.role = new Jwtdecode(this.cookie).getRole();
      if (this.role === 'JOBSEEKER'){
        this.notLogged = false;
        this.isRecruiter = false;
        this.isSeeker = true;
      }
      else{
        this.notLogged = false;
        this.isSeeker = false;
        this.isRecruiter = true;
      }
    }

  }

  goUp(): void{
    window.scrollTo(0, 0);
  }

  hire(){
    this._router.navigate(['/register'], {queryParams:{'role': Role.RECRUITER.valueOf()}} );
  
  }
  gethired(){
    this._router.navigate(['/register'], {queryParams:{'role': Role.JOBSEEKER.valueOf()}} );
  }

}
