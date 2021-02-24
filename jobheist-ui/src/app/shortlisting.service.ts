import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShortlistingService {

  constructor(private http: HttpClient, private cookie: CookieService) { }

  public recommendedprofiles(id: number): Observable<any>{
    return this.http.get(`http://localhost:8080/query-engine/api/v1/recommended-profiles/${id}`);
  }

}
