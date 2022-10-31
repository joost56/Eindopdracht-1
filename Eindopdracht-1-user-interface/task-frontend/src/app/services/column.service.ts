import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {Task} from "../models/Task";
import {Column} from "../models/Column";
import {SwitchColumn} from "../models/SwitchColumn";
import {HttpClient} from "@angular/common/http";

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
    this.httpClient.get<Column[]>('boards/columns').subscribe((columnList) => {
      this.columnsArray = columnList;
      this.columns.next([...this.columnsArray])
    })
  }

  createColumn(name: string) {
    this.httpClient.post<Column>('boards/columns', {columnId: name})
      .subscribe(() => {
      this.updateColumns();
    })
  }

  addTask(columnId: string, taskDescription: string) {
    this.httpClient.post<Task>('/boards/tasks', {columnId: columnId, taskDescription: taskDescription})
      .subscribe(() => {
        this.updateColumns()
      });
  };

  moveTaskBetweenColumns(previousColumnId: number, nextColumnId: number, previousIndex: number, nextIndex: number) {
    // const task = this.columnsArray[previousColumnId].tasks[previousIndex]
    // this.columnsArray[nextColumnId].tasks.splice(nextIndex, 0, task);
    // this.columnsArray[previousColumnId].tasks.splice(previousIndex, 1);
    // this.columns.next([...this.columnsArray]);
    const task = this.columnsArray[previousColumnId].tasks[previousIndex]
    this.httpClient.put<SwitchColumn>('/boards/columns/switch',
      {oldColumnId: previousColumnId, newColumnId: nextColumnId, taskId: task.id})
      .subscribe(() => {
        this.updateColumns();
      });
  }
}
