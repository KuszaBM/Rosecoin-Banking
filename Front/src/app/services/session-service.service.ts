import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserName} from "./base-client.service";
@Injectable({
  providedIn: 'root'
})
export class SessionServiceService {
  private token : string;
  constructor(private httpClient : HttpClient) {
    this.token = "";
  }
  public setToken(token: string) {
    this.token = token;
  }
  public getVerifiedUsers() : Observable<string[]> {
    let requestUrl : string = "http://192.168.122.22:8080/session/" + this.token + "/getUsers";
    return this.httpClient.get<string[]>(requestUrl);
  }
  public getUserBaseData() : Observable<User> {
    let requestUrl : string = "http://192.168.122.22:8080/session/" + this.token + "/getUserBaseData";
    return this.httpClient.get<User>(requestUrl);
  }
  public transferCoins(username: string, coins: number) : Observable<TransferResponse> {
    let requestUrl : string = "http://192.168.122.22:8080/session/" + this.token + "/sendTo"
    return this.httpClient.post<TransferResponse>(requestUrl, {"username": username, "coins" : coins})
  }
}
export interface User {
  username:  string;
  coins: number;
}
export interface TransferResponse {
  responseCode: number;
  coins: number;
}
