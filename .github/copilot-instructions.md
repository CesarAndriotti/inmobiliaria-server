# Copilot Instructions for Inmobiliaria Server

## Project Overview
Spring Boot 3.3.3 real estate management REST API with MySQL database. Java 21, Maven-based, JWT authentication, and comprehensive error handling via custom exceptions.

## Architecture & Key Patterns

### 1. **Exception Handling (Critical Pattern)**
- **Location**: `src/main/java/com/inmobiliaria/server/exceptions/`
- Use `CustomException` for all business logic errors - constructor takes message and `HttpStatus`
- **GlobalExceptionHandler** catches all `CustomException` and maps HTTP status codes to localized messages via `messages.properties`
- Always catch `DataAccessException` separately for database issues with appropriate status codes
- Example: `throw new CustomException(env.getProperty("data.access-error")+": "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);`

### 2. **Layered Architecture**
- **Controllers** (`api/*`): HTTP endpoints, input validation, throw CustomException
- **Services** (`services/{Entity}/`): Business logic, database operations, exception handling
- **Repositories**: Spring Data JPA, automatic CRUD operations
- **DTOs**: `ResponseDto<T>` for all responses with data, message, and HTTP status
- **Models**: JPA entities aligned with MySQL database schema

### 3. **Response Format**
All endpoints return `ResponseEntity<ResponseDto<T>>`:
```java
return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto<>(
    data,
    env.getProperty("http.success.ok"),
    HttpStatus.OK.value()
));
```

### 4. **Message Externalization**
- All user-facing messages stored in `src/main/resources/messages.properties`
- Access via `Environment` bean: `env.getProperty("key.name")`
- Covers HTTP status messages, database errors, validation messages
- Keys follow pattern: `http.{category}.{status}` or `database.{error-type}`

### 5. **Security & Authentication**
- JWT-based stateless authentication via `JwtRequestFilter`
- Routes in `SecurityConfig.java`:
  - `/api/auth/login`: public
  - `/api/**`: currently permitAll (review commented role-based rules)
- Session creation policy: `STATELESS`
- CORS enabled with default configuration

## Common Development Tasks

### Adding a New Entity Endpoint
1. Create model in `models/` with JPA annotations
2. Create repository extending `JpaRepository<Entity, ID>`
3. Create service interface in `services/{Entity}/` and implementation class
4. In service: wrap database calls in try-catch for `DataAccessException` and generic `Exception`
5. Create controller in `controllers/` with `@RestController` and entity-specific mappings
6. Controller methods should catch exceptions from service and throw `CustomException`
7. Always return `ResponseDto<T>` with appropriate messages from properties file

### Database Operations
- Repository queries wrapped in service try-catch blocks
- `DataIntegrityViolationException` → `HttpStatus.CONFLICT`
- `DataAccessException` → `HttpStatus.INTERNAL_SERVER_ERROR`
- Generic exceptions → `HttpStatus.INTERNAL_SERVER_ERROR`
- Check properties file for appropriate message keys

### Adding New Messages
- Edit `src/main/resources/messages.properties`
- Use hierarchical keys: `category.subcategory.key`
- Example: `http.client.bad-request`, `database.data-integrity-violation`
- Common categories: `http.success.*`, `http.client.*`, `http.server.*`, `database.*`

## Project Structure
```
src/main/java/com/inmobiliaria/server/
├── controllers/           # REST endpoints (16 entity controllers)
├── services/{Entity}/     # Business logic (Service interface + ServiceImpl)
├── repositories/          # JPA repository interfaces
├── models/               # JPA entities (33 entities)
├── dto/                  # ResponseDto, PropertyDto, etc.
├── exceptions/           # CustomException, GlobalExceptionHandler, ExceptionDetails
├── security/             # JwtRequestFilter, JWT utilities
├── utils/                # Utility classes
├── SecurityConfig.java   # Spring Security configuration
└── ServerApplication.java # Main application class
```

## Key Dependencies
- **Spring Boot 3.3.3**: Web, Data JPA, Security, Thymeleaf
- **JWT**: jjwt-api 0.11.5 (token generation/validation)
- **Database**: MySQL 8+ with JDBC driver
- **Validation**: jakarta.validation-api

## Configuration
- **Port**: 8081 (configured in `application.properties`)
- **Database**: MySQL at `192.168.0.25:3307` (schema: `base_inmobiliaria`)
- **File Upload**: Max 5MB (configurable in properties)
- **Timezone**: America/Argentina/Buenos_Aires

## Common Fixes & Patterns
- **Typo in properties**: `unhadled-error` should be `unhandled-error` (appears in multiple services)
- **Status codes**: Always use `HttpStatus` enum, not raw integers
- **Service interface contracts**: Methods declare `throws CustomException` - required for exception propagation
- **Null checks in services**: Validate entities exist before operations to throw appropriate 404/400 errors

## Testing & Build
```bash
mvn clean install        # Build project
mvn spring-boot:run      # Run application
```

Use Docker Compose file in `com/inmobiliaria/server/docker-compose.yml` for database setup.
