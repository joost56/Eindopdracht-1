import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {Task} from "../models/Task";
import {Column} from "../models/Column";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ColumnService {
  private columnCounter = 0;
  private columns : Subject<Column[]>;
  private columnsArray : Column[] = [];

  constructor(private httpClient: HttpClient) {
    this.columns = new BehaviorSubject<Column[]>([]);
  }

  getColumns(): Observable<Column[]> {
    return this.columns;
  }

  createColumn() {
    this.columnsArray.push({id: this.columnCounter, tasks: []});
    this.columns.next([...this.columnsArray]);
    this.columnCounter += 1
  }

  addTask(columnId: number, taskContent: string) {
    this.columnsArray[columnId].tasks.push({content: taskContent})
    this.columns.next([...this.columnsArray]);
  }

  moveTaskBetweenColumns(previousColumnId: number, nextColumnId: number, previousIndex: number, nextIndex: number) {
    const task = this.columnsArray[previousColumnId].tasks[previousIndex]
    this.columnsArray[nextColumnId].tasks.splice(nextIndex, 0, task);
    this.columnsArray[previousColumnId].tasks.splice(previousIndex, 1);
    this.columns.next([...this.columnsArray]);
  }

  moveTaskInColumn(columnId: number, previousIndex: number, nextIndex: number) {
    const task = this.columnsArray[columnId].tasks[previousIndex]
    this.columnsArray[columnId].tasks.splice(previousIndex, 1);
    this.columnsArray[columnId].tasks.splice(nextIndex, 0, task);
    this.columns.next([...this.columnsArray]);
  }

  getColumnsTest() {
    this.httpClient.post<Column>('/boards/columns', {columnId: "columntest"}).subscribe(console.log);
  }
}
