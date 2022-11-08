import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import {User} from "../../models/User";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user : User = {
    username: "",
    password: ""
  }

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.login(this.user)
  }

  goToRegisterPage() {
    console.log("going to register page")
    this.router.navigateByUrl("/register");
  }
}
