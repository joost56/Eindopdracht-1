import {User} from "./User";

export interface Task {
  id: number
  description: string;
  users: User[];
}
