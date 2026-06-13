# Backend Assignment

A Spring Boot backend project implementing a simple Todo REST API with user and admin authorization checks.

## Overview

This repository contains a Java Spring Boot application that provides CRUD-style operations for Todo items. It includes:

- REST endpoints for creating and retrieving todos
- Role-based request authorization using request headers
- Caching support via `@EnableCaching`
- Spring Data dependencies for data persistence

## Technologies

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- Spring Data Redis
- Spring Validation
- H2 database (runtime)
- MySQL connector (runtime)
- Lombok

## Running the Application

### Prerequisites

- Java 17
- Maven

### Start the app

```bash
mvn spring-boot:run
```

The application starts on port `8080` by default.

## API Endpoints

### Create a todo

- `POST /api/todos`
- Request body: `Todo` JSON
- Requires `Authorization` header list with role and userId
- Example authorization header values:
  - `["USER", "<userId>"]`
  - `["ADMIN", "<admin-key>"]`

### Get todos for current user

- `GET /api/todos/me`
- Requires `Authorization` header list with role and userId
- Returns todos for the user in header index 1

### Get todos by user id (admin only)

- `GET /api/todos/user/{userId}`
- Requires `Authorization` header list with role `ADMIN`

### Update todo status

- `PUT /api/todos/{id}/status`
- Request body: status string
- Requires `Authorization` header list with role and userId
- If the role is `USER`, the user must match the todo owner

## Notes

- The current authorization mechanism expects a list of header values, with the first element as role and the second as user identifier.
- Error handling is provided for unauthorized access and missing resource conditions.

## Project Structure

- `src/main/java/com/assignment` - main application and controller/service classes
- `src/main/resources/application.properties` - application configuration

## Build

```bash
mvn clean package
```
