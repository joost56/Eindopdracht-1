import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {login, logout} from '../actions/user.actions';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/User";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  username$: Observable<string>;
  router: Router;

  constructor(private store: Store<{ username: string }>, private httpClient: HttpClient, router: Router) {
    this.username$ = store.select('username')
    this.router = router;
  }

  register(user: User) {
    this.httpClient.post<User>('/users/register', {username: user.username, password: user.password})
      .subscribe((user) => {
        console.log("registered user with username: " + user.username)
      })
  }

  login(user: User) {
    this.httpClient.post<boolean>('/users/login', {
      username: user.username,
      password: user.password
    }).pipe().subscribe((result) => {
      if (result) {
        localStorage.setItem('username', user.username);
        this.store.dispatch(login({username: user.username}))
        this.router.navigateByUrl("/");
        console.log("logged in succesfully");
      }
      else {
        console.log("login failed")
      }
    });
  }

  logout() {
    console.log("Logging out")
    localStorage.setItem('username', '');
    this.store.dispatch(logout())
  }

  getUsername(): Observable<string> {
    return this.username$;
  }
}
