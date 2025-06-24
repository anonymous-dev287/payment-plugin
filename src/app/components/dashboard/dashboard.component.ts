// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-dashboard',
//   // standalone: true,
//   // imports: [],
//   templateUrl: './dashboard.component.html',
//   styleUrl: './dashboard.component.css'
// })
// export class DashboardComponent {

// }
import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';
import { CategorySpendingDto } from '../../models/category-spending.model';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  topCategories: CategorySpendingDto[] = [];

  constructor(private service: TransactionService) {}

  ngOnInit(): void {
    this.service.getTopCategories(1).subscribe(data => {
      this.topCategories = data;
    });
  }
}
