import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { RegistrationLoginService } from './registration-login.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private _loginService: RegistrationLoginService,private _router: Router){}
  canActivate(): boolean{
    if(this._loginService.loggedIn()){
      return true;
    } else {
      this._router.navigate(['login']);
      return false;
    }
  }
}