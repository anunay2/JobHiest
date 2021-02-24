import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';
import { User } from './user';


@Injectable({
  providedIn: 'root'
})
export class RegistrationLoginService {

  
  constructor(private _http : HttpClient, private cookie: CookieService) { }
  
  private consumeToken():HttpHeaders{
   
    return new HttpHeaders({'Authorization':  this.cookie.get("Authorization")});
  }

  public loginUserFromRemote(user:User):Observable<any>{
    return this._http.post("http://localhost:8080/authentication/api/v1/login",user,
    { headers: new HttpHeaders({ 'Access-Control-Allow-Origin' : '*'}) }); 
    //{responseType: 'text'});
  }
  public registerUserFromRemote(user : User):Observable<any>{
    return this._http.post<any>("http://localhost:8080/authentication/api/v1/registeruser",user,
    { headers: new HttpHeaders({ 'Access-Control-Allow-Origin' : '*'}) }); 
  }

  public getSensitiveData():Observable<any>{
    return this._http.get<any>("http://localhost:8080/authentication/api/v1/sensitive", 
    { headers: this.consumeToken() }); 
  }

  loggedIn():boolean{
    return !!this.cookie.get("Authorization");
   }

}
