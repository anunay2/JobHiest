import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Jwtdecode } from '../jwt-decode';
import { RegistrationLoginService } from '../registration-login.service';
import { User } from '../user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user = new User();
  userrole: string;
  
  emailid: string;
  password: string;
  constructor(private cookie: CookieService, private _service: RegistrationLoginService,
    private _snackBar: MatSnackBar, private _router: Router) { }

  ngOnInit(): void {
  }

  onLoginSubmit(){
    if(this.emailid === undefined || this.emailid === '' || this.password === undefined || this.password === ''){
      this._snackBar.open("Type in your email id and password and try logging in again", "close", {
        duration: 4000,
      });
    }
    else{
      this.user.emailid = this.emailid;
      this.user.password = this.password;
      this._service.loginUserFromRemote(this.user).subscribe(
        data => {
          console.log(data);
          // for production, secureFlag = true while setting cookie
          this.cookie.set('Authorization', 'Bearer ' + data.token, 7, '', '', false, 'Strict');
          this.userrole = new Jwtdecode(this.cookie).getRole();
         // console.log(this.userrole);
          if (this.userrole === 'JOBSEEKER'){
            this._router.navigate(['job-seeker']);
          }
          else if (this.userrole === 'RECRUITER'){
            this._router.navigate(['job-recruiter']);
          }

        },
        error => {
          if(error.status === 417){
            this._snackBar.open("Unable to authenticate you, please try again later", "close", {
              duration: 4000,
            });
          }
          else{
            this._snackBar.open("Bad credentials, please enter valid email id and password", "close", {
              duration: 4000,
            });
          }

      }
    );

    }
  }

}
