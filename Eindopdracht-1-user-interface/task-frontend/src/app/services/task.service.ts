import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {Task} from "../models/Task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private tasks : Subject<Task[]>;
  private tasksArray : Task[] = [];

  constructor() {
    this.tasks = new BehaviorSubject<Task[]>([]);
  }

  getTasks(): Observable<Task[]> {
    return this.tasks;
  }

  createTask(task : Task) {
    this.tasksArray.push(task);
    this.tasks.next([...this.tasksArray]);
  }
}
