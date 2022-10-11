import { Component, OnInit } from '@angular/core';
import {Task} from "../../models/Task";
import {TaskService} from "../../services/task.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  task : Task = {
    content: "",
  }
  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
  }

  addTask() {
    this.taskService.createTask(this.task);
  }

}
