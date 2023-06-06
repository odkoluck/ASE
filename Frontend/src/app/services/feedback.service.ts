// @ts-ignore
import { Injectable } from '@angular/core';
// @ts-ignore
import { HttpClient, HttpHeaders } from '@angular/common/http';
// @ts-ignore
import { Observable } from 'rxjs';
// @ts-ignore
import { Feedback } from '../models/feedback.model';

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private baseUrl = 'http://localhost:8080/feedbacks';

  constructor(private http: HttpClient) { }

  getAllFeedbacks(): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.baseUrl}`);
  }

  getFeedbackById(id: string): Observable<Feedback> {
    return this.http.get<Feedback>(`${this.baseUrl}/${id}`);
  }

  getFeedbacksByEventId(eventId: string): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.baseUrl}/event/${eventId}`);
  }

  getFeedbacksByUserId(userId: string): Observable<Feedback[]> {
    return this.http.get<Feedback[]>(`${this.baseUrl}/user/${userId}`);
  }

  createFeedback(eventId: string, feedback: Feedback, tokenId: string): Observable<any> {
    const headers = new HttpHeaders().set('tokenId', tokenId);
    return this.http.post<any>(`${this.baseUrl}/${eventId}/create`, feedback, { headers });
  }

  deleteFeedback(id: string, tokenId: string): Observable<any> {
    const headers = new HttpHeaders().set('tokenId', tokenId);
    return this.http.delete<any>(`${this.baseUrl}/delete/${id}`, { headers });
  }

  updateFeedback(id: string, feedback: Feedback, tokenId: string): Observable<any> {
    const headers = new HttpHeaders().set('tokenId', tokenId);
    return this.http.put<any>(`${this.baseUrl}/update/${id}`, feedback, { headers });
  }
}
