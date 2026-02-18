# Expense Manager REST API

A Spring Boot REST API application for managing personal expenses.

## Features

- Create, Read, Update, and Delete expenses
- Filter expenses by category
- Filter expenses by date range
- Get total expenses (overall, by category, or by date range)
- Input validation
- Exception handling
- H2 in-memory database
- RESTful API design

## Technologies Used

- Spring Boot 3.2.1
- Spring Data JPA
- Spring Web
- H2 Database
- Lombok
- Bean Validation
- Maven

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd expense-manager
```

### 2. Build the application

```bash
mvn clean install
```

### 3. Run the application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/expense-manager-1.0.0.jar
```

The application will start on `http://localhost:8080`

### 4. Access H2 Console

The H2 database console is available at: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:expensedb`
- Username: `sa`
- Password: (leave blank)

## API Endpoints

### Base URL
```
http://localhost:8080/api/expenses
```

### Endpoints

#### 1. Get All Expenses
```http
GET /api/expenses
```

#### 2. Get Expense by ID
```http
GET /api/expenses/{id}
```

#### 3. Create New Expense
```http
POST /api/expenses
Content-Type: application/json

{
  "description": "Grocery Shopping",
  "amount": 150.50,
  "category": "Food",
  "date": "2026-02-18"
}
```

#### 4. Update Expense
```http
PUT /api/expenses/{id}
Content-Type: application/json

{
  "description": "Updated Grocery Shopping",
  "amount": 200.00,
  "category": "Food",
  "date": "2026-02-18"
}
```

#### 5. Delete Expense
```http
DELETE /api/expenses/{id}
```

#### 6. Get Expenses by Category
```http
GET /api/expenses/category/{category}
```
Example: `/api/expenses/category/Food`

#### 7. Get Expenses by Date Range
```http
GET /api/expenses/date-range?startDate=2026-01-01&endDate=2026-12-31
```

#### 8. Get Expenses by Category and Date Range
```http
GET /api/expenses/category/{category}/date-range?startDate=2026-01-01&endDate=2026-12-31
```

#### 9. Get Total Expenses
```http
GET /api/expenses/total
```

#### 10. Get Total Expenses by Category
```http
GET /api/expenses/total/category/{category}
```

#### 11. Get Total Expenses by Date Range
```http
GET /api/expenses/total/date-range?startDate=2026-01-01&endDate=2026-12-31
```

## Sample cURL Commands

### Create an Expense
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Grocery Shopping",
    "amount": 150.50,
    "category": "Food",
    "date": "2026-02-18"
  }'
```

### Get All Expenses
```bash
curl http://localhost:8080/api/expenses
```

### Get Expense by ID
```bash
curl http://localhost:8080/api/expenses/1
```

### Update an Expense
```bash
curl -X PUT http://localhost:8080/api/expenses/1 \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Updated Shopping",
    "amount": 200.00,
    "category": "Food",
    "date": "2026-02-18"
  }'
```

### Delete an Expense
```bash
curl -X DELETE http://localhost:8080/api/expenses/1
```

### Get Expenses by Category
```bash
curl http://localhost:8080/api/expenses/category/Food
```

### Get Total Expenses
```bash
curl http://localhost:8080/api/expenses/total
```

## Expense Model

```json
{
  "id": 1,
  "description": "Grocery Shopping",
  "amount": 150.50,
  "category": "Food",
  "date": "2026-02-18",
  "createdAt": "2026-02-18",
  "updatedAt": "2026-02-18"
}
```

### Field Validations

- `description`: Required, cannot be blank
- `amount`: Required, must be positive
- `category`: Required, cannot be blank
- `date`: Required

## Error Handling

The API returns appropriate HTTP status codes and error messages:

- `200 OK`: Successful GET/PUT/DELETE requests
- `201 Created`: Successful POST requests
- `400 Bad Request`: Validation errors
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server errors

Example error response:
```json
{
  "timestamp": "2026-02-18T03:22:58",
  "status": 404,
  "error": "Not Found",
  "message": "Expense not found with id: 1",
  "path": "/api/expenses/1"
}
```

## Project Structure

```
expense-manager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/expense/manager/
│   │   │       ├── ExpenseManagerApplication.java
│   │   │       ├── controller/
│   │   │       │   └── ExpenseController.java
│   │   │       ├── model/
│   │   │       │   └── Expense.java
│   │   │       ├── repository/
│   │   │       │   └── ExpenseRepository.java
│   │   │       ├── service/
│   │   │       │   └── ExpenseService.java
│   │   │       └── exception/
│   │   │           ├── ResourceNotFoundException.java
│   │   │           ├── ErrorResponse.java
│   │   │           └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/expense/manager/
└── pom.xml
```

## Common Expense Categories

- Food & Dining
- Transportation
- Shopping
- Entertainment
- Bills & Utilities
- Healthcare
- Travel
- Education
- Personal Care
- Others

## License

This project is open source and available under the MIT License.

## Author

Expense Manager System - 2026
