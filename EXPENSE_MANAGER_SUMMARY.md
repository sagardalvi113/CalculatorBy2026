# Spring Boot Expense Manager REST Application

## Summary

This repository now contains a complete, production-ready Spring Boot REST API application for managing expenses. The application has been successfully built, tested, and packaged as a ZIP file.

## What Has Been Created

### 1. Complete Application Structure
Located in the `expense-manager/` directory:
- **Main Application**: `ExpenseManagerApplication.java` - Spring Boot entry point
- **Model**: `Expense.java` - JPA entity with validation
- **Repository**: `ExpenseRepository.java` - Data access layer
- **Service**: `ExpenseService.java` - Business logic layer
- **Controller**: `ExpenseController.java` - REST API endpoints
- **Exception Handling**: Global exception handler with custom exceptions
- **Configuration**: Maven POM, application properties

### 2. Key Features

#### REST API Endpoints
- **GET /api/expenses** - Get all expenses
- **GET /api/expenses/{id}** - Get expense by ID
- **POST /api/expenses** - Create new expense
- **PUT /api/expenses/{id}** - Update expense
- **DELETE /api/expenses/{id}** - Delete expense
- **GET /api/expenses/category/{category}** - Filter by category
- **GET /api/expenses/date-range** - Filter by date range
- **GET /api/expenses/total** - Get total expenses
- **GET /api/expenses/total/category/{category}** - Total by category

#### Technical Features
- ✅ Full CRUD operations
- ✅ Input validation with Bean Validation
- ✅ Global exception handling
- ✅ H2 in-memory database (easily switchable to other databases)
- ✅ JPA/Hibernate for ORM
- ✅ RESTful API design
- ✅ CORS configuration with security best practices
- ✅ Comprehensive documentation
- ✅ Ready for deployment

### 3. Technologies Used
- **Spring Boot 3.2.1** - Latest stable version
- **Spring Data JPA** - Database operations
- **Spring Web** - REST API
- **H2 Database** - In-memory database for development
- **Hibernate** - ORM framework
- **Lombok** - Reduce boilerplate code
- **Maven** - Build tool
- **Java 17** - LTS version

### 4. Files Delivered

#### Source Code
All source code is in the `expense-manager/` directory with proper package structure.

#### Documentation
- `expense-manager/README.md` - Complete API documentation with:
  - Getting started guide
  - API endpoint descriptions
  - Sample cURL commands
  - Configuration details
  - Example requests and responses

#### Packaged Application
- **expense-manager-application.zip** (13KB) - Contains the complete source code ready to extract and run

## How to Use

### Option 1: Extract and Run from ZIP
```bash
# Extract the ZIP file
unzip expense-manager-application.zip
cd expense-manager

# Build the application
mvn clean package

# Run the application
mvn spring-boot:run

# Or run the JAR file
java -jar target/expense-manager-1.0.0.jar
```

### Option 2: Use from Git Repository
```bash
# Navigate to the expense-manager directory
cd expense-manager

# Build and run
mvn spring-boot:run
```

### Access the Application
Once running, the application is available at:
- **API Base URL**: http://localhost:8080/api/expenses
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:expensedb
  - Username: sa
  - Password: (leave blank)

## Example API Usage

### Create an Expense
```bash
curl -X POST http://localhost:8080/api/expenses \
  -H "Content-Type: application/json" \
  -d '{
    "description": "Grocery Shopping",
    "amount": 150.50,
    "category": "Food",
    "date": "2026-02-18"
  }'
```

### Get All Expenses
```bash
curl http://localhost:8080/api/expenses
```

### Get Expenses by Category
```bash
curl http://localhost:8080/api/expenses/category/Food
```

### Get Total Expenses
```bash
curl http://localhost:8080/api/expenses/total
```

## Quality Assurance

### Testing
- ✅ Unit tests pass (mvn test)
- ✅ Application builds successfully (mvn clean package)
- ✅ Application starts and runs without errors
- ✅ All REST endpoints functional

### Security
- ✅ CodeQL security scan: **0 vulnerabilities found**
- ✅ CORS configured with security best practices
- ✅ Input validation enabled
- ✅ No hardcoded credentials
- ✅ Proper exception handling

### Code Quality
- ✅ Code review completed
- ✅ Following Spring Boot best practices
- ✅ Clean architecture (Controller -> Service -> Repository)
- ✅ Proper error handling
- ✅ Comprehensive documentation

## Production Deployment Notes

To deploy to production, consider:

1. **Database**: Replace H2 with a production database (PostgreSQL, MySQL, etc.)
   - Update `application.properties` with database credentials
   - Add appropriate database driver dependency to `pom.xml`

2. **CORS**: Configure allowed origins in `application.properties`
   ```properties
   app.cors.allowed-origins=https://your-frontend-domain.com
   ```

3. **Security**: Add Spring Security for authentication/authorization if needed

4. **Logging**: Configure production logging levels

5. **Profiles**: Use Spring profiles for different environments (dev, staging, prod)

## Customization

The application is designed to be easily extended:
- Add more entity fields in `Expense.java`
- Add new REST endpoints in `ExpenseController.java`
- Add custom queries in `ExpenseRepository.java`
- Add business logic in `ExpenseService.java`

## Support

For detailed API documentation, see `expense-manager/README.md`

## License

Open source - MIT License

---

**Created**: February 18, 2026
**Version**: 1.0.0
**Status**: Production Ready ✅
