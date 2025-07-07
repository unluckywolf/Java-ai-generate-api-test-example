# Compute API Service Documentation

## Overview
The Compute API Service provides simple mathematical operations via RESTful endpoints. Currently supports addition operations with comprehensive error handling.

## Tech Stack
- **Language**: Java 11
- **Framework**: Spring Boot 2.7.17
- **Build Tool**: Maven
- **Testing**: Cucumber + Rest Assured + JUnit 5

## API Endpoints

### Addition Endpoint
**GET** `/api/add`

Performs addition of two numbers.

#### Parameters
- `a` (query parameter, required): First number to add
- `b` (query parameter, required): Second number to add

#### Request Examples
```bash
# Valid request
curl "http://localhost:8080/api/add?a=10&b=20"

# Missing parameter
curl "http://localhost:8080/api/add?a=10"

# Invalid input type
curl "http://localhost:8080/api/add?a=abc&b=10"
```

#### Response Format
**Success Response (200 OK)**
```json
{
  "result": 30.0
}
```

**Error Response (400 Bad Request)**
```json
{
  "error": "Missing parameter: b"
}
```

```json
{
  "error": "Invalid input type"
}
```

## Running the API Service Locally

### Prerequisites
- Java 11 or higher
- Maven 3.6+

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Java-ai-generate-api-test-example
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run tests**
   ```bash
   # Run app BDD tests
   mvn test -Dtest=RunCucumberTest
   
   # Run API BDD tests
   mvn test -Dtest=RunCucumberApiTest
   ```

4. **Start the API service**
   ```bash
   mvn spring-boot:run
   ```

   The service will start on `http://localhost:8080`

5. **Test the API**
   ```bash
   # Test addition endpoint
   curl "http://localhost:8080/api/add?a=10&b=20"
   
   # Expected response: {"result":30.0}
   ```

### Configuration
The application uses Spring Boot's default configuration. For custom configuration:

- **Port**: Set `server.port` in `application.properties`
- **Logging**: Configure logging levels in `application.properties`

### Project Structure
```
src/
├── main/java/com/example/compute/
│   ├── ComputeApplication.java     # Spring Boot main class
│   ├── ComputeController.java      # REST API controller
│   ├── CalculatorService.java      # Business logic service
│   └── Calculator.java             # Core calculation logic
├── test/java/com/example/compute/
│   ├── RunCucumberTest.java        # App BDD test runner
│   ├── RunCucumberApiTest.java     # API BDD test runner
│   ├── ComputeStepDefinitions.java # App BDD step definitions
│   └── ComputeApiStepDefinitions.java # API BDD step definitions
└── test/resources/
    ├── compute.feature             # App BDD scenarios
    ├── compute_api.feature         # API BDD scenarios
    └── application-test.properties # Test configuration
```

## Testing
The project follows strict BDD (Behavior-Driven Development) practices:

### App-Level Tests
- **File**: `compute.feature`
- **Framework**: Cucumber + JUnit 5
- **Coverage**: Core business logic for addition operations

### API-Level Tests
- **File**: `compute_api.feature`
- **Framework**: Cucumber + Rest Assured + JUnit 5
- **Coverage**: REST API endpoints, error handling, and response validation

### Test Execution
```bash
# Run all tests
mvn test

# Run specific test suite
mvn test -Dtest=RunCucumberTest
mvn test -Dtest=RunCucumberApiTest

# Generate test reports
mvn surefire-report:report
```

## Error Handling
The API implements comprehensive error handling:

1. **Missing Parameters**: Returns 400 with specific error message
2. **Invalid Input Types**: Returns 400 when parameters cannot be parsed as numbers
3. **Internal Errors**: Returns 500 for unexpected server errors

## Development Notes
- All code follows strict BDD development cycles
- Each scenario is implemented incrementally
- Tests are written first, then minimum code to pass
- Refactoring is performed after green tests
- Clean code principles are applied throughout

## Health Check
The API service provides Spring Boot Actuator endpoints for health monitoring:
- Health check: `GET /actuator/health`
- Info: `GET /actuator/info`

## Security
Current implementation focuses on functional requirements. For production deployment, consider:
- Input validation and sanitization
- Rate limiting
- Authentication/authorization
- HTTPS configuration
- CORS policies