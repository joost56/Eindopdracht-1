import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Task } from "../../models/Task";
import {Observable, of} from "rxjs";
import {TaskService} from "../../services/task.service";
import {Column} from "../../models/Column";
import {ColumnService} from "../../services/column.service";
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {User} from "../../models/User";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-column',
  templateUrl: './column.component.html',
  styleUrls: ['./column.component.css']
})
export class ColumnComponent implements OnInit {
  @Input() drop!: (event: CdkDragDrop<string[]>) => void;
  _column : Column = {id: "id was not properly set", tasks: []};
  connectedTo: string[] = [];
  tasksContent$: Observable<string[]> = of([]);
  tasks$: Observable<Task[]> = of([]);
  id: string = "";
  panelOpenState = false;
  users$: Observable<User[]> = of([]);

  constructor(private userService: UserService, private columnService: ColumnService) { }

  @Input() set column(value: Column) {
    this._column = value;
    this.tasks$ = of(value.tasks);
    this.tasksContent$ = of(value.tasks.map(function (task) {
      return task.description;
    }))
    this.id = value.id;
    this.connectedTo.push(value.id.toString());
  }

  ngOnInit(): void {
    this.tasks$ = of(this._column.tasks);
    this.tasks$.subscribe(console.log);
    this.users$ = this.userService.getUsers()
  }

  ngOnChanges() : void {
  }

  addUserToTask(username: string, taskId: string) {
    this.columnService.addUserToTask(username, taskId);
  }

}
