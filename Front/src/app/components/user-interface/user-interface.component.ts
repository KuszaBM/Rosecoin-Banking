import {Component, signal} from '@angular/core';
import {BaseClientService, UserName} from "../../services/base-client.service";
import {SessionServiceService, User} from "../../services/session-service.service";
import {single} from "rxjs";

@Component({
  selector: 'app-user-interface',
  templateUrl: './user-interface.component.html',
  styleUrls: ['./user-interface.component.css']
})
export class UserInterfaceComponent {
  users : string[] = [];
  user : User = {"username":"noData", "coins":0}
  coins : number = 0;
  coinsS = signal(0)
  constructor(private baseClientService : BaseClientService, private sessionService : SessionServiceService) {
    this.getUserData()
    this.refreshUsers()
  }

  changeCoins(event: Event) {
    const coins = (event.target as HTMLInputElement).value
    this.coins = Number(coins);
  }
  public refreshUsers() {
    this.sessionService.getVerifiedUsers().subscribe(data => {
      this.users = data
      for(let i = 0; i < data.length; i++) {
        console.log(data[i])
      }
      for(let i = 0; i < data.length; i++) {
        console.log(this.users[i])
      }
    });
  }

  public getUserData() {
    this.sessionService.getUserBaseData().subscribe(data => this.user = data)
  }

  public sentTo(username: string, coins: number) {
    this.sessionService.transferCoins(username, coins).subscribe(data => this.coins = data.coins)
  }
}

