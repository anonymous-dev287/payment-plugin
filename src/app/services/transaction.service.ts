
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TransactionRequest } from '../models/transaction-request.model';
import { Budget } from '../models/budget.model';
import { CategorySpendingDto } from '../models/category-spending.model';
import { ExpenseReport } from '../models/expense-report.model';
import { Transaction } from '../models/transaction.model';

@Injectable({ providedIn: 'root' })
export class TransactionService {
  private baseUrl = 'http://localhost:8080/api/transactions';

  constructor(private http: HttpClient) {}

  createTransaction(txn: TransactionRequest): Observable<string> {
    return this.http.post(`${this.baseUrl}/maketransaction`, txn, { responseType: 'text' });
  }

  createBudget(budget: Budget): Observable<string> {
    return this.http.post(`${this.baseUrl}/createbudget`, budget, { responseType: 'text' });
  }

  // getCategories(): Observable<string> {
  //   return this.http.get(`${this.baseUrl}/categories`, { responseType: 'text' });
  // }
  getCategories(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/categories`);
  }

  getTopCategories(userId: number): Observable<CategorySpendingDto[]> {
    const params = new HttpParams().set('userId', userId.toString());
    return this.http.get<CategorySpendingDto[]>(`${this.baseUrl}/top-categories`, { params });
  }
  getExpenseReport(userId: number): Observable<ExpenseReport[]> {
  return this.http.get<ExpenseReport[]>(`${this.baseUrl}/expense-report`, {
    params: new HttpParams().set('userId', userId.toString()) });
}

getTransactions(userId: number): Observable<Transaction[]> {
  return this.http.get<Transaction[]>(`${this.baseUrl}/history`, {
    params: new HttpParams().set('userId', userId.toString())
  });
}


}
