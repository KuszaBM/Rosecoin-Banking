import { Component } from '@angular/core';
import {BaseClientService} from "../../services/base-client.service";
@Component({
  selector: 'app-register-window',
  templateUrl: './register-window.component.html',
  styleUrls: ['./register-window.component.css']
})
export class RegisterWindowComponent {

  constructor(private baseClientService : BaseClientService) {
  }
  register(username: string, password: string, email: string) {
    this.baseClientService.register(username, password, email).subscribe(data => {
      console.log("Response - " + data)
    })
  }
}
