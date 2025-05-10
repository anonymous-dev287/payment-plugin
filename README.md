# Smart Expense Tracker Plugin for Payment Apps

An intelligent budget-tracking plugin designed to integrate with UPI-based payment platforms like Google Pay or PhonePe. It categorizes spending automatically based on QR merchant detection, alerts users on budget thresholds, and generates insightful monthly reports.

---

## 💡 Key Features

- 🔍 **QR-Based Expense Categorization**
  - Automatically maps merchant names from QR scans to predefined categories: `Food`, `Entertainment`, `Shopping`, `Beverages`, etc.

- 🎯 **Budget Allocation & Monitoring**
  - Users can set monthly limits per category.
  - Tracks live spending against each category.

- 🚨 **Smart Alerts**
  - Notifies users when 80% of any category budget is reached.

- 📊 **Insight Reports**
  - End-of-cycle report generation with category-wise expenses and limits.

- ♻️ **Auto-Renewal & Customization**
  - Automatically renews expired budgets.
  - Allows users to modify categories (add/remove) dynamically.

---

## 🧱 Tech Stack

| Component         | Technology           |
|------------------|----------------------|
| Language          | Java 17              |
| Framework         | Spring Boot          |
| Build Tool        | Gradle               |
| Database          | (Pluggable) - H2, PostgreSQL, or MongoDB |
| Design Patterns   | Factory, Strategy    |
| Principles        | SOLID, Clean Code    |
| Containerization  | Docker (optional)    |

---

## 🛠️ APIs Overview

### 🔁 Transaction API
`POST /transactions/handle`

- Automatically categorizes the QR merchant
- Deducts from associated budget
- Alerts on threshold breach

### ➕ Create Budget
`POST /budgets/create`

- Accepts user-defined `limit`, `category`, and `expiry date`

### 🔄 Renew & Customize Categories
`POST /budgets/renew`

- Renews expired budgets for a user
- Allows category add/remove in the same call

### 📤 Generate Report + Reset
`POST /budgets/clear-and-report`

- Resets spent data for all categories
- Returns detailed spending report for the month

---

## 🧪 Testing

- 85%+ code coverage using JUnit 5 and Mockito.
- Modular service layer for unit and integration testing.

---

## 📦 Future Enhancements

- 📩 Send report via SMS/email using Twilio or SendGrid.
- 📱 Android/iOS SDK for easy plugin integration.
- 📈 Charts and data visualization dashboard.

---

## 🚀 How to Run

```bash
# Clone the repo
git clone https://github.com/your-username/smart-expense-plugin.git
cd smart-expense-plugin

# Build
./gradlew build

# Run
./gradlew bootRun
