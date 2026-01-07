# Spring Boot RBAC API

A RESTful API built with Spring Boot 3 featuring role-based access control (RBAC), user authentication, and PostgreSQL integration. Demonstrates clean architecture with Controller-Service-Repository pattern.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-blue)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🌐 Live Demo

**[▶️ View Live API](https://supply-chain-api-3y87.onrender.com)**

> ⚠️ **Note:** Hosted on Render's free tier - first request may take 30-60 seconds to wake up.

### Test Endpoints

```bash
# Health Check
curl https://supply-chain-api-3y87.onrender.com/actuator/health

# Login (returns user data with role)
curl -X POST https://supply-chain-api-3y87.onrender.com/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

---

## ✨ Features

- **5-Tier Role System** - Administrator, Manager, Warehouse Clerk, Auditor, Viewer
- **Permission-Based Access** - Granular permissions per role
- **RESTful Design** - Clean API endpoints following REST conventions
- **PostgreSQL Integration** - JPA/Hibernate with proper entity relationships
- **Clean Architecture** - Controller → Service → Repository pattern
- **CORS Configured** - Ready for frontend integration

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                      Client                             │
└─────────────────────┬───────────────────────────────────┘
                      │ HTTP/REST
                      ▼
┌─────────────────────────────────────────────────────────┐
│                   Controller Layer                      │
│         AuthController  │  UserController               │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                    Service Layer                        │
│          AuthService    │   UserService                 │
└─────────────────────┬───────────────────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────────────────┐
│                  Repository Layer                       │
│                   UserRepository                        │
└─────────────────────┬───────────────────────────────────┘
                      │ JPA/Hibernate
                      ▼
┌─────────────────────────────────────────────────────────┐
│                    PostgreSQL                           │
└─────────────────────────────────────────────────────────┘
```

---

## 🔐 Role-Based Access Control

### Roles & Hierarchy

| Role | Level | Description |
|------|-------|-------------|
| `ADMINISTRATOR` | 5 | Full system access, user management |
| `MANAGER` | 4 | Approve operations, manage resources |
| `WAREHOUSE_CLERK` | 3 | Create and view resources |
| `AUDITOR` | 2 | Read-only access to audit logs |
| `VIEWER` | 1 | Read-only access to basic data |

### Permission Matrix

| Permission | Admin | Manager | Clerk | Auditor | Viewer |
|------------|:-----:|:-------:|:-----:|:-------:|:------:|
| `users.create` | ✅ | ❌ | ❌ | ❌ | ❌ |
| `users.read` | ✅ | ✅ | ❌ | ✅ | ❌ |
| `users.update` | ✅ | ❌ | ❌ | ❌ | ❌ |
| `users.delete` | ✅ | ❌ | ❌ | ❌ | ❌ |
| `transfers.create` | ✅ | ✅ | ✅ | ❌ | ❌ |
| `transfers.read` | ✅ | ✅ | ✅ | ✅ | ❌ |
| `transfers.approve` | ✅ | ✅ | ❌ | ❌ | ❌ |
| `inventory.read` | ✅ | ✅ | ✅ | ✅ | ✅ |
| `reports.view` | ✅ | ✅ | ✅ | ✅ | ❌ |
| `audit.view` | ✅ | ✅ | ❌ | ✅ | ❌ |

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| `POST` | `/api/auth/login` | Authenticate user | Public |
| `POST` | `/api/auth/register` | Register new user | Public |
| `POST` | `/api/auth/logout` | Logout user | Authenticated |

### Users

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| `GET` | `/api/users` | List all users | Admin |
| `GET` | `/api/users/{id}` | Get user by ID | Admin |
| `POST` | `/api/users` | Create new user | Admin |
| `PUT` | `/api/users/{id}` | Update user | Admin |
| `DELETE` | `/api/users/{id}` | Delete user | Admin |
| `PUT` | `/api/users/{id}/role` | Change user role | Admin |

### Health

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| `GET` | `/actuator/health` | Health check | Public |

---

## 🚀 Quick Start

### Prerequisites

- Java 21+
- Maven 3.8+
- PostgreSQL 14+

### 1. Clone the repository

```bash
git clone https://github.com/Zag009/spring-boot-rbac-api.git
cd spring-boot-rbac-api
```

### 2. Create PostgreSQL database

```bash
psql -U postgres -c "CREATE DATABASE rbac_db;"
```

### 3. Configure environment variables

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/rbac_db
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=your_password
```

Or update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/rbac_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 4. Run the application

```bash
mvn spring-boot:run
```

### 5. Test the API

```bash
# Health check
curl http://localhost:8080/actuator/health

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

---

## 📁 Project Structure

```
src/main/java/com/rbac/api/
├── RbacApplication.java          # Main application entry
├── config/
│   └── CorsConfig.java           # CORS configuration
├── controller/
│   ├── AuthController.java       # Authentication endpoints
│   └── UserController.java       # User management endpoints
├── dto/
│   ├── LoginRequest.java         # Login request payload
│   ├── LoginResponse.java        # Login response payload
│   └── UserDto.java              # User data transfer object
├── entity/
│   ├── User.java                 # User entity
│   └── Role.java                 # Role enum
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/
│   └── UserRepository.java       # User data access
└── service/
    ├── AuthService.java          # Authentication logic
    └── UserService.java          # User business logic
```

---

## 🧪 Demo Accounts

| Username | Password | Role |
|----------|----------|------|
| `admin` | `admin123` | Administrator |
| `manager` | `manager123` | Manager |
| `clerk` | `clerk123` | Warehouse Clerk |
| `auditor` | `auditor123` | Auditor |
| `viewer` | `viewer123` | Viewer |

---

## 📝 Example Requests & Responses

### Login

**Request:**
```bash
POST /api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

**Response:**
```json
{
  "id": "usr_abc123",
  "username": "admin",
  "name": "System Admin",
  "email": "admin@company.com",
  "role": "ADMINISTRATOR",
  "permissions": [
    "users.create",
    "users.read",
    "users.update",
    "users.delete",
    "transfers.create",
    "transfers.read",
    "transfers.approve",
    "inventory.read",
    "reports.view",
    "audit.view"
  ]
}
```

### Get All Users (Admin only)

**Request:**
```bash
GET /api/users
Authorization: Bearer <token>
```

**Response:**
```json
[
  {
    "id": "usr_abc123",
    "username": "admin",
    "name": "System Admin",
    "email": "admin@company.com",
    "role": "ADMINISTRATOR",
    "createdAt": "2025-01-07T10:30:00Z"
  },
  {
    "id": "usr_def456",
    "username": "manager",
    "name": "Operations Manager",
    "email": "manager@company.com",
    "role": "MANAGER",
    "createdAt": "2025-01-07T11:00:00Z"
  }
]
```

---

## 🛠️ Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming language |
| Spring Boot 3.3 | Application framework |
| Spring Data JPA | Data persistence |
| Hibernate | ORM |
| PostgreSQL | Database |
| Maven | Build tool |
| Lombok | Boilerplate reduction |

---

## 🔄 Related Projects

| Repository | Description |
|------------|-------------|
| [inventory-blockchain](https://github.com/Zag009/inventory-blockchain) | Full-stack supply chain platform using this API |

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Zag009**

Portfolio project demonstrating:
- RESTful API design
- Role-based access control implementation
- Clean architecture patterns
- Spring Boot best practices

---

⭐ **Star this repo if you find it useful!**
