import { Component } from '@angular/core';
import  {Injectable} from "@angular/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'RoseBanking';
  login = true;
  loggedIn = false
  swapLogReg() {
    if(this.login)
      this.login = false
    else
      this.login = true
  }
  public setLoggedIn() {
    this.loggedIn = true;
  }
}
