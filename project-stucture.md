# AssetFlow: Enterprise Asset & Resource Management System

## Project Overview
AssetFlow is a centralized, role-based ERP platform designed to simplify and digitize how organizations track, allocate, and maintain physical assets and shared resources. It eliminates manual tracking inefficiencies by providing structured asset lifecycles, centralized resource booking, and real-time visibility into asset custody and condition.

---

## Technical Stack

### **Backend (Core API & Business Logic)**
* **Language:** Scala (v3.3.8 LTS)
* **Framework:** Play Framework (MVC architecture, highly scalable)
* **Concurrency:** Akka (Actor model for handling parallel bookings and background tasks)
* **Authentication:** JSON Web Tokens (JWT) via `java-jwt` for strict Role-Based Access Control (RBAC).

### **Data Layer**
* **Primary Database:** PostgreSQL (Relational integrity for complex entity relationships)
* **ORM / Query Builder:** Slick (Type-safe, functional database queries)
* **Caching:** Redis (via `scalacache-redis`) for rapid retrieval of dashboard KPIs and high-read data.

### **Frontend (Client Application)**
* **Framework:** React.js / Next.js 
* **State Management:** Redux Toolkit or Zustand
* **UI Library:** Material-UI (MUI) or Tailwind UI
* **Scheduling:** FullCalendar.js (For visual resource booking and overlap prevention)

### **Infrastructure & DevOps**
* **Build Tool:** sbt (Scala Build Tool)
* **Containerization:** Docker & Docker Compose (For consistent dev and production environments)
* **Cloud Storage:** AWS S3 (For storing asset photos, maintenance receipts, and check-in documents)

---

## Project Structure

The repository follows a standard Play Framework MVC layout. This keeps routing, business logic, and database interactions strictly isolated.

```text
assetflow-backend/
│
├── app/                                # Main application code
│   ├── controllers/                    # Handles HTTP requests and responses
│   │   ├── AssetController.scala       # Registers, updates, and fetches assets
│   │   ├── BookingController.scala     # Handles resource booking logic
│   │   ├── AuthController.scala        # Login and JWT generation
│   │   └── AuditController.scala       # Manages audit cycles and discrepancy reports
│   │
│   ├── models/                         # Database schemas and domain entities
│   │   ├── Asset.scala                 # Asset data transfer objects (DTOs)
│   │   ├── User.scala                  # Employee and Role definitions
│   │   └── Booking.scala               # Time-slot reservation logic
│   │
│   ├── repositories/                   # Data access layer (Slick queries)
│   │   ├── AssetRepository.scala       # Postgres interactions for assets
│   │   └── UserRepository.scala        
│   │
│   ├── services/                       # Core business logic (The "brain")
│   │   ├── AllocationService.scala     # Enforces conflict rules (no double-allocations)
│   │   ├── MaintenanceService.scala    # Approval workflows for repairs
│   │   └── NotificationService.scala   # Triggers alerts for overdue returns
│   │
│   └── utils/                          # Shared utilities
│       └── JwtUtil.scala               # Token decoding and validation
│
├── conf/                               # Application configuration
│   ├── application.conf                # DB connections, secret keys, Akka config
│   ├── routes                          # Defines all REST API endpoints (GET, POST, etc.)
│   └── evolutions/default/             # Database migration scripts (SQL)
│       ├── 1.sql                       # Initial schema creation
│       └── 2.sql                       # Subsequent table updates
│
├── project/                            # sbt configuration files
│   ├── build.properties                # sbt version definition
│   └── plugins.sbt                     # Play Framework and packaging plugins
│
├── test/                               # Testing suite (ScalaTest)
│   ├── controllers/                    # Unit tests for API endpoints
│   └── services/                       # Unit tests for business logic
│
├── build.sbt                           # Master dependency and build blueprint
├── docker-compose.yml                  # Local orchestration for Postgres and Redis
├── Dockerfile                          # Instructions for building the production image
└── README.md                           # Quickstart guide for developers