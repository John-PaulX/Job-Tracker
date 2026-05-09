# 💼 Job Application Tracker

A full-stack web application to track job applications, manage
interview stages, and monitor your job search progress with a
real-time dashboard.

🔗 **Live Demo:** https://job-tracker-production-dd62.up.railway.app
🔗 **Source Code:** https://github.com/John-PaulX/Job-Tracker

---

## 📌 About The Project

As a fresher actively applying for jobs, I was struggling to keep
track of all my applications across multiple companies. I built
this tool to solve that real problem — a clean, simple web app
where I can add every application, update its status as I progress
through interviews, and see my overall job search statistics at a glance. 
This application is now deployed in production using Railway and accessible publicly on the internet.

---

## 📸 Application Preview

Screenshot 1:

### Dashboard:
<img width="1886" height="918" alt="Dashboard" src="https://github.com/user-attachments/assets/5a94c225-d1c8-4b1e-95d3-3f3ac415a9ee" />

Screenshot 2:

### Added Application Entries:
<img width="1912" height="376" alt="Applications" src="https://github.com/user-attachments/assets/eecf4fc7-4d54-4a86-95e8-6b586bcf6eb8" />


## ✨ Features

- ➕ Add job applications with company, role, location, salary and notes
- 📊 Real-time dashboard showing total applications, interviews, offers and rejections
- 🔄 Update application status — Applied → Interview → Offered → Rejected
- ✏️ Edit any application details anytime
- 🗑️ Delete applications
- 🔍 Search applications by company name
- 🔽 Filter by application status
- 📅 Track the date you applied
- 📱 Responsive design — works on mobile and desktop

---

## 🛠️ Tech Stack

### Backend
| Technology | Purpose |
|---|---|
| Java 17 | Core programming language |
| Spring Boot 3 | Backend framework, REST API, embedded Tomcat |
| Spring Data JPA | Database abstraction layer |
| Hibernate ORM | Maps Java objects to MySQL tables |
| MySQL | Relational database |
| Maven | Build tool and dependency management |

### Frontend
| Technology | Purpose |
|---|---|
| HTML5 | Page structure |
| CSS3 | Styling, responsive layout, animations |
| JavaScript | Dynamic UI, API calls using Fetch API |

---

## ☁️ Deployment

This application is deployed on Railway with MySQL cloud database.

Deployment workflow:

IntelliJ IDEA → Git → GitHub → Railway CI/CD → Live Production

Whenever code is pushed to GitHub, Railway automatically:
- Pulls latest code
- Builds the Maven project
- Creates the JAR file
- Deploys the latest version automatically

---

## 🏗️ Project Architecture
The backend follows a clean 3-layer architecture:

```text
HTTP Request
     ↓
Controller Layer (@RestController)
     ↓
Service Layer (@Service)
     ↓
Repository Layer (@Repository)
     ↓
MySQL Database
```

### Project Structure
src/main/java/com/jobtracker/job_tracker/
├── controller/
│   └── JobApplicationController.java   → REST API endpoints
├── service/
│   └── JobApplicationService.java      → Business logic
├── repository/
│   └── JobApplicationRepository.java   → Database operations
└── model/
├── JobApplication.java             → Entity class
└── ApplicationStatus.java         → Status enum
src/main/resources/
├── static/
│   └── index.html                      → Frontend (HTML+CSS+JS)
└── application.properties             → App configuration

---

## 🔌 REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/applications | Get all applications |
| GET | /api/applications/{id} | Get application by ID |
| POST | /api/applications | Add new application |
| PUT | /api/applications/{id} | Update application |
| PATCH | /api/applications/{id}/status | Update status only |
| DELETE | /api/applications/{id} | Delete application |
| GET | /api/applications/stats | Get dashboard statistics |
| GET | /api/applications/search?keyword= | Search by company name |
| GET | /api/applications/status/{status} | Filter by status |

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

Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtracker_db
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

**4. Run the application**
```bash
mvn spring-boot:run
```

**5. Open in browser**

http://localhost:8080

---

## 🔐 Production Configuration

For production deployment, sensitive credentials are managed using environment variables:

```env
MYSQL_URL=
MYSQLUSER=
MYSQLPASSWORD=
PORT=
```

This keeps database credentials secure and prevents exposing secrets in source code.

---

## 📊 Database Schema

```sql
CREATE TABLE job_applications (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    job_role     VARCHAR(100) NOT NULL,
    status       ENUM('APPLIED','INTERVIEW','OFFERED','REJECTED'),
    applied_date DATE,
    location     VARCHAR(100),
    salary_range VARCHAR(50),
    notes        TEXT
);
```

---

## 💡 What I Learned Building This

- Building REST APIs with Spring Boot following layered architecture
- Using Spring Data JPA and Hibernate for database operations without writing raw SQL
- Connecting a JavaScript frontend to a Java backend using Fetch API
- HTTP methods — GET, POST, PUT, PATCH, DELETE and when to use each
- Git version control and pushing projects to GitHub
- Handling CORS to allow frontend-backend communication
- Deploying Spring Boot applications to cloud platforms
- Using environment variables for secure production configuration
- Setting up CI/CD pipeline using GitHub + Railway
- Debugging production API issues

---

## 🚀 Future Improvements

- Add user authentication with Spring Security and JWT tokens
- Email notifications when interview is scheduled
- Export applications to PDF or Excel
- Add pagination for large number of applications

---

## 👤 Author

**John Paul**  
📧 johnpaulgummadi@gmail.com  
💻 [GitHub Profile](https://github.com/John-PaulX)

---

⭐ If you found this project useful, feel free to star the repository!
