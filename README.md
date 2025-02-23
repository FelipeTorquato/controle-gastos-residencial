# Residential Expense Control

Web application designed to users manage residential expenses. It allows users to register, list and delete users, and also create and list transactions. The system's back-end is built using **Java 21**, **Spring Boot 3.4.3**, **Maven** for dependency management and an **H2 in-memory database**. The front-end is developed using **React** with **TypeScript**. It interacts with the backend API using **Axios** to allow users to manage their expenses and transactions. The UI is built with **Bootstrap** for styling.

---

## Features

### Back-End Features

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

### Front-End Features

- **User Interface**:
  - **Navbar**: Navigation between Home, Users, Transactions and Summary page.
  - **Home**: Displays two tables —Users and Transactions— each occupying half of the screen.
  - **User Page**: Allows creating users.
  - **Transaction Page**: Allows creating transactions.
  - **Summary Page**: Displays a summary of users' financial data (revenue, expenses, and net balance).
- **Design**:
  - Built with **Bootstrap** for a clean layout.
- **API Integration**:
  - Uses **Axios** to interact with the back-end API.

---

## Project Structure

```bash
controle_gastos-residencial/
├── back-end/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── controle/
│   │   │   │           └── controle_gastos/
│   │   │   │               ├── application/
│   │   │   │               │   ├── controller/
│   │   │   │               │   ├── filter/
│   │   │   │               │   └── swagger/
│   │   │   │               ├── domain/
│   │   │   │               │   ├── entity/
│   │   │   │               │   ├── exceptions/
│   │   │   │               │   ├── repository/
│   │   │   │               │   ├── service/
│   │   │   │               │   └── to/
│   │   │   │               ├── infra/
│   │   │   │               │   ├── init/
│   │   │   │               │   └── repository/
│   │   │   │               └── ControleDeGastosResidencialApplication.java
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       └── application.properties
│   ├── pom.xml
│   └── README.md
├── front-end/
│   ├── public/
│   ├── src/
│   │   ├── assets/
│   │   ├── components/
│   │   │   ├── Navbar.tsx
│   │   ├── pages/
│   │   │   ├── Home.tsx
│   │   │   ├── Home.css
│   │   │   ├── User.tsx
│   │   │   ├── User.css
│   │   │   ├── Transaction.tsx
│   │   │   ├── Transaction.css
│   │   │   ├── Summary.tsx
│   │   │   └── Summary.css
│   │   ├── service/
│   │   │   ├── userService.ts
│   │   │   └── transactionService.ts
│   │   ├── types.ts
│   │   ├── App.tsx
│   │   ├── App.css
│   │   ├── main.tsx
│   │   └── vite-env.d.ts
│   ├── index.html
│   ├── package.json
│   ├── tsconfig.json
│   └── vite.config.ts
└── README.md
```

---
## API Documentation

All endpoints are accessible under the base URL: `http://localhost:8080`.

### User Endpoints

- **Create a User**:
  - **POST** `/v1/user`
  - Request Body:
    ```json
    {
        "name": "John Smith",
        "age": 23
    }
    ```
  - Response:
    ```json
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
    ```json
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
    ```json
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
    ```json
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
    ```json
    {
        "description": "Salary",
        "amount": 5000,
        "type": "REVENUE",
        "userId": 1
    }
    ```
    - Response:
    ```json
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
    ```json
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
    ```json
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

## Running Back-End

The back-end application is designed to run directly from IDE and uses **Java 21**.

1. Import the `back-end` folder into the IDE as Maven Project.
2. Wait for the IDE to download all dependencies from `pom.xml` file.
3. Locate the main class: `ControleDeGastosResidencialApplication.java` in the `src/main/java/com/controle/controle_gastos/ControleDeGastosResidencialApplication.java` directory.
4. Run main class.
5. The back-end will start and run on `http://localhost:8080`.
6. Swagger can be used to test the API at `http://localhost:8080/swagger-ui/index.html`.
7. Ensure the back-end is running before starting the front-end.

---

## Running Front-End

The front-end is built using **React** and **TypeScript** and can be run independently.

1. Enter front-end folder:
   ```bash
   git clone https://github.com/FelipeTorquato/controle-gastos-residencial
   cd front-end
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start development server:
   ```bash
   npm run dev
   ```
4. Access application at `http://localhost:5173/`.

---

## Database Configuration

- **H2 in-memory dataase.**
- Database console is acessible at `http:localhost:8080/h2-console`.
  - **JDBC URL**: `jdbc:h2:mem:expensecontrol`
  - **User Name**: `sa`
  - **Password**: `sa`