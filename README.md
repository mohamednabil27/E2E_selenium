# Selenium Grid Test Automation

End-to-End testing solution using:
- Selenium Grid (Hub + Nodes)
- Docker containers
- Chrome & Firefox browsers
- Allure Test Reporting

## Quick Start

### Prerequisites
- Docker
- Docker Compose

### Run Tests
1. **Start Selenium Grid and run tests**
   ```bash
   # Clean existing containers
   docker-compose down

   # Start services and build test-runner
   docker-compose up -d --build

   # Execute tests (Maven)
   docker-compose exec test-runner mvn test -Dtest=${TEST_PATTERN}

   # View Allure report (after tests complete)
   allure serve allure-reports
