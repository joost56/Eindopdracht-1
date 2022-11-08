import { Component, OnInit } from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user : User = {
    username: "",
    password: ""
  }

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  register() {
    this.userService.register(this.user)
  }
}
