import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})

export class SourceService {

  sourceUrl = 'http://localhost:8080/source/';

  constructor(private http: HttpClient) { }

  public getSourceURL() {
    return this.http.get(this.sourceUrl);
  }

}
