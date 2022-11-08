import { Action, createReducer, on, State } from "@ngrx/store";
import { login, logout } from "../actions/user.actions";

export const initialState = "";

const _userReducer = createReducer(
    initialState,
    on(login, (_state, action) => {
        const username = action.username;
        console.log("Logging in with username ", username);
        return username;
    }),
    on(logout, (_state) => '')
);

export function  userReducer(state: string | undefined, action: Action) {
    return _userReducer(state, action);
}