import { Component } from '@angular/core';
import {BaseClientService} from "../../services/base-client.service";
import {AppComponent} from "../../app.component";
import {SessionServiceService} from "../../services/session-service.service";

@Component({
  selector: 'app-login-window',
  templateUrl: './login-window.component.html',
  styleUrls: ['./login-window.component.css']
})
export class LoginWindowComponent {

  constructor(private  baseClientService : BaseClientService,private sessionService : SessionServiceService, private a : AppComponent) {
  }
  logIn(username: string, password: string) {
    this.baseClientService.logIn(username, password).subscribe(data => {
      console.log("Created new session for user " + data.username + " on Ip address " +data.ipAddress + " | token [" + data.token + "]")
      this.sessionService.setToken(data.token);
      this.a.setLoggedIn();
    })
  }
}
