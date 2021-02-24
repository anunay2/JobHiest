import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobSeekerProfile } from './JobSeekerProfile';

@Injectable({
  providedIn: 'root'
})
export class JobSeekerProfileService {

  constructor(private http: HttpClient) { }

  addJobSeeker(value){
    return this.http.post('http://localhost:8080/jobprofile/api/v1/profile', value);
  }

  editJobSeeker(value: JobSeekerProfile): Observable<JobSeekerProfile>{
    return this.http.put<JobSeekerProfile>('http://localhost:8080/jobprofile/api/v1/profile', value);
  }

  getJobSeeker(): Observable<JobSeekerProfile[]>{
    return this.http.get<JobSeekerProfile[]>('http://localhost:8080/jobprofile/api/v1/profiles');
  }

  getJobSeekerByEmail(email: string): Observable<JobSeekerProfile>{
    return this.http.get<JobSeekerProfile>('http://localhost:8080/jobprofile/api/v1/profile/' + email);
  }
}
