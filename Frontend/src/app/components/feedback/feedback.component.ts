import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subscription } from 'rxjs';

export interface Feedback {
  id: string;
  eventId: string;
  userId: string;
  location: number;
  accuracy: number;
  performance: number;
  rating: number;
  description: string;
}

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html'
})

export class FeedbackComponent implements OnInit, OnDestroy {
  feedbacks: Feedback[] = [];
  private feedbacksSubscription: Subscription | undefined;
  newFeedback: Feedback = {
    id: '',
    eventId: '',
    userId: '',
    location: 0,
    accuracy: 0,
    performance: 0,
    rating: 0,
    description: ''
  };

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.getAllFeedback();
  }

  getAllFeedback() {
    this.feedbacksSubscription = this.http.get<Feedback[]>('/feedbacks').subscribe({
      next: (response: Feedback[]) => {
        this.feedbacks = response;
      },
      error: (error: any) => {
        console.error('Error retrieving feedback:', error);
      }
    });
  }

  ngOnDestroy() {
    if (this.feedbacksSubscription) {
      this.feedbacksSubscription.unsubscribe();
    }
  }

  createFeedback() {
    this.http.post<any>('/feedbacks/create', this.newFeedback).subscribe(
      (response: any) => {
        console.log('Feedback created:', response);
        this.getAllFeedback();
        this.resetForm();
      },
      (error: any) => {
        console.error('Error creating feedback:', error);
      }
    );
  }

  deleteFeedback(feedbackId: string) {
    this.http.delete<any>('/feedbacks/delete/' + feedbackId).subscribe(
      (response: any) => {
        console.log('Feedback deleted:', response);
        this.getAllFeedback();
      },
      (error: any) => {
        console.error('Error deleting feedback:', error);
      }
    );
  }

  resetForm() {
    this.newFeedback = {
      id: '',
      eventId: '',
      userId: '',
      location: 0,
      accuracy: 0,
      performance: 0,
      rating: 0,
      description: ''
    };
  }
}
