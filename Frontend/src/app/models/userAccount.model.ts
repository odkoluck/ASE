import {Token} from "./token.model";

export interface UserAccount{
  id: String,
  emailAddress: String,
  name: String,
  password: String,
  token?: Token
  userRole: UserRole
}
export enum UserRole{
  ADMINISTRATOR = "ADMINISTRATOR",
  ORGANIZER = "ORGANIZER",
  ATTENDEE = "ATTENDEE"
}
