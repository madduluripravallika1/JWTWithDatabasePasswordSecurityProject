# JWTWithDatabasePasswordSecurityProject

# 🛡️ JWT Authentication with Database (Spring Boot + Security)

This project demonstrates how to implement **JWT-based authentication** using **Spring Boot, Spring Security, and MySQL Database**.  
It includes **user registration, login, token generation, and token validation**.

---

## 🚀 Tech Stack
- Java 17+
- Spring Boot 3.x
- Spring Security 6
- JPA + Hibernate
- MySQL Database
- JWT (JSON Web Token)

---

## ⚙️ Project Flow

1. **User Registration** → Saves new user in DB (with encoded password)
2. **Login** → Validates credentials and generates JWT token
3. **Validate Token / Access Secured Endpoint** → Use the token to access protected APIs

---

## 🧩 API Endpoints (with CURL Commands)

### 1️⃣ Register a New User
```bash
curl --location 'http://localhost:8080/auth/register' \
--header 'Content-Type: application/json' \
--data '{
    "username": "john123",
    "password": "mypassword123"
}'
Response:

User registered with id: 5

2️⃣ Login and Generate JWT Token
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username": "john123",
    "password": "mypassword123"
}'


✅ Example Response:

{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huMTIzIiwiaWF0IjoxNzYyMjQzNjAwLCJleHAiOjE3NjIyNDQ1MDB9.xxxxxxxxxxxx"
}

3️⃣ Validate the JWT Token
curl --location 'http://localhost:8080/auth/validateToken' \
--header 'Authorization: Bearer <your_generated_token>'


✅ Example Response:

Authenticated User: john123

4️⃣ Access a Secured Endpoint (Example)

If you have a /api/books or /api/test endpoint that requires authentication:

curl --location 'http://localhost:8080/api/books' \
--header 'Authorization: Bearer <your_generated_token>'


✅ Example Response:

[
    {
        "id": 1,
        "title": "Spring Boot Guide",
        "author": "John Doe"
    }
]

🕒 Token Expiry

By default, JWT tokens are valid for 15 minutes (900,000 ms).
You can change it in your JwtUtil.java:

private static final long EXPIRATION_TIME = 1000 * 60 * 15; // 15 minutes

🧠 Database Table (user)
id	password (hashed)	username
1	$2a$10$xxxxxx...	john123
2	$2a$10$yyyyyy...	jane123
🧾 How to Run Locally

Clone the repository

git clone https://github.com/madduluripravallika1/JWTWithDatabasePasswordSecurityProject.git
cd JWTWithDatabasePasswordSecurityProject


Configure MySQL in application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/jwt_security_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update


Run the app

mvn spring-boot:run


Test using the above CURL commands ✅

👩‍💻 Author

Pravallika Madduluri
💡 Spring Boot | JWT | Security | Database Integration


---

### 3️⃣ Save and Push It

Run these commands:
```bash
git add README.md
git commit -m "Added detailed README with JWT flow and curl examples"
git push origin main
