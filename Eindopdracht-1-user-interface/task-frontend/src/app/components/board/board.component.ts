import {Component, Input, OnInit} from '@angular/core';
import {CdkDragDrop} from "@angular/cdk/drag-drop";
import {Column} from "../../models/Column";
import {Observable, of} from "rxjs";
import {Task} from "../../models/Task";
import {TaskService} from "../../services/task.service";
import {ColumnService} from "../../services/column.service";

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {
  task: Task = { content: ""}
  columns$: Observable<Column[]> = of([]);

  constructor(private taskService : TaskService, private columnService: ColumnService) { }

  ngOnInit(): void {
    this.columns$ = this.columnService.getColumns();
    this.columns$.subscribe(console.log);
  }

  ngOnChanges() : void {
  }

  addColumn() : void {
    this.columnService.createColumn()
  }

  addTask(columnId: number, taskContent: string) : void {
    this.columnService.addTask(columnId, taskContent);
  }

  drop = (event: CdkDragDrop<string[]>) => {
      if (event.previousContainer === event.container) {
        this.columnService.moveTaskInColumn(parseInt(event.container.id),
          event.previousIndex,
          event.currentIndex);
      } else {
        this.columnService.moveTaskBetweenColumns(parseInt(event.previousContainer.id),
          parseInt(event.container.id),
          event.previousIndex,
          event.currentIndex)
    }
    console.log(event.container.id)
    console.log(event.previousContainer.id)
    console.log(event.currentIndex)
    console.log(event.previousIndex)
  }

  test() {
    this.columnService.getColumnsTest();
  }
}
