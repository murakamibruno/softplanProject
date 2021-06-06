import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Pessoa } from './pessoa';

@Injectable({
  providedIn: 'root'
})

export class PessoaService {


  baseUrl = 'http://localhost:8080/pessoa/';
  sourceUrl = 'http://localhost:8080/source/';

  constructor(private http: HttpClient) { }

  public createPerson(pessoa) {
    return this.http.post(this.baseUrl, pessoa);
  }

  public listPeople() {
    return this.http.get<Pessoa[]>(this.baseUrl);
  }

  public deletePerson(id) {
    return this.http.delete(this.baseUrl + id);
  }

  public getPersonById(id) {
    return this.http.get<Pessoa>(this.baseUrl + id);
  }

  public putPerson(pessoa) {
    return this.http.put(this.baseUrl + pessoa.id, pessoa);
  }

  public getSourceURL() {
    return this.http.get(this.sourceUrl);
  }

}
