# Expense-Tracker
## Overview
    This api project connects to a database and allows user to track his expenses and income while controlling the budget and sending alarms about when the budget is exceeded.
## Features
* **Authentication and Authorization:**
Securely create an account and login using jwt authentication
* **Expenses Entry:** Add, update, delete and categorize expenses expenses.
* **Income Entry:** Logging incomes from various sources.
* **Budget Management:** Setting budget to track expenses.
* **Email Notification:** Notify the user if the budget is exceeded through email.
* **Expenses Tracking:** View your expenses and sort them according to date or amount.
* **Incomes Tracking:** View your incomes and sort them according to date or amount.
## Structure
```
├── expenseTracker
│   ├── auth                                // Authentication
│   │   ├── AuthenticationController.java   // End points for authentication and registration
│   │   ├── AuthenticationRequest.java      // Request body for authentication request
│   │   ├── AuthenticationResponse.java     // Request body for authentication request
│   │   ├── AuthenticationService.java      // Service to authenticate and register
│   │   └── RegistrationRequest.java        // Request body for registeration request
│   ├── config                              // Configuration files
│   │   ├── ApplicationConfig.java
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── JwtService.java                 // Serviec to handle jwt operations
│   │   └── SecurityConfig.java
│   │── exceptionHandling
│   │   ├── errorResponse                   // The response body for the error
│   │   ├── exception                       // The declaration of the exceptions
│   │   └── exceptionHandler                // The excepion handlers
│   │── user
│   │   ├── controller                      // end point to get detail for current user
│   │   ├── model          
│   │   ├── repository                      // Repository for interacting with the user table in the database
│   │   └── service                         // Services for handling user operation
│   │── expense
│   │   ├── controller                      // End points for crud operations for the expense
│   │   ├── model          
│   │   ├── repository                      // Repository for interacting with the expense table in the database
│   │   └── service                         // Services for handling expense operation
│   │── budget 
│   │   ├── controller                      // End points for crud operations for the budget
│   │   ├── model          
│   │   ├── repository                      // Repository for interacting with the budget table in the database
│   │   └── service                         // Services for handling budget operations
│   │── util.email
│   │   ├── EmailConfig.java                
│   │   └── EmailService.java               // Services for sending an email
│   └── ...
└── ExpenseTrackerApplication.java
```
## Installation and Run
```bash
git clone https://github.com/sayedhassan-a/spring-boot-Expense-Tracker.git
docker-compose up
```

## API
### Auth API
**/api/register**
- `POST`: Create a new user and return auth token.

**Request Body**
```json
{
  "username": "user",
  "email": "user@example.com",
  "firstname": "Ali",
  "lastname": "Ahmed",
  "password": "password"
}
```
**Response Body**
```json
{
  "token": "exampletoken"
}
```
___
**/api/authenticate**
- `POST`: Login for an existing user

**Request Body**
```json
{
  "username": "user",
  "password": "password"
}
```
**Response Body**
```json
{
  "token": "exampletoken"
}
```
___
### Expense API
**/api/expense**
- `GET`: Get list of expenses.
- `POST`: Create a new expense.
- `PUT`: Update an existing expense

**/api/expense/:id**
- `GET`: Get an expense by id.
- `DELETE`: Delete an expense by id.
___
### Income API
**/api/income**
- `GET`: Get list of income.
- `POST`: Create a new income.
- `PUT`: Update an existing income

**/api/income/:id**
- `GET`: Get an income by id.
- `DELETE`: Delete an income by id.
___
### Category API
**/api/category**
- `GET`: Get list of category.
- `POST`: Create a new category.
- `PUT`: Update an existing category

**/api/category/:id**
- `GET`: Get an category by id.
- `DELETE`: Delete an category by id.
___
### Budget API
**/api/budget**
- `GET`: Get the budget of the current user.
- `POST`: Create a budget for the current user.
- `Delete`: Delete the budget for the current user.
