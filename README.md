# Employee Management System (Intermediate)

A Spring Boot application for managing **Employees, Departments, and Roles**, demonstrating entity relationships, DTOs, service layers, validation, exception handling, and JWT-based authentication.

---

## Features

- **User Registration & Login** with JWT-based authentication
- **CRUD APIs** for Employee, Department, and Role
- **Entity relationships**:
  - Employee → Department (ManyToOne)
  - Employee → Role (ManyToMany)
- **DTOs** and **ModelMapper** for clean API responses
- **Service Layer** for business logic separation
- **Validation** and **exception handling** for robust API
- **Optional search/filter APIs** for employees by department or role

---

## Technology Stack

- Java 17+
- Spring Boot 3+
- Spring Data JPA / Hibernate
- MySQL / H2 Database
- Spring Security (JWT)
- ModelMapper
- Maven / Gradle

---

## Setup

1. Clone the repository:

```bash
git clone <repo_url>


Configure your application.yml with database and JWT properties:

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/employeedb
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

app:
  secret: your_jwt_secret_key
  expiration: 3600000


Build and run:

mvn spring-boot:run

Authentication & User Flow

Register a new user
Endpoint: POST /api/user/register
Request Body Example:

{
  "username": "admin",
  "password": "admin123"
}


Creates a new user in the system.

Login to get JWT token
Endpoint: POST /api/user/login
Request Body Example:

{
  "username": "admin",
  "password": "admin123"
}


Returns a JWT token to be used for authorization.

Use JWT token for protected endpoints

Include token in the Authorization header:

Authorization: Bearer <token>


All Employee, Department, and Role APIs are protected by JWT.

API Endpoints
Employee APIs
Endpoint	Method	Description	Request Body Example
/api/employee	GET	Get all employees	-
/api/employee/{id}	GET	Get specific employee by ID	-
/api/employee	POST	Add new employee	json { "name": "Dheeraj Mehra", "email": "dm201558@gmail.com", "department": { "name": "Engineering", "location": "Second Floor" }, "roles": [ { "roleName": "Teacher" }, { "roleName": "Staff" } ] }
/api/employee/{id}	DELETE	Delete employee by ID	-
Department APIs
Endpoint	Method	Description	Request Body Example
/api/department	GET	Get all departments	-
/api/department/{id}	GET	Get department by ID	-
/api/department	POST	Add new department	json { "name": "Engineering", "location": "Second Floor" }
/api/department/{id}	DELETE	Delete department	-
Role APIs
Endpoint	Method	Description	Request Body Example
/api/role	GET	Get all roles	-
/api/role/{id}	GET	Get role by ID	-
/api/role	POST	Add new role	json { "roleName": "Teacher" }
/api/role/{id}	DELETE	Delete role	-
Extra Features / Challenges

Add search/filter endpoints:

/api/employee/search?department=Engineering

/api/employee/search?role=Teacher


Notes

DTOs are used to avoid exposing entities directly.

Employee creation automatically handles nested Department and Roles creation if they don’t exist.

JWT authentication secures the APIs. Unauthorized requests return 401 Unauthorized.

Authors

Dheeraj Mehra – Project Lead / Developer
