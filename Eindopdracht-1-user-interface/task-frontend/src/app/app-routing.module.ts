import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BoardComponent} from "./components/board/board.component";
import {LoginComponent} from "./components/login/login.component";
import {IsLoggedInGuard} from "./guards/is-logged-in.guard";
import {RegisterComponent} from "./components/register/register.component";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component:RegisterComponent
  },
  {
    path: '**',
    component: BoardComponent,
    canActivate: [IsLoggedInGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
