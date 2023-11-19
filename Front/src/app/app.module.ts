import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LoginWindowComponent } from './components/login-window/login-window.component';
import { RegisterWindowComponent } from './components/register-window/register-window.component';
import { HttpClientModule} from "@angular/common/http";
import { UserInterfaceComponent } from './components/user-interface/user-interface.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginWindowComponent,
    RegisterWindowComponent,
    UserInterfaceComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
