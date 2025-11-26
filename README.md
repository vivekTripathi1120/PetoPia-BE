
---

# 🐾 **PetoPia-BE**

### *Backend for an Intelligent Pet-Care Management Platform*
---

## 📌 **Overview**

**PetoPia-BE** is a Spring Boot backend designed to simplify and automate pet-care workflows for pet owners and service providers.
It manages onboarding, pet profiles, health tracking, services, safety modules, and secure authentication — all built on a modular monolithic architecture prepared for future microservices and AI expansion.

The system integrates **WhatsApp OTP**, **Actuator monitoring**, **Eureka discovery**, and **JWT + OAuth2** for robust security.

---

# 🧱 **Core Features (Grouped)**

### **1. User & Pet Management**

* Role-based login
* WhatsApp OTP-based onboarding
* Pet profiles: vaccination, behaviour, food, health

### **2. Appointments & Services**

* Vet, grooming, training, walking
* Service provider management
* Scheduling + availability engine

### **3. Health & Records**

* Vaccination timeline
* Reminders & alerts
* Health history logs

### **4. Safety & Support**

* Lost & Found module
* Emergency SOS / quick-contact
* Notification system

### **5. Intelligence Layer (Foundation)**

* Recommendation engine base
* Activity & pattern tracking
* Prepared for LLM-powered AI insights

---

# 🛠️ **Tech Stack**

### **Backend**

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* Spring Security (JWT + OAuth2)

### **Infrastructure**

* Eureka (service registration)
* Actuator (service health monitoring)

### **Database**

* MySQL

### **Integrations**

* WhatsApp API → OTP + notifications

---

# 📁 **Project Folder Structure**

```
PetoPia-BE/
│
├── .mvn/                 
│   └── wrapper/
│
├── src/
│   ├── main/
│   │   ├── java/com/petopia/petopiabe/
│   │   │   ├── auth/
│   │   │   ├── pet/
│   │   │   ├── appointment/
│   │   │   ├── services/
│   │   │   ├── health/
│   │   │   ├── lostfound/
│   │   │   ├── emergency/
│   │   │   ├── notification/
│   │   │   ├── security/
│   │   │   └── common/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/
│   │
│   └── test/ (if present)
│
├── pom.xml
├── mvnw  
├── mvnw.cmd  
└── README.md
```

---

# 🏛️ **Architecture Diagram (Monolithic Modular)**

```
                         ┌─────────────────────────┐
                         │       API Gateway       │ (Not required yet)
                         └──────────────┬──────────┘
                                        │
                       ┌────────────────┴────────────────┐
                       │        PetoPia-BE API           │
                       │   (Monolithic - Modularized)    │
                       └────────────────┬────────────────┘
                                        │
         ┌──────────────┬──────────────┬──────────────┬──────────────┐
         │              │              │              │              │
   Auth Module   Pet Module   Service Module   Health Module   Safety Module
 (JWT/OAuth2)    (Profiles)     (Vets,etc)     (Records)    (LostFound/SOS)
         │              │              │              │
         └──────────────┴──────────────┴──────────────┘
                        │
                 Recommendation Engine
                        │
                 WhatsApp Integration
                        │
                   MySQL Database
```

---

# 🗂️ **ER Diagram (Text Representation)**

```
User (user_id PK)
 ├── name
 ├── phone
 ├── email
 ├── role
 └── password_hash

Pet (pet_id PK)
 ├── owner_id FK → User.user_id
 ├── name
 ├── age
 ├── breed
 ├── health_info

ServiceProvider (provider_id PK)
 ├── type (vet/groomer/trainer/walker)
 └── experience

Appointment (appointment_id PK)
 ├── user_id FK
 ├── pet_id FK
 ├── provider_id FK
 ├── date
 └── status

HealthRecord (record_id PK)
 ├── pet_id FK
 └── details

LostFound (case_id PK)
 ├── pet_id FK
 └── status

EmergencyContact (ec_id PK)
 ├── user_id FK
 └── phone
```

---

# 🔌 **API Endpoints Overview**

### **Auth**

```
POST /api/v1/auth/register  
POST /api/v1/auth/login  
POST /api/v1/auth/verify-otp  
```

### **Pets**

```
GET  /api/v1/pets  
POST /api/v1/pets  
PUT  /api/v1/pets/{id}  
DELETE /api/v1/pets/{id}
```

### **Appointments**

```
POST /api/v1/appointments  
GET  /api/v1/appointments/user/{id}
PUT  /api/v1/appointments/{id}/status
```

### **Service Providers**

```
POST /api/v1/providers  
GET  /api/v1/providers  
GET  /api/v1/providers/type/{type}
```

### **Health**

```
POST /api/v1/health  
GET  /api/v1/health/pet/{id}
```

### **Lost & Found**

```
POST /api/v1/safety/lostfound
GET  /api/v1/safety/lostfound/{id}
```

### **Emergency**

```
POST /api/v1/safety/emergency
GET  /api/v1/safety/emergency/user/{id}
```

---

# ⚙️ **Environment Setup**

### **application.yml**

```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/petopia
    username: root
    password: yourpassword

  jpa:
    hibernate:
      ddl-auto: update

jwt:
  secret: YOUR_SECRET
  expiration: 86400000

whatsapp:
  apiKey: YOUR_KEY
  senderNumber: YOUR_NUMBER
```

---

# 🐳 **Docker Support (Optional)**

### **Dockerfile**

```dockerfile
FROM openjdk:17-jdk
WORKDIR /app
COPY target/petopia.jar petopia.jar
ENTRYPOINT ["java","-jar","petopia.jar"]
```

### **docker-compose.yml**

```yaml
version: "3.8"
services:
  mysql:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: petopia
    ports:
      - "3306:3306"

  petopia:
    build: .
    depends_on:
      - mysql
    ports:
      - "8080:8080"
```

---

# 🤝 **Contribution Guide**

* Fork the repo
* Create feature branch
* Commit with meaningful messages
* Open PR with description

---

# 📬 **Contact**

**Vivek Tripathi**
GitHub: *https://github.com/vivekTripathi1120/PetoPia-BE*
LinkedIn: *https://www.linkedin.com/in/vivek-tripathi-9a17a9230/*

---


