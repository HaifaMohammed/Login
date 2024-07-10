import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {FormsModule} from "@angular/forms";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginObject: Login;
  resultObject: Results;

  constructor(private http: HttpClient, private router: Router) {

    this.loginObject = new Login();
    this.resultObject = new Results();
  }

  onLogin(): void {
    debugger;

    this.http.post('http://DEV-1261.mshome.net:8080/copyMCP/webapi/Login', this.loginObject).subscribe((res: any)=> {

      this.resultObject = res;
      if(this.resultObject.type === "Doctor")
      {
        alert("login success");
        this.router.navigateByUrl('/doctor-consults');
      }
      else if(this.resultObject.type === "Patient")
      {
        alert("login success");
        this.router.navigateByUrl('/dashboard');
      }
      else
      {
        alert("invalid data");
      }

      });




    // if(this.loginObject.email === 'admin' && this.loginObject.password === 'admin')
    // {
    //   alert("login success");
    //   this.router.navigateByUrl('/dashboard');
    // }
    // else {
    //   alert("invalid data");
    // }

  }
}

export class Login{
  LOGIN_email: String;
  LOGIN_password: String;

  constructor() {
    this.LOGIN_email = '';
    this.LOGIN_password = '';
  }
}
export class Results {
  id: number;
  type: String;

  constructor() {
    this.id = 0;
    this.type = '';
  }
}
