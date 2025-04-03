# Base image with Java 21 and Maven
FROM maven:3.9-eclipse-temurin-21-alpine

WORKDIR /usr/src/app

# Copy project files
COPY pom.xml .
COPY src ./src

# Cache dependencies
RUN mvn dependency:go-offline

# Environment variables with defaults
ENV SELENIUM_HUB_URL=http://selenium-hub:4444
ENV BROWSER_NAME=chrome
ENV TEST_PATTERN=Automation

# Build and execute specific tests
CMD mvn test site allure:report \
    -Dtest=${TEST_PATTERN} \
    -Dselenium.hub.url=${SELENIUM_HUB_URL} \
    -Dbrowser=${BROWSER_NAME}