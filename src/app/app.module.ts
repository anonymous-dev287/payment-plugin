import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AddTransactionComponent } from './components/add-transaction/add-transaction.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { BudgetComponent } from './components/budget/budget.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,         // ✅ Include this
    AddTransactionComponent,
    DashboardComponent,      // ✅ Include this
    BudgetComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule         // ✅ RouterModule included through here
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
