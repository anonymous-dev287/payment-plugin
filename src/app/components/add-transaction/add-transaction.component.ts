
// import { Component } from '@angular/core';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { TransactionService } from '../../services/transaction.service';
// import { TransactionRequest } from '../../models/transaction-request.model';

// @Component({
//   selector: 'app-add-transaction',
//   templateUrl: './add-transaction.component.html'
// })
// export class AddTransactionComponent {
//   form: FormGroup;
//   message = '';

//   constructor(private fb: FormBuilder, private service: TransactionService) {
//     this.form = this.fb.group({
//       userId: [1, Validators.required],
//       amount: [0, [Validators.required, Validators.min(1)]],
//       category: ['', Validators.required],
//       transactionType: ['EXPENSE', Validators.required],
//       description: ['']
//     });
//   }

//   submit() {
//     if (this.form.valid) {
//       const txn: TransactionRequest = this.form.value;
//       this.service.createTransaction(txn).subscribe(msg => this.message = msg);
//     }
//   }
// }
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TransactionService } from '../../services/transaction.service';
import { TransactionRequest } from '../../models/transaction-request.model';

@Component({
  selector: 'app-add-transaction',
  templateUrl: './add-transaction.component.html',
  styleUrls: ['./add-transaction.component.scss']
})
export class AddTransactionComponent implements OnInit {
  form!: FormGroup;
  message = '';
  categories: string[] = [];

  constructor(private fb: FormBuilder, private service: TransactionService) {}

  ngOnInit(): void {
    this.form = this.fb.group({
    userId: [1, Validators.required],
    amount: [null, [Validators.required, Validators.min(1)]],
    category: ['', Validators.required],
    transactionType: ['EXPENSE', Validators.required],
    description: [''],
    transactionDate: [new Date().toISOString().substring(0, 10), Validators.required]
    });

    // this.service.getCategories().subscribe(res => {
    //   this.categories = res.split(','); 
    // });
    this.service.getCategories().subscribe(data => {
    this.categories = data; 
  });
  }

  submit(): void {
    if (this.form.valid) {
      const txn: TransactionRequest = this.form.value;
      this.service.createTransaction(txn).subscribe(msg => {
        this.message = msg;
        this.form.reset({ userId: 1, transactionType: 'EXPENSE' });
      });
    }
  }
}
