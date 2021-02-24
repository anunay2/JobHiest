import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecruitersPostingsService {

  constructor(private http: HttpClient) { }

  getAllPostings(emailid: string): Observable<any>{
    return this.http.get('http://localhost:8080/query-engine/api/v1/job-postings/' + emailid);
  }

  getPostingById(id: number): Observable<any>{
    return this.http.get('http://localhost:8080/query-engine/api/v1/job-details/' + id);
  }
}
