import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserAccount} from "../models/userAccount.model";
import {Token} from '../models/token.model'

@Injectable({providedIn: 'root'})
export class LoginService{
  private apiServerUrl = '';
  constructor(private http: HttpClient) {}

  public login(emailAddress: String, password: String): Observable<Token>{
    return this.http.post<Token>(`${this.apiServerUrl}/api/login`, {emailAddress, password})
  }

  public saveTokenId(token: Token): void{
    localStorage.setItem('tokenId', token.tokenId);
  }
  public isAuthenticated(): boolean{
    return !!localStorage.getItem('tokenId');
  }


}
