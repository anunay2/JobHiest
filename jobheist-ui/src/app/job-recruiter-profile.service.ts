import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JobRecruiterProfile } from './JobRecruiterProfile';

@Injectable({
  providedIn: 'root'
})
export class JobRecruiterProfileService {

  constructor(private http: HttpClient) { }

  editJobRecruiter(value: JobRecruiterProfile): Observable<JobRecruiterProfile>{
    return this.http.put<JobRecruiterProfile>('http://localhost:8080/jobrecruiterprofile/api/v1/rprofile',value);
  }

  getJobRecruiterByEmail(email): Observable<JobRecruiterProfile>{
    return this.http.get<JobRecruiterProfile>('http://localhost:8080/jobrecruiterprofile/api/v1/rprofile/' + email);
  }

  getJobRecruiter(): Observable<JobRecruiterProfile[]>{
    return this.http.get<JobRecruiterProfile[]>('http://localhost:8080/jobrecruiterprofile/api/v1/rprofiles');
  }

  addJobRecruiter(value){
    return this.http.post('http://localhost:8080/jobrecruiterprofile/api/v1/rprofile',value);
  }  

}
