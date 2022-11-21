import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {Task} from "../models/Task";
import {Column} from "../models/Column";
import {SwitchColumn} from "../models/SwitchColumn";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/User";

@Injectable({
  providedIn: 'root'
})
export class ColumnService {
  private columns: Subject<Column[]>;
  private columnsArray: Column[] = [];

  constructor(private httpClient: HttpClient) {
    this.columns = new BehaviorSubject<Column[]>([]);
  }

  getColumns(): Observable<Column[]> {
    return this.columns;
  }

  updateColumns() {
    this.httpClient.get<Column[]>('/boards/columns').subscribe((columnList) => {
      console.log("columns updated with list: ")
      console.log(columnList)
      this.columnsArray = columnList;
      this.columns.next([...this.columnsArray])
    })
  }

  createColumn(name: string) {
    console.log("columname: " + name);
    this.httpClient.post<Column>('/boards/columns', {columnId: name})
      .subscribe((column) => {
        console.log("created column: " + column);
      this.updateColumns();
    })
  }

  addTask(columnId: string, taskDescription: string) {
    this.httpClient.post<Task>('/boards/tasks', {columnId: columnId, taskDescription: taskDescription})
      .subscribe(() => {
        this.updateColumns();
      });
  };

  addUserToTask(username: string, taskId: string) {
    this.httpClient.post<User[]>('/users/assign', {
      username: username,
      taskId: taskId
    }).subscribe(() => {
      this.updateColumns();
    });
  }

  moveTaskBetweenColumns(previousColumnId: string, nextColumnId: string, previousIndex: number) {
    const task = this.columnsArray.filter((column) => column.id == previousColumnId)[0].tasks[previousIndex]
    this.httpClient.put<SwitchColumn>('/boards/columns/switch',
      {oldColumnId: previousColumnId, newColumnId: nextColumnId, taskId: task.id})
      .subscribe(() => {
        this.updateColumns();
      });
  }

  getColumnsTest() {
    this.httpClient.post<Column>('/boards/columns', {columnId: "columntest"}).subscribe(console.log);
  }
}
