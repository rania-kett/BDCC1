# Product Management System

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Security](#security)
- [Database](#database)

## Overview

Welcome to the Product Management System! This modern web application allows you to efficiently manage your product inventory with an intuitive and secure interface. Whether you're a simple user browsing products or an administrator managing the entire catalog, this application meets all your management needs.

## Features

### For All Users
- Secure authentication system
- Responsive interface adapted to all devices
- Modern design with Bootstrap
- Product list visualization

### For Standard Users
- Product catalog browsing
- Product details viewing (name, price, quantity)

### For Administrators
- Add new products
- Delete existing products
- Real-time data validation
- Secure access to sensitive features

## Technologies Used

| Technology | Version | Usage |
|------------|---------|-------|
| Java | 17+ | Main language |
| Spring Boot | 3.x | Backend framework |
| Spring Security | 3.x | Authentication & authorization |
| H2 Database | 2.x | In-memory database |
| Thymeleaf | 3.x | Template engine |
| Bootstrap | 5.3.2 | CSS framework |
| Lombok | 1.18+ | Boilerplate code reduction |

## Installation and Setup

### Prerequisites
Before starting, make sure you have installed:
- JDK 17 or higher
- Maven 3.6 or higher
- A modern web browser

### Launching the Application

1. **Download the project**
   ```bash
   git clone [your-repository]
   cd bdcc1-rania
   ```

2. **Build and run**
   ```bash
   # With Maven
   mvn clean spring-boot:run
   
   # Or build the JAR then execute it
   mvn clean package
   java -jar target/bdcc1-rania-1.0.0.jar
   ```

3. **Access the application**
   - Main application: http://localhost:8099
   - H2 Console: http://localhost:8099/h2-console
     - JDBC URL: `jdbc:h2:mem:products-db`
     - Username: `sa`
     - Password: (leave empty)

## Usage

### Connecting to the Application

Several accounts are available to test different features:

| Role | Username | Password | Permissions |
|------|----------|----------|-------------|
| User | `user1` or `user2` | `1234` | Product viewing |
| Administrator | `admin` | `1234` | Full product management |

### Application Navigation

1. **Login page**: Enter your credentials
2. **Home page**: List of all available products
3. **For administrators**:
   - "New Product" button to add a product
   - "Delete" button on each row to remove a product

### Adding a New Product

1. Click the "New Product" button (visible only to administrators)
2. Fill out the form:
   - Name: 3 to 50 characters (required)
   - Price: ≥ 0 (required)
   - Quantity: ≥ 1 (required)
3. Click "Save" to register

## Project Structure

```
bdcc1-rania/
├── src/main/java/ma/mundia/bdcc1rania/
│   ├── Bdcc1RaniaApplication.java      # Main class
│   ├── entities/
│   │   └── Product.java               # JPA entity
│   ├── repository/
│   │   └── ProductRepository.java     # Spring Data repository
│   ├── web/
│   │   └── ProductController.java     # MVC controller
│   └── sec/
│       └── SecurityConfig.java        # Security configuration
├── src/main/resources/
│   ├── templates/                     # Thymeleaf views
│   │   ├── Layout1.html               # Main layout
│   │   ├── Products.html              # Product list
│   │   ├── New-Product.html           # Add form
│   │   ├── login.html                 # Login page
│   │   └── notAuthorized.html         # Not authorized page
│   ├── application.properties         # Configuration
│   └── data.sql                       # Initial data (optional)
└── pom.xml                           # Maven dependencies
```

## Security

The application implements robust security with:

### Security Mechanisms
- Authentication via custom form
- Role-based authorization (USER/ADMIN)
- Password hashing with BCrypt
- CSRF protection enabled
- Secure session management

### Access Controls
- `/user/**`: Accessible to authenticated users
- `/admin/**`: Reserved for administrators
- `/public/**`: Public access
- `/login`: Accessible to all

## Database

### H2 Configuration
- Type: In-memory database
- Persistence: Data is reset on restart
- Console: Web interface available for debug

### Table Structure
```sql
CREATE TABLE PRODUCT (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(50) NOT NULL,
    PRICE DOUBLE NOT NULL,
    QUANTITY DOUBLE NOT NULL
);
```

### Initial Data
The application automatically pre-fills 3 products at startup:
1. Computer - 5,400.00 (12 units)
2. Printer - 1,200.00 (11 units)
3. Smart Phone - 12,000.00 (33 units)

## Troubleshooting

### Common Issues

1. **Port already in use**
   ```
   Error: Port 8099 already in use
   ```
   Solution: Modify `server.port` in `application.properties`

2. **Connection error**
   ```
   Unable to connect to database
   ```
   Solution: Check that H2 console is accessible at `/h2-console`

3. **Access denied**
   ```
   Access Denied
   ```
   Solution: Use the provided credentials with the appropriate role

### Logs and Debug
- SQL queries are displayed in the console
- Encoded passwords are logged at startup
- Use H2 console to inspect the database

## Future Development

Potential improvements:
- Result pagination
- Search functionality
- Data export
- Email notifications
- REST API versioning
- Docker containerization

---

Developed with Spring Boot and Bootstrap
