Resilient Email Sending Service (Java + Spring Boot)

A backend email service with retry, fallback, idempotency, rate limiting, and status tracking using mock providers.

Features

- Retry with exponential backoff  
- Fallback between providers  
- Prevent duplicate sends (idempotency)  
- Rate limiting (5 requests/minute)  
- Email send status tracking  
- Simple logging  
- REST API endpoint: `/api/email/send`

Setup
Prerequisites
-->Java 17+, Maven

Run Locally
```bash
git clone https://github.com/yourusername/resilient-email-service.git
cd resilient-email-service
mvn clean install
mvn spring-boot:run

Prasanna Kumar Kanakala
📧 Email: kanakalaprasannakumar@gmail.com
🔗 LinkedIn: https://tinyurl.com/52bv4c5v
