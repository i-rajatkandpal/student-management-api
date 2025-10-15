# Student Management API

A RESTful API for managing student records built with **Spring Boot**, **PostgreSQL**, and **Docker**. This project demonstrates professional backend development practices including DTOs, validation, exception handling, and clean architecture.

---

## 🚀 Features

- ✅ **Full CRUD Operations** - Create, Read, Update, Delete students
- ✅ **Input Validation** - Comprehensive validation with custom error messages
- ✅ **Exception Handling** - Custom exceptions with proper HTTP status codes
- ✅ **DTO Pattern** - Separation of API and database models
- ✅ **Docker Support** - Easy setup with Docker Compose
- ✅ **Search Functionality** - Filter students by course
- ✅ **Unique Email Constraint** - Prevents duplicate email addresses
- ✅ **PostgreSQL Database** - Production-ready relational database

---

## 🛠️ Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.6 |
| PostgreSQL | Latest |
| Docker | Latest |
| Maven | 3.9.11 |

**Dependencies:**
- Spring Web
- Spring Data JPA
- Spring Validation
- PostgreSQL Driver

---

## 📋 Prerequisites

Before running this project, ensure you have:

- **Java 21** installed
- **Docker Desktop** installed and running
- **Maven** (included via Maven Wrapper)
- **Postman** or any API testing tool (optional)

---

## 🏃 Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/i-rajatkandpal/student-management-api.git
cd student-management-api
```

### 2. Start PostgreSQL with Docker
```bash
docker-compose up -d
```

This will start PostgreSQL on port `1234` with:
- Database: `schooldb`
- Username: `teacher`
- Password: `password`

### 3. Run the Application
```bash
cd student-management
./mvnw spring-boot:run
```

**Windows:**
```bash
mvnw.cmd spring-boot:run
```

The API will start on `http://localhost:8080`

---

## 📚 API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/students` | Get all students |
| `GET` | `/students/{id}` | Get student by ID |
| `POST` | `/students` | Create new student |
| `PUT` | `/students/{id}` | Update student |
| `DELETE` | `/students/{id}` | Delete student |
| `GET` | `/students/search?course={course}` | Search by course |

---

## 📝 Request/Response Examples

### Create Student

**Request:**
```http
POST /api/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 22,
  "course": "Computer Science"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "course": "Computer Science",
  "enrollmentDate": "2025-10-16"
}
```

---

### Get All Students

**Request:**
```http
GET /api/students
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "course": "Computer Science",
    "enrollmentDate": "2025-10-16"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "course": "Mathematics",
    "enrollmentDate": "2025-10-16"
  }
]
```

---

### Get Student by ID

**Request:**
```http
GET /api/students/1
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "course": "Computer Science",
  "enrollmentDate": "2025-10-16"
}
```

---

### Update Student

**Request:**
```http
PUT /api/students/1
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "age": 23,
  "course": "Data Science"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "John Updated",
  "email": "john.updated@example.com",
  "age": 23,
  "course": "Data Science",
  "enrollmentDate": "2025-10-16"
}
```

---

### Delete Student

**Request:**
```http
DELETE /api/students/1
```

**Response:** `200 OK`
```
"Deleted"
```

---

### Search by Course

**Request:**
```http
GET /api/students/search?course=Computer Science
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "course": "Computer Science",
    "enrollmentDate": "2025-10-16"
  }
]
```

---

## ⚠️ Error Responses

### Validation Error

**Request:**
```json
{
  "name": "",
  "email": "invalid-email",
  "age": 15,
  "course": ""
}
```

**Response:** `400 Bad Request`
```json
{
  "name": "Name cannot be blank",
  "email": "Invalid email format",
  "age": "Age must be greater than 18",
  "course": "Course cannot be blank"
}
```

---

### Student Not Found

**Request:**
```http
GET /api/students/999
```

**Response:** `404 Not Found`
```json
{
  "error": "Student not found with id: 999"
}
```

---

### Duplicate Email

**Request:**
```json
{
  "name": "Test User",
  "email": "existing@example.com",
  "age": 20,
  "course": "Math"
}
```

**Response:** `400 Bad Request`
```json
{
  "error": "Email must be unique: existing@example.com"
}
```

---

## 🎯 Validation Rules

| Field | Rules |
|-------|-------|
| `name` | Required, 2-50 characters |
| `email` | Required, valid email format, unique |
| `age` | Required, 18-100 |
| `course` | Required, not blank |
| `enrollmentDate` | Auto-generated (current date) |

---

## 🗄️ Database Schema

### Students Table

| Column | Type | Constraints |
|--------|------|-------------|
| `id` | INTEGER | PRIMARY KEY, AUTO_INCREMENT |
| `name` | VARCHAR(255) | NOT NULL |
| `email` | VARCHAR(255) | NOT NULL, UNIQUE |
| `age` | INTEGER | NOT NULL |
| `course` | VARCHAR(255) | NOT NULL |
| `enrollment_date` | DATE | NOT NULL |

---

## 🐳 Docker Commands

### Start Database
```bash
docker-compose up -d
```

### Stop Database
```bash
docker-compose down
```

### View Logs
```bash
docker-compose logs -f
```

### Access PostgreSQL CLI
```bash
docker exec -it management psql -U teacher -d schooldb
```

**Inside PostgreSQL:**
```sql
\dt              -- List tables
SELECT * FROM students;
\q               -- Quit
```

---

## 🧪 Testing with Postman

1. Import the collection or create requests manually
2. Set base URL: `http://localhost:8080/api`
3. Test all endpoints with valid and invalid data
4. Verify error handling works correctly

**Test Scenarios:**
- ✅ Create student with valid data
- ❌ Create student with invalid email
- ❌ Create student with age < 18
- ❌ Create student with duplicate email
- ✅ Get all students
- ✅ Get student by ID
- ❌ Get non-existent student (404)
- ✅ Update student
- ✅ Delete student
- ✅ Search by course

---

## 📁 Project Structure

```
student-management-api/
├── docker-compose.yml
└── student-management/
    ├── src/
    │   └── main/
    │       ├── java/com/example/student_management/
    │       │   ├── Student.java                    # Entity
    │       │   ├── StudentRepository.java          # Data Access
    │       │   ├── StudentService.java             # Business Logic
    │       │   ├── StudentController.java          # REST API
    │       │   ├── StudentRequestDTO.java          # Input DTO
    │       │   ├── StudentResponseDTO.java         # Output DTO
    │       │   ├── GlobalExceptionHandler.java     # Error Handling
    │       │   └── StudentManagementApplication.java
    │       └── resources/
    │           └── application.properties
    └── pom.xml
```

---

## 🏗️ Architecture

This project follows a **layered architecture**:

```
Controller Layer (REST API)
      ↓
Service Layer (Business Logic)
      ↓
Repository Layer (Data Access)
      ↓
Database (PostgreSQL)
```

**Key Design Patterns:**
- **DTO Pattern** - Separates API contracts from database models
- **Repository Pattern** - Abstracts data access
- **Exception Handling** - Centralized with `@ControllerAdvice`
- **Dependency Injection** - Constructor injection for loose coupling

---

## 🔧 Configuration

### application.properties

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:1234/schooldb
spring.datasource.username=teacher
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

**Hibernate DDL Auto Modes:**
- `update` - Updates schema automatically (Development)
- `create` - Recreates schema on each startup
- `validate` - Only validates schema (Production)
- `none` - Disables schema management

---

## 🚀 Deployment

### Building for Production

```bash
./mvnw clean package
```

This creates a JAR file in `target/student-management-0.0.1-SNAPSHOT.jar`

### Running the JAR

```bash
java -jar target/student-management-0.0.1-SNAPSHOT.jar
```

### Environment Variables

For production, use environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://prod-host:5432/schooldb
export SPRING_DATASOURCE_USERNAME=prod_user
export SPRING_DATASOURCE_PASSWORD=secure_password
```

---

## 🐛 Troubleshooting

### Port Already in Use

**Error:** `Port 8080 is already in use`

**Solution:**
```bash
# Change port in application.properties
server.port=8081
```

### Database Connection Failed

**Error:** `Connection refused`

**Solution:**
1. Check Docker is running: `docker ps`
2. Start database: `docker-compose up -d`
3. Verify port: `docker-compose ps`

### TimeZone Error

**Error:** `Invalid value for parameter "TimeZone"`

**Solution:** Already handled in `StudentManagementApplication.java`:
```java
TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
```

---

## 📖 Learning Resources

**Topics Covered:**
- Spring Boot REST APIs
- Spring Data JPA
- Bean Validation
- Exception Handling
- DTO Pattern
- Docker & Docker Compose
- PostgreSQL Integration

---

## 👨‍💻 Author

**Rajat Kandpal**
- GitHub: [@i-rajatkandpal](https://github.com/i-rajatkandpal)
- Project: [Student Management API](https://github.com/i-rajatkandpal/student-management-api)

---

## 📄 License

This project is open source and available for educational purposes.

---

## 🙏 Acknowledgments

- Spring Boot Documentation
- Baeldung Tutorials
- Amigoscode YouTube Channel
---

