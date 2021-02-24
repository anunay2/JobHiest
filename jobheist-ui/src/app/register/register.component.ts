import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { FormGroup, FormBuilder, Validators} from '@angular/forms';
import { RegistrationLoginService } from '../registration-login.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from '../user';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
user: User =new User();
registerForm:FormGroup;
hide =true;
 msg='';

  constructor(private formBuilder: FormBuilder
     ,private _service:RegistrationLoginService,private _router:Router, private _route:ActivatedRoute,
     private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this._route.queryParams.subscribe(param => {this.user.role=param["role"]});
    this.registerForm = this.formBuilder.group({
      'emailId' : [this.user.emailid, [Validators.required,Validators.email]],
      'userName' : [this.user.username,[Validators.required]],
      'password' : [this.user.password,[Validators.required,Validators.minLength(6),Validators.maxLength(30)]]
    });
  }
onRegisterSubmit(){
  this._service.registerUserFromRemote(this.user).subscribe(
    data=>{
      //console.log("response received");
      this.msg="Registration Successful";
      this._router.navigate(['login']);
    },
    error=>{
      //console.log("exception occured");
      if(error.status === 417){
        this._snackBar.open("Unable to Register you now, please try again later", "close", {
          duration: 30000,
        });
      }
      else{
        this.msg = 'Account with this emailid already exists';
      }
      
      
    }
  );

}
}
