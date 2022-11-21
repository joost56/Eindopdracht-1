import {Injectable} from '@angular/core';
import {Store} from '@ngrx/store';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {login, logout} from '../actions/user.actions';
import {HttpClient} from "@angular/common/http";
import {User} from "../models/User";
import {Router} from "@angular/router";
import {Column} from "../models/Column";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private users: Subject<User[]>;
  private userArray: User[] = [];
  private username$: Observable<string>;
  private router: Router;

  constructor(private store: Store<{ username: string }>, private httpClient: HttpClient, router: Router) {
    this.users = new BehaviorSubject<User[]>([]);
    this.username$ = store.select('username')
    this.router = router;
  }

  updateUsers(){
    this.httpClient.get<User[]>('/users/all').subscribe((userList) => {
      this.userArray = userList;
      this.users.next([...this.userArray])
    })
  }

  register(user: User) {
    console.log("trying to register user with username: " + user.username + " and password : " + user.password);
    this.httpClient.post<boolean>('/users/register', {username: user.username, password: user.password})
      .subscribe(() => {
        this.updateUsers();
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

  getUsers(): Observable<User[]> {
    return this.users;
  }

  getUsername(): Observable<string> {
    return this.username$;
  }
}
