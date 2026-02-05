# Transactional Order Processing System

A backend-only **Transactional Order Processing System** built using **Java, Spring Boot, JPA, and MySQL**, focused on **transactions, data consistency, and concurrency safety**.

---

## Features

- Create orders with multiple order items
- Strict order lifecycle management
- Transactional service layer
- Optimistic locking for concurrency control
- Global exception handling
- RESTful APIs
- Swagger API documentation
- Clean layered architecture

---

## Architecture


- Controller: REST endpoints
- Service: Business logic and transactions
- Repository: Data access using Spring Data JPA
- Database: MySQL

---

## Order Lifecycle


### Valid Transitions
- CREATED → CONFIRMED
- CREATED → CANCELLED

### Invalid Transitions (Blocked)
- CONFIRMED → any
- CANCELLED → any

Invalid transitions result in a business exception.

---

## Tech Stack

- Java 17
- Spring Boot 3.2.x
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Swagger (springdoc-openapi)
- Lombok

---

## Transactions & Concurrency

### Transaction Management
- Service layer methods are annotated with `@Transactional`
- Ensures ACID properties for order operations

### Concurrency Handling
- Implemented using **Optimistic Locking**
- `@Version` field on `Order` entity
- Prevents race conditions such as:
  - Confirm and cancel at the same time
  - Double confirmation

On conflict:
- Spring throws `OptimisticLockingFailureException`
- API returns **409 CONFLICT**

---

## Global Exception Handling

Centralized error handling using `@RestControllerAdvice`.

| Exception | HTTP Status |
|--------|------------|
| OrderNotFoundException | 404 NOT FOUND |
| InvalidOrderStateException | 400 BAD REQUEST |
| OptimisticLockingFailureException | 409 CONFLICT |
| Other exceptions | 500 INTERNAL SERVER ERROR |

---

## REST APIs

### Create Order

POST/orders


## Request Body:

***json***
```
{
  "items": [
    {
      "productCode": "P1001",
      "quantity": 2,
      "unitPrice": 500
    }
  ]
}
```
## Confirm Order:

PUT /orders/{id}/confirm


## Cancel order:

PUT /orders/{id}/cancel


## Swagger Documentation

After starting the application, access Swagger UI at:

- http://localhost:8080/swagger-ui/index.html

## Database Design

**Order**
-id
-status
-version (optimistic locking)
-createdAt
-updatedAt

**OrderItem**
-id
-productCode
-quantity
-unitPrice
-order_id (foreign key)

## Key Design Decisions 

-Used optimistic locking for better scalability
-Enforced business rules at service layer
-Centralized exception handling
-Avoided over-engineering (DTOs, async flows) intentionally

## What This Project Demonstrates

> Transactional backend design
>Concurrency-safe REST APIs
>Clean architecture
>Production-style exception handling
>Interview-ready backend engineering skills
