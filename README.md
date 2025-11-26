# 🐾 PetoPia-BE

Backend service for PetoPia — a comprehensive pet care management platform.

## Overview  
PetoPia-BE is a Spring Boot + Maven-based backend built to support pet owners and service providers.  
It manages pet profiles, health records, service bookings, and secure user flows — offering a modular, structured, and secure API-driven backend.  
The backend is designed for scalability, maintainability, and future enhancement (e.g. AI-powered logic, frontend integration).

## Core Features (Grouped)

### 1. User & Pet Management  
- Secure onboarding and authentication (JWT + OAuth2)  
- Pet profile handling (health info, habits, vaccination history, etc.)

### 2. Service & Appointment Handling  
- Manage service providers (vets, groomers, trainers, walkers)  
- Booking / scheduling for services like vet visits, grooming, training, walking

### 3. Health & Records Management  
- Pet health records & history logging  
- Vaccination schedules and reminders / notifications  

### 4. Safety & Utility Features  
- Lost & Found reporting flow  
- Emergency contact / quick-connect support  

### 5. Infrastructure & Integration Layer  
- Modular, clean backend architecture via Spring Boot + Maven  
- Relational data storage using MySQL (or configured DB)  
- Service monitoring via Spring Actuator & reliable configurations  
- WhatsApp based OTP verification and notifications (where configured)  

## Tech Stack  

- **Backend**: Java, Spring Boot, Spring Security (JWT + OAuth2), Spring Data JPA  
- **Build & Dependency Management**: Maven  
- **Database**: MySQL (relational, schema-driven, ACID-compliant)  
- **Monitoring & Service Info**: Spring Actuator  
- **Third-party Integration**: WhatsApp API for OTP and alerts  

## How to Run Locally  

```bash
# Clone the repository
git clone https://github.com/vivekTripathi1120/PetoPia-BE.git
cd PetoPia-BE

# Configure database & other environment-specific properties 
# (e.g. in src/main/resources/application.yml or application.properties)

# Use Maven wrapper to build
./mvnw clean install

# Run the application
./mvnw spring-boot:run


```

PROJECT FILE STRUCTURE:
PetoPia-BE/
│
├── .mvn/                      # Maven wrapper files  
│   └── wrapper/
├── src/                       # Source code  
│   ├── main/                  
│   │   ├── java/              # Java source packages  
│   │   └── resources/         # Configuration / property / resource files  
│   └── test/                  # (if tests are present)  
├── mvnw                       # Maven wrapper script (Unix)  
├── mvnw.cmd                   # Maven wrapper script (Windows)  
├── pom.xml                    # Maven build file / project descriptor  
├── .gitignore                 
├── .gitattributes             # Git attributes config  
└── (any other config or root files)
