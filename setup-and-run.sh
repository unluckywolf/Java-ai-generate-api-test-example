#!/bin/bash

echo "=== Java BDD Development Setup ==="
echo "This script will set up the Java development environment and run BDD tests"
echo ""

# Check if Java is available
if ! command -v java &> /dev/null; then
    echo "Java is not installed. Please install Java 11 or later."
    echo "For Ubuntu/Debian: sudo apt update && sudo apt install openjdk-11-jdk"
    echo "For macOS: brew install openjdk@11"
    exit 1
fi

# Check if Maven is available
if ! command -v mvn &> /dev/null; then
    echo "Maven is not installed. Please install Maven."
    echo "For Ubuntu/Debian: sudo apt update && sudo apt install maven"
    echo "For macOS: brew install maven"
    exit 1
fi

echo "Java version:"
java -version

echo "Maven version:"
mvn -version

echo ""
echo "=== Running BDD Tests ==="
echo ""

# Clean and test
echo "1. Cleaning project..."
mvn clean

echo "2. Running App BDD tests (compute.feature)..."
mvn test -Dtest=RunCucumberTest

echo "3. Running API BDD tests (compute_api.feature)..."
mvn test -Dtest=RunCucumberApiTest

echo "4. Generating test reports..."
mvn surefire-report:report

echo ""
echo "=== BDD Development Process Complete ==="
echo "Check target/surefire-reports/ for detailed test reports"