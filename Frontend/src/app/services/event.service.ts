import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Event} from "../models/event.model";

@Injectable({providedIn: "root"})
export class EventService {
  private apiServerUrl = '';
  constructor(private http: HttpClient) { }
  public getAllEvents(): Observable<Event[]>{
    return this.http.get<Event[]>(`${this.apiServerUrl}/api/events`);
  }
  public addEvent(event: Event): Observable<Event>{
    return this.http.post<Event>(`${this.apiServerUrl}/api/events/create`, event);
  }
  public updateEvent(event: Event, eventId: String): Observable<Event>{
    return this.http.put<Event>(`${this.apiServerUrl}/api/events/myEvents/${eventId}`, event);
  }

}
