import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ColumnComponent} from "./components/column/column.component";

const routes: Routes = [
  {
    path: '**',
    component: ColumnComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
