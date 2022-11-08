import { createAction, props } from '@ngrx/store'

export const login = createAction('[Login Page Component] Login', props<{ username: string }>());
export const logout = createAction('[Navbar Component] Logout')