import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BaseClientService {

  constructor(private httpClient : HttpClient) { }

  public logIn(username: string, password: string): Observable<LoginResponse> {
    return this.httpClient.post<LoginResponse>("http://192.168.122.22:8080/base/logIn",{"username": username, "password": password });
  }
  public register(username: string, password: string, email: string): Observable<string> {
    return this.httpClient.post<string>("http://192.168.122.22:8080/base/register",{"username": username, "password": password, "email": email});
  }

  public getVerifiedUsers() : Observable<UserName[]> {
    return this.httpClient.get<UserName[]>("http://192.168.122.22:8080/base/getVerifiedUsers");
  }

}
export interface LoginResponse {
  status:    number;
  token:     string;
  username:  string;
  ipAddress: string;
}

export interface UserName {
  username: string;
}
