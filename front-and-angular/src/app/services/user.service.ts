import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {tap} from 'rxjs/operators';


const API_URL = 'http://localhost:8080/api/test/';
const USER_API = 'http://localhost:8080/mod/';
const USER_DEL = 'http://localhost:8080/mod/';

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
    return this.http.delete(USER_API + username).pipe(tap(console.log));
  }
  updateRole(username: string, method: string, role: string): Observable<any>{
    return this.http.post(USER_API + username + '/' + method + '/' + role, httpOptions).pipe(tap(console.log));
  }
}
