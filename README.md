#  HackMate Backend

> A Spring Boot powered student collaboration platform for hackathons and project team formation.

HackMate helps students discover teammates, create teams, manage requests, receive notifications, and collaborate with verified students through a secure and scalable backend architecture.

---

#  Features

### 🔐 Authentication & Security
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption
- Protected APIs
- Spring Security Integration

### 👤 User Profiles
- Create Profile
- Update Profile
- View Profile
- Upload Profile Information
- Student Verification Status

### 🎓 Student Verification
- College Email Verification
- Verified Student Badge Support
- Verification Status Tracking

### 🛠 Skills Management
- Add Skills
- Update Skills
- Skill-Based Discovery

### 📕 Interests Management
- Add Interests
- Update Interests
- Interest-Based Matching

### 🤝 Team Requests
- Create Team Requests
- Browse Team Requests
- Join Team Requests
- Accept Join Requests
- Reject Join Requests

### 🔍 Search & Discovery
- Search Students
- Filter by Skills
- Discover Compatible Teammates

### 🎯 Recommendation System
- Rule-Based Matching Engine
- Skill Matching
- Interest Matching
- Compatibility Scoring

### 🔔 Notifications
- Notification Generation
- Read/Unread Status Tracking

### 🤖 AI Skill Extraction
- Gemini API Integration
- Extract Skills from User Bio
- Automatic Skill Suggestions

---

#  Tech Stack

| Category | Technology |
|-----------|------------|
| Language | Java 17 |
| Framework | Spring Boot |
| Security | Spring Security |
| Authentication | JWT |
| Database | MySQL |
| ORM | Spring Data JPA |
| Build Tool | Maven |
| Password Encryption | BCrypt |
| API Testing | Postman |
| Cloud Database | Aiven MySQL |
| Deployment | Render |
| AI Integration | Gemini API |

---

#  Backend Architecture

```text
src/main/java
│
├── auth
├── user
├── skill
├── interest
├── recommendation
├── teamrequest
├── joinrequest
├── notification
├── verification
├── ai
│
├── config
├── security
└── websocket
```

---

#  Database Design

Core Entities:

- User
- Skill
- Interest
- TeamRequest
- JoinRequest
- Notification

Relationships:

- User ↔ Skills (Many-to-Many)
- User ↔ Interests (Many-to-Many)
- User ↔ Team Requests
- Team Request ↔ Join Requests
- User ↔ Notifications

---

#  Security Features

### JWT Authentication

Every protected endpoint requires a valid JWT token.

Authentication Flow:

```text
Register
   ↓
Login
   ↓
JWT Generated
   ↓
Token Sent In Header
   ↓
Protected APIs Accessible
```

### Password Security

```text
BCrypt Password Encoding
```

Used to securely store user passwords.

---

#  REST API Modules

### Authentication APIs

```text
POST   /api/auth/register
POST   /api/auth/login
```

### User APIs

```text
GET    /api/users/me
PUT    /api/users/me
```

### Verification APIs

```text
POST   /api/verification/verify-student
```

### Skills APIs

```text
POST   /api/skills
GET    /api/skills
```

### Interests APIs

```text
POST   /api/interests
GET    /api/interests/me
```

### Team Request APIs

```text
POST   /api/team-requests
GET    /api/team-requests
GET    /api/team-requests/{id}
DELETE /api/team-requests/{id}

```

### Join Request APIs

```text
POST   /api/join-requeststeam/{teamRequestId}
GET    /api/join-requeststeam/{teamRequestId}
```

### Recommendation APIs

```text
GET    /api/recommendations
```

### Notification APIs

```text
GET    /api/notifications
```

### AI APIs

```text
POST   /api/ai/extract-skills
```

---

# 🤖 AI Skill Extraction

HackMate integrates Gemini API to automatically identify technical skills from a user's bio.

### Example

Input:

```text
I build Spring Boot REST APIs, React applications and work with MySQL databases.
```

Output:

```json
[
  "Spring Boot",
  "React",
  "MySQL"
]
```

---

# ⚙️ Local Setup

### Clone Repository

```bash
git clone https://github.com/Kavya-Sahu16/hackmate.git
```

### Navigate To Project

```bash
cd hackmate
```

### Configure Database

Update:

```properties
application.properties
```

with your MySQL credentials.

### Install Dependencies

```bash
./mvnw clean install
```

### Run Application

```bash
./mvnw spring-boot:run
```

Server starts at:

```text
http://localhost:8081
```

---

# 🌍 Deployment

### Backend

Deployed on Render

Backend URL:

https://hackmate-backend-lwdz.onrender.com

### Database

Hosted on Aiven MySQL Cloud

---

# 🧪 Testing

API testing performed using Postman.

Tested modules:

- Authentication
- JWT Security
- Profile Management
- Verification
- Skills
- Interests
- Team Requests
- Join Requests
- Recommendations
- Notifications
- AI Skill Extraction

---

# 📸 API Screenshots

## User Registration

<img width="1470" height="956" alt="Registration" src="https://github.com/user-attachments/assets/742697b1-069a-4d11-a1f3-3527f2130101" />

## User Login (JWT Authentication)

<img width="1470" height="956" alt="Login" src="https://github.com/user-attachments/assets/52745ebe-dfaf-4d73-bb1f-083c087a1e03" />

## Student Verification

<img width="1470" height="956" alt="verifi" src="https://github.com/user-attachments/assets/86584a8d-dc1f-4cf9-ae49-6626e916aafc" />

## Recommendation Engine

<img width="1470" height="956" alt="recommendations" src="https://github.com/user-attachments/assets/64da1277-de68-408c-93f4-ae79ab326ade" />

## AI Skill Extraction

<img width="2047" height="1331" alt="AiSkillExtraction" src="https://github.com/user-attachments/assets/92586a59-99e5-4982-92e6-49d2527ca52b" />

---

# 🚀 Future Enhancements

- Real-Time Notifications with WebSockets
- Real-Time Team Updates
- In-App Chat System
- Advanced AI Recommendation Engine
- Hackathon Event Integration
- Team Analytics Dashboard

---

# 👩‍💻 Author

**Kavya Sahu**

B.Tech Computer Science Engineering  
Institute of Engineering & Technology (IET DAVV)

GitHub:

https://github.com/Kavya-Sahu16

LinkedIn:

https://linkedin.com/in/kavya-sahu-65038a31b

---

# ⭐ Project Highlights

✅ JWT Authentication

✅ Spring Security

✅ BCrypt Encryption

✅ Student Verification

✅ Team Formation System

✅ Join Request Workflow

✅ Search & Discovery

✅ Recommendation Engine

✅ Notification System

✅ AI Skill Extraction

✅ Cloud Deployment

---
