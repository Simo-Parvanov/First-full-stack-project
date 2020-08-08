import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {tap} from 'rxjs/operators';


const API_URL = 'http://localhost:8080/api/test/';
const USER_API = 'http://localhost:8080/mod/';
const STATISTIC = 'http://localhost:8080/statistic/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }
  findAll() {
    return this.http.get(USER_API);
  }

  deleteUser(username) {
    return this.http.delete(USER_API + username);
  }
// .pipe(tap(console.log)
  updateRole(params): Observable<any>{
    console.log(params, 'service-class')
    return this.http.post(USER_API + 'update', params, httpOptions).pipe(tap(console.log));
  }

  getStat(): Observable<any>{
    return this.http.get<any>(STATISTIC).pipe(tap(console.log));
  }
}
