import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecommendedJobsService {

  constructor(private http: HttpClient) { }

  getData(email: string): Observable<any>{
    return this.http.get('http://localhost:8080/query-engine/api/v1/recommended-jobs/' + email);
  }

  searchByName(jobname: string): Observable<any>{
    return this.http.get(`http://localhost:8080/query-engine/api/v1/jobs-search/?title=${jobname}`);
  }

  searchByFiltered(jobname?: string, skillname?: string, locationname?: string): Observable<any>{
    // tslint:disable-next-line: max-line-length
    return this.http.get(`http://localhost:8080/query-engine/api/v1/jobs-search/?title=${jobname}&skill=${skillname}&location=${locationname}` );
  }


}
