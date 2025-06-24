export interface TransactionRequest {
  userId: number;
  amount: number;
  category: string;
  transactionType: 'INCOME' | 'EXPENSE';
  description: string;
  transactionDate: string;
}
