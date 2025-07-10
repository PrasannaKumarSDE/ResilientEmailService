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

API Endpoints
1. Send Email
URL: POST http://localhost:8082/email/send

Request Body (JSON):
{
  "messageId": "123",
  "recipient": "recipient@example.com",
  "subject": "Hello",
  "body": "This is a test email"
}


Run Locally
```bash
git clone https://github.com/yourusername/resilient-email-service.git
cd resilient-email-service
mvn clean install
mvn spring-boot:run

Prasanna Kumar Kanakala
📧 Email: kanakalaprasannakumar@gmail.com
🔗 LinkedIn: https://tinyurl.com/52bv4c5v
