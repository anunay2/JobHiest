import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from './job';

@Injectable({
  providedIn: 'root'
})
export class JobUpdateService {

  constructor(private _http : HttpClient) {
   }

  public updateJob(job:any):Observable<any>{
    return this._http.post<any>("http://localhost:8080/job-indexing/jobs",job); 

  }
}
