💊 Health Claims Backend System

A Spring Boot REST API for managing health insurance claims with approval workflow, validation, pagination, and MongoDB integration.

⚙️ Tech Stack
Java 17+
Spring Boot
Spring Web
Spring Data MongoDB
Bean Validation (Jakarta Validation)
SLF4J Logging
🏗️ Architecture
Controller → Service → Repository → MongoDB
🚀 Features
Create health claims
Auto approval for claims < 5000
Approve / Reject workflow
Validation using annotations
Pagination support
Global exception handling
Clean DTO (Request/Response separation)
📌 API Endpoints
Create Claim
POST /claims
Get All Claims (Paginated)
GET /claims?page=0&size=5
Get Claim by ID
GET /claims/{id}
Approve Claim
PUT /claims/{id}/approve
Reject Claim
PUT /claims/{id}/reject
Delete Claim
DELETE /claims/{id}
📊 Business Rules
If amount < 5000 → auto APPROVED
Only NEW claims can be approved/rejected
Approved claims cannot be rejected (and vice versa)
🧠 Exception Handling
Custom exceptions for invalid claims
Global exception handler for clean API responses
▶️ Run Project
mvn spring-boot:run
