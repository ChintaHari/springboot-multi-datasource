# Spring Boot Multi-Datasource Application

## Overview
This Spring Boot application demonstrates CRUD operations with a multi-datasource configuration, allowing operations on two different databases simultaneously. The project serves as a practical example of managing separate data sources for different domains (e.g., `Employee` and `Department`) within the same application.

## Use Case
In many enterprise applications, different departments or functionalities may need to operate on separate databases. This project illustrates how to configure and manage these scenarios efficiently, providing a robust example for developers needing to implement similar features in their applications.

## Getting Started

### Prerequisites
- JDK 17
- Maven 3.x
- MySQL Server (locally installed or accessible instance)

### Installation and Setup
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/springboot-multi-datasource.git
   cd springboot-multi-datasource
   ```

2. **Database Setup**:
   Create two MySQL databases:

   ```bash
   CREATE DATABASE employee_db;
   CREATE DATABASE department_db;
   ```
   Update the application.properties or application.yml with the correct database credentials.

3. **Build the application**:

   ```bash
    mvn clean install
    ```
   
5. **Run the application**:
   
   ```bash
   mvn spring-boot:run
   ```

## Dependencies
This project includes the following main dependencies:

- `spring-boot-starter-data-jpa`: Includes Spring Data JPA, Hibernate, and other necessary libraries to integrate with JPA.
- `spring-boot-starter-web`: Provides all the dependencies required to build web applications including Spring MVC, Tomcat, and Jackson.
- `mysql-connector-java`: MySQL JDBC driver required to connect to MySQL databases.
- `spring-boot-devtools`: Provides features like automatic restarts and live reloads for improved development experience.
- `lombok`: Simplifies the code by auto-generating getters, setters, constructors, and other common methods.
- `spring-boot-starter-test`: Provides testing libraries including JUnit, Hamcrest, and Mockito along with Spring Test to facilitate unit and integration tests.


## Configuration

### Data Source Properties
The `application.properties` file contains the configurations for two separate data sources:

- **Employee Database**:
  ```properties
  spring.datasource.employee.url=jdbc:mysql://localhost:3306/employee_db
  spring.datasource.employee.username=springstudent
  spring.datasource.employee.password=springstudent
  ```

- **Department Database**:
  ```properties
    spring.datasource.department.url=jdbc:mysql://localhost:3306/department_db
    spring.datasource.department.username=springstudent
    spring.datasource.department.password=springstudent
  ```

### Hibernate and JPA
Configurations for Hibernate and JPA are also specified to manage entity persistence:

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
```

## Configuration Classes

The project features two main configuration classes:

- **EmployeeConfig**:
  This class is annotated with `@Primary` to denote the primary data source and includes beans for `DataSource`, `EntityManagerFactory`, and `TransactionManager`. This configuration focuses on the `Employee` entity and repository.

- **DepartmentConfig**:
  Similar to `EmployeeConfig`, but for the `Department` entity and repository, without the `@Primary` annotation, indicating it as the secondary data source.

These configurations enable the application to handle transactions and entity management for two distinct databases, showcasing a scalable approach for enterprise applications.

## Project Functionality

### Endpoints

The application provides REST endpoints for each entity:

- **Employee Endpoints**:
  - `GET /employee/all`
  - `POST /employee/add`
  - `GET /employee/id/{id}`
  - `GET /employee/name/{name}`

- **Department Endpoints**:
  - `GET /department/all`
  - `POST /department/add`
  - `GET /department/name/{name}`
  - `GET /department/id/{id}`

### Testing

To test the endpoints, you can use tools like Postman or CURL. Below are examples of how to add a new employee and a new department:

#### Adding an Employee
```bash
curl -X POST http://localhost:8080/employee/add -H 'Content-Type: application/json' -d '{"name":"John Doe", "email":"johndoe@example.com", "department":"HR"}'
```

#### Adding a Department
```bash
curl -X POST http://localhost:8080/department/add -H 'Content-Type: application/json' -d '{"name":"HR", "location":"Chicago"}'
```

### Verification

After performing operations, navigate to your MySQL Workbench to verify that the respective schemas (`employee_db` and `department_db`) have been appropriately updated according to the endpoint used. This step is crucial to confirm that your multi-datasource configuration is functioning as expected.


   
