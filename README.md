# Residential Expense Control

Web application designed to users manage residential expenses. It allows users to register, list and delete users, and also create and list transactions. The system is built using **Java 21**, **Spring Boot 3.4.3**, **Maven** for dependency management and an **H2 in-memory database**.

---

## Features
- **User Management**:
    - Create a new user.
    - List all users.
    - Delete a user by ID and his transactions.
    - Summary of all users with their expenses, income, net balance and total expense, total income and total net balance.

- **Transaction Management**:
    - Create a new transaction.
    - List all transactions.
    
- **Database**:
    - Uses H2 in-memory database for easy setup.
    - Database console accessible at `/h2-console`.

---

## API Documentation

All endpoints are accessible under the base URL: `http://localhost:8080`.

### User Endpoints

- **Create a User**:
  - **POST** `/v1/user`
  - Request Body:
    ```
    {
        "name": "John Smith",
        "age": 23
    }
    ```
  - Response:
    ```
    {
        "id": 1,
        "name": "John Smith",
        "age": 23,
        "role": "ADULT"
    }
    ```

- **List all Users**:
  - **GET** `/v1/user`
  - Response:
    ```
    {
        "content": [
            {
                "id": 1,
                "name": "John Smith",
                "age": 23,
                "role": "ADULT"
            }
        ],
        "total": 1,
        "page": 0,
        "size": 10
    }
    ```

- **Get User by ID**:
  - **GET** `/v1/user/{id}`
  - Response:
    ```
    {
        "id": 1,
        "name": "John Smith",
        "age": 23,
        "role": "ADULT"
    }
    ```

- **Delete User**:
  - **DELETE** `/v1/user/{id}`
  - Response: `204 No Content`

- **Get User Summary**:
  - Response:
    ```
    {
        "userSummaries": [
            {
            "userId": 1,
            "userName": "John Smith",
            "userAge": 23,
            "totalUserRevenue": 5000,
            "totalUserExpense": 0,
            "userNetBalance": 5000
            },
            {
            "userId": 2,
            "userName": "Maria Smith",
            "userAge": 21,
            "totalUserRevenue": 0,
            "totalUserExpense": 2000,
            "userNetBalance": -2000
            }
        ],
        "totalRevenue": 5000,
        "totalExpense": 2000,
        "totalNetBalance": 3000
    }
    ```

### Transaction Endpoints

- **Create Transaction**:
  - **POST** `/v1/transaction`
  - Request Body:
    ```
    {
        "description": "Salary",
        "amount": 5000,
        "type": "REVENUE",
        "userId": 1
    }
    ```
    - Response:
    ```
    {
        "id": 1,
        "description": "Salary",
        "amount": 5000,
        "type": "REVENUE",
        "user": {
            "id": 1,
            "name": "John Smith",
            "age": 23,
            "role": "ADULT"
        }
    }
    ```

- **List all Transactions**
  - **GET** `/v1/transaction`
  - Response:
    ```
    {
        "content": [
            {
                "id": 1,
                "description": "Salary",
                "amount": 5000,
                "type": "REVENUE",
                "user": {
                    "id": 1,
                    "name": "John Smith",
                    "age": 23,
                    "role": "ADULT"
                }
            },
            {
                "id": 2,
                "description": "Purchase",
                "amount": 2000,
                "type": "EXPENSE",
                "user": {
                    "id": 2,
                    "name": "Maria Smith",
                    "age": 21,
                    "role": "ADULT"
                }
            }
        ],
        "total": 2,
        "page": 0,
        "size": 10
    }
    ```

- **Get Transaction by ID**
  - **GET** `/v1/transaction/{id}`
  - Response:
    ```
    {
        "id": 1,
        "description": "Salary",
        "amount": 5000,
        "type": "REVENUE",
        "user": {
            "id": 1,
            "name": "John Smith",
            "age": 23,
            "role": "ADULT"
        }
    }
    ```

---

## Database Configuration

- **H2 in-memory dataase.**
- Database console is acessible at `http:localhost:8080/h2-console`.
  - **JDBC URL**: `jdbc:h2:mem:expensecontrol`
  - **User Name**: `sa`
  - **Password**: `sa`