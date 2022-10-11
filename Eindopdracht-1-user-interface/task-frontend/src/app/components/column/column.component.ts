import { Component, OnInit } from '@angular/core';
import { Task } from "../../models/Task";
import {Observable, of} from "rxjs";
import {TaskService} from "../../services/task.service";

@Component({
  selector: 'app-column',
  templateUrl: './column.component.html',
  styleUrls: ['./column.component.css']
})
export class ColumnComponent implements OnInit {
  tasks$ : Observable<Task[]> = of([]);

  constructor(private taskService : TaskService) { }

  ngOnInit(): void {
    this.tasks$ = this.taskService.getTasks();
    this.tasks$.subscribe(console.log);
  }

  ngOnChanges() : void {
    console.log("Showing tasks: ", this.tasks$);
  }

}
