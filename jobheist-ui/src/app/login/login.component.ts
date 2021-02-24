import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { RegistrationLoginService } from '../registration-login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { Jwtdecode } from '../jwt-decode';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
user = new User();
loginForm: FormGroup;
hide = true;
msg = '';
userrole: string;

  constructor(private formBuilder: FormBuilder , private _service: RegistrationLoginService,
     private _router: Router, private cookie: CookieService, private _route: ActivatedRoute,
     private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      'emailId' : [this.user.emailid, [Validators.required, Validators.email]],
      'password' : [this.user.password, [Validators.required, Validators.minLength(6), Validators.maxLength(30)]]
    });
    
  }
  onLoginSubmit(){
    this._service.loginUserFromRemote(this.user).subscribe(
      data => {
        console.log(data);
        // for production, secureFlag = true while setting cookie
        this.cookie.set('Authorization', 'Bearer ' + data.token, 7, '', '', false, 'Strict');
        this.msg = 'login successful';
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
        console.log("exception occured");
        if(error.status === 417){
          this._snackBar.open("Unable to authenticate you, please try again later", "close", {
            duration: 30000,
          });
        }
        else{
          this.msg = 'Bad credentials, please enter valid email id and password';
        }
        
      }
    );

  }
}
