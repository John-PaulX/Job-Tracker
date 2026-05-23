# 💼 Job Application Tracker

A full-stack web application to track job applications, manage interview 
stages, and monitor your job search progress with a real-time dashboard.

🔗 **Live Demo:** https://job-tracker-9vyb.onrender.com  
💻 **GitHub:** https://github.com/John-PaulX/Job-Tracker

---

## 📌 About The Project

As a fresher actively applying for jobs, I was struggling to keep track 
of all my applications across multiple companies. I built this tool to 
solve that real problem — a clean, simple web app where I can add every 
application, update its status as I progress through interviews, and see 
my overall job search statistics at a glance.

The application includes full JWT-based authentication — users register 
and login securely, with passwords stored using BCrypt hashing.

---

## 📸 Application Preview

Screenshot 1:

### Dashboard:
<img width="1886" height="918" alt="Dashboard" src="https://github.com/user-attachments/assets/5a94c225-d1c8-4b1e-95d3-3f3ac415a9ee" />

Screenshot 2:

### Added Application Entries:
<img width="1912" height="376" alt="Applications" src="https://github.com/user-attachments/assets/eecf4fc7-4d54-4a86-95e8-6b586bcf6eb8" />

---

## ✨ Features

- 🔐 Secure registration and login with JWT authentication
- 🔒 BCrypt password hashing — passwords never stored in plain text
- ➕ Add job applications with company, role, location, salary and notes
- 📊 Real-time dashboard showing total, interviews, offers and rejections
- 🔄 Update application status — Applied → Interview → Offered → Rejected
- ✏️ Edit any application details anytime
- 🗑️ Delete applications
- 🔍 Search applications by company name
- 🔽 Filter by application status
- 📅 Track the date you applied
- 📱 Responsive design — works on mobile and desktop
- ☁️ Deployed live on Render with cloud PostgreSQL database

---

## 🛠️ Tech Stack

### Backend
| Technology | Purpose |
|---|---|
| Java 17 | Core programming language |
| Spring Boot 3 | Backend framework, REST API, embedded Tomcat |
| Spring Security | Authentication and authorization |
| JWT (jjwt 0.11.5) | Stateless token-based authentication |
| BCrypt | Secure password hashing |
| Spring Data JPA | Database abstraction layer |
| Hibernate ORM | Maps Java objects to MySQL tables |
| MySQL | Relational database |
| PostgreSQL | Production database on Render |
| Maven | Build tool and dependency management |

### Frontend
| Technology | Purpose |
|---|---|
| HTML5 | Page structure |
| CSS3 | Styling, responsive layout, animations |
| JavaScript | Dynamic UI, API calls using Fetch API |

### DevOps
| Technology | Purpose |
|---|---|
| Git | Version control |
| GitHub | Source code hosting |
| Render | Cloud deployment platform |
| Docker | Containerization for deployment |
| PostgreSQL | Cloud database on Render |

---

## 🔐 Security Implementation

The application uses JWT (JSON Web Token) based authentication:

User registers → password hashed with BCrypt → saved to database
User logs in → credentials verified → JWT token generated
JWT token sent to client → stored in memory (not localStorage)
Every API request → JWT sent in Authorization header
Spring Security filter validates JWT → allows or rejects request


### Security Flow
POST /api/auth/register  → Create account (public)
POST /api/auth/login     → Login and get JWT token (public)
GET  /api/applications   → Protected — requires valid JWT token
POST /api/applications   → Protected — requires valid JWT token
(all other endpoints)    → Protected — requires valid JWT token

---

## 🏗️ Project Architecture
```text
HTTP Request
     ↓
Spring Security Filter Chain
     ↓ (validates JWT token)
Controller Layer (@RestController)
     ↓ (handles HTTP requests)
Service Layer (@Service)
     ↓ (business logic)
Repository Layer (@Repository)
     ↓ (database operations)
Database (MySQL locally / PostgreSQL on Render)
```

### Project Structure
```text
src/main/java/com/jobtracker/job_tracker/
├── controller/
│   ├── AuthController.java          → /api/auth/register, /api/auth/login
│   └── JobApplicationController.java → /api/applications (protected)
├── service/
│   ├── AuthService.java             → Register and login logic
│   └── JobApplicationService.java   → CRUD business logic
├── repository/
│   ├── UserRepository.java          → User database operations
│   └── JobApplicationRepository.java → Application database operations
├── model/
│   ├── User.java                    → User entity
│   ├── JobApplication.java          → Application entity
│   └── ApplicationStatus.java       → Status enum
├── security/
│   ├── SecurityConfig.java          → Security rules and configuration
│   ├── JwtUtil.java                 → JWT generate, validate, extract
│   ├── JwtAuthFilter.java           → Filter for every request
│   └── CustomUserDetailsService.java → Load users from database
├── dto/
│   ├── RegisterRequest.java         → Registration request body
│   ├── LoginRequest.java            → Login request body
│   └── AuthResponse.java            → Token response
└── exception/
├── GlobalExceptionHandler.java   → Centralized error handling
├── ResourceNotFoundException.java
├── BadRequestException.java
├── DuplicateResourceException.java
└── ErrorResponse.java
src/main/resources/
├── static/
│   └── index.html                   → Complete frontend (HTML+CSS+JS)
└── application.properties           → App configuration
```
---

## 🔌 REST API Endpoints

### Auth Endpoints (Public)
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/auth/register | Register new account |
| POST | /api/auth/login | Login and get JWT token |

### Application Endpoints (Protected — JWT Required)
| Method | Endpoint | Description |
|---|---|---|
| GET | /api/applications | Get all applications |
| GET | /api/applications/{id} | Get application by ID |
| POST | /api/applications | Add new application |
| PUT | /api/applications/{id} | Update full application |
| PATCH | /api/applications/{id}/status | Update status only |
| DELETE | /api/applications/{id} | Delete application |
| GET | /api/applications/stats | Get dashboard statistics |
| GET | /api/applications/search | Search by company name |

---

## ⚙️ How to Run Locally

### Prerequisites
- Java 17 or higher
- MySQL 8.0
- Maven

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/John-PaulX/Job-Tracker.git
cd Job-Tracker
```

**2. Create the database**
```sql
CREATE DATABASE jobtracker_db;
```

**3. Configure database credentials**

Open `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtracker_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**4. Run the application**
```bash
mvn spring-boot:run
```

**5. Open in browser**
http://localhost:8080

**6. Register an account and start tracking!**

---

## ☁️ Deployment

The application is deployed on **Render** with:
- Spring Boot backend running as a Docker container
- PostgreSQL database hosted on Render
- Environment variables for secure credential management
- Dockerfile used for containerized deployment

Configuration uses environment variables so the same code
works both locally and in production:
```properties
# Local development uses MySQL by default
spring.datasource.url=${DATABASE_URL:jdbc:mysql://localhost:3306/jobtracker_db}
spring.datasource.username=${DATABASE_USERNAME:root}
spring.datasource.password=${DATABASE_PASSWORD:your_password}

# On Render, DATABASE_URL is set to PostgreSQL JDBC URL
# jdbc:postgresql://host/dbname
```

---

## 📊 Database Schema

```sql
-- Users table
CREATE TABLE users (
    id       BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,  -- BCrypt hash
    role     VARCHAR(50)  NOT NULL DEFAULT 'ROLE_USER'
);

-- Job Applications table
CREATE TABLE job_applications (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    job_role     VARCHAR(100) NOT NULL,
    status       VARCHAR(20)  NOT NULL,
    applied_date DATE,
    location     VARCHAR(100),
    salary_range VARCHAR(50),
    notes        TEXT
);
```

---

## 💡 What I Learned Building This

- Building REST APIs with Spring Boot following 3-layer architecture
- Implementing JWT-based authentication with Spring Security
- Secure password storage using BCrypt hashing
- Using Spring Data JPA and Hibernate for database operations
- Global exception handling with @RestControllerAdvice
- Connecting frontend to backend using JavaScript Fetch API
- Environment variable configuration for local and production
- Deploying a Spring Boot application using Docker on Render cloud platform
- Configuring PostgreSQL for production and MySQL for local development
- Git version control and GitHub for source code management

---

## 🚀 Future Improvements

- Add user-specific applications (each user sees only their own)
- Email notifications when interview is scheduled
- Export applications to PDF or Excel
- Pagination for large number of applications
- Password reset via email
- Google OAuth login

---

## 👤 Author

**John Paul**  
💻 [GitHub Profile](https://github.com/John-PaulX)

---

⭐ If you found this project useful, feel free to star the repository!
