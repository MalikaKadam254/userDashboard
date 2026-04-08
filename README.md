# User Dashboard Application

A full-stack User dashboard application built with Spring Boot and React that allows users to view, search, filter, and sort user data efficiently. It also includes a tag summary API that aggregates and analyzes user tags with performance tracking.

This project is built using:
- **Backend:** Spring Boot (Java)
- **Frontend:** React + TypeScript
- **API Communication:** Axios

It allows users to:
- View a list of users
- Filter by status (Active / Inactive)
- Sort by different fields
- Search users by name
- Generate tag summaries via API

---
## Features

### Backend
- REST API built with Spring Boot
- Filter users by status (`active` / `inactive`)
- Sort users by:
  - `id`
  - `name`
  - `email`
  - `joinedAt`
- Tag summary API:
  - Counts tag frequency
  - Returns sorted summary
  - Measures processing time

### Frontend
- User table display
- Clickable column sorting
- Search by name
- Filter by status
- Refresh data
- Custom React hooks for clean state management

---

## Project Structure
```
dashboard(backend)/
├── Dashboard.java
├── controller/
│   └── UserController.java
├── dto/
│   └── TagSummaryRequest.java
├── entity/
│   └── User.java
└── test/
    └── controller/
        └── UserControllerTest.java

userboard(frontend)/
├── components/
│   └── UserTable.tsx
├── hooks/
│   ├── useUsers.ts
│   └── useUserFilter.ts
├── api.ts
├── App.tsx
└── types/User.ts
```

---

## How to Run the Project

### Backend (Spring Boot)

#### Prerequisites
- Java 17+
- Maven or Gradle

#### Steps
```bash
cd dashboard
mvn spring-boot:run
```
Server will start at:
```
http://localhost:8080/users
```

---

### Frontend (React + TypeScript)

#### Prerequisites
- Node.js (v16+ recommended)
- npm or yarn

#### Steps
```bash
cd userboard
npm install
npm install axios
npm run dev
```
App will run at:
```
http://localhost:5173
```

---
## API Endpoints

### 1. Get Users
```
GET /users
```
#### Query Parameters:
| Param  | Type   | Description                 |
|--------|--------|-----------------------------|
| status | string | active / inactive           |
| sortBy | string | id, name, email, joinedAt  |
| order  | string | asc / desc                 |

#### Example:
```
/users?status=active&sortBy=name&order=asc
```

---

### 2. Tag Summary
```
POST /users/tagSummary
```
#### Request Body:
```json
{
  "users": [
    { "id": "1", "name": "Alice", "tags": ["admin", "billing", "support"] },
    { "id": "2", "name": "Bob", "tags": ["billing", "admin"] },
    { "id": "3", "name": "Carol", "tags": ["admin", "ops", "support"] }
  ]
}
```

#### Response:
```json
{
  "summary": [
    { "tag": "admin", "count": 3 },
    { "tag": "billing", "count": 2 },
    { "tag": "support", "count": 2 },
    { "tag": "ops", "count": 1 }
  ],
  "processedCount": 3,
  "durationMs": "4 ms"
}
```

---
## Assumptions Made
- User data is hardcoded in memory (no database used)
- Status values are strictly:
  - active
  - inactive
- Frontend assumes backend is running on:
  - http://localhost:8080/users
- No authentication/authorization is implemented
- Tag summary input follows expected JSON structure

---
## Limitations
- No pagination for large datasets
- No validation library (basic runtime checks only)
- Minimal error handling

---
## Improvements (If Given More Time)

### Backend Improvements
- Add database integration (MySQL)
- Implement pagination & caching
- Use DTOs and validation (@Valid)
- Replace RuntimeException with proper exception handling
- Write integration tests

### Frontend Improvements
- Add better UI/UX (Material UI / Tailwind)
- Debounced search for performance
- Add pagination & loading skeletons
- Improve error handling with user feedback
- Write component and hook tests

### Full Stack Enhancements
- Add authentication (JWT / OAuth)

##  Author
Malika Kadam
