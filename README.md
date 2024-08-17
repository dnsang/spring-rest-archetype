# Getting Started Guide

## 1. Generate a New Project

To create a new project from this archetype, execute the following Maven command. 
Ensure you replace `groupId`, `artifactId`, and `package` with your specific values:

```bash
mvn archetype:generate -B \
  -DarchetypeGroupId=dev.sang.archetype \
  -DarchetypeArtifactId=spring-rest-archetype \
  -DarchetypeVersion=0.1 \
  -DgroupId=your.company \
  -DartifactId=your-project-name \
  -Dpackage=your.company.project \
  -DarchetypeCatalog=remote
```

## 2.Build the Project
Navigate to the project directory and build the project using Maven:
```bash
cd your-project-name/
mvn clean compile package
```

## 3. Check Test Coverage
Review the test coverage report by opening the following file in your web browser:

```bash
open target/site/jacoco/index.html
```

## 4. Start & Stop 
### a. (MacOS) Install coreutils if it is not already installed
```bash
brew install coreutils
```
### b.Make the `runservice` script executable:
```bash
chmod +x runservice
```
### c.Start the service in production mode:
```bash
./runservice start production
```

## 5.Verify Service Operation
Ensure that your service is running by executing the following command:
```bash
curl -X GET localhost:8080/api/v1/health
```
## 6.Stop the service
```bash
./runservice stop
```

