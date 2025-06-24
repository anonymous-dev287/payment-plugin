
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionService } from '../../services/transaction.service';
import { Budget } from '../../models/budget.model';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.scss']
})
export class BudgetComponent implements OnInit {
  form!: FormGroup;
  categories: string[] = [];
  message = '';

  constructor(private fb: FormBuilder, private service: TransactionService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      userId: [1, Validators.required],
      amount: [null, Validators.required],
      category: ['', Validators.required]
    });

    this.service.getCategories().subscribe(data => {
  this.categories = data; // ✅ already an array now
});
  }

  submit(): void {
    const budget: Budget = this.form.value;
    this.service.createBudget(budget).subscribe(msg => {
      this.message = msg;
      this.form.reset({ userId: 1 });
    });
  }
}
