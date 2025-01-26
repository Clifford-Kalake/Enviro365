# Enviro365

Enviro365 is a Spring Boot application designed to manage waste categories and disposal guidelines. This project provides RESTful APIs for creating, updating, retrieving, and deleting waste categories and disposal guidelines.

## Technologies Used

- Java
- Spring Boot
- Maven
- JPA (Java Persistence API)
- ModelMapper
- Jakarta Validation

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/Clifford-Kalake/Enviro365.git
    cd Enviro365
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### Waste Categories

- **GET /api/waste-categories**: Retrieve all waste categories.
- **GET /api/waste-categories/{id}**: Retrieve a waste category by ID.
- **POST /api/waste-categories**: Create a new waste category.
- **PUT /api/waste-categories/{id}**: Update an existing waste category.
- **DELETE /api/waste-categories/{id}**: Delete a waste category by ID.

### Disposal Guidelines

- **GET /api/disposal-guidelines/{categoryId}**: Retrieve disposal guidelines by waste category ID.
- **POST /api/disposal-guidelines**: Create a new disposal guideline.
- **PUT /api/disposal-guidelines/{id}**: Update an existing disposal guideline.
- **DELETE /api/disposal-guidelines/{id}**: Delete a disposal guideline by ID.

## Project Structure

- `src/main/java/com/enviro/assessment/grad001/CliffordKalake/controllers`: Contains the REST controllers.
- `src/main/java/com/enviro/assessment/grad001/CliffordKalake/services`: Contains the service layer.
- `src/main/java/com/enviro/assessment/grad001/CliffordKalake/model`: Contains the entity classes.
- `src/main/java/com/enviro/assessment/grad001/CliffordKalake/dtos`: Contains the Data Transfer Objects (DTOs).

## Contact

For any inquiries, please contact Clifford Kalake at kalakec@gmail.com.
