# LiveQueryAPI

LiveQueryAPI is a Spring Boot RESTful API for managing question and comments for LiveQuery, built with Java 21 and Maven. 
It provides endpoints to create, retrieve, and delete questions and comments, using PostgreSQL as the database.

## Features

- RESTful endpoints for question/comment management.
- Input validation with Jakarta Validation.
- JPA integration for database operations.
- Logging with SLF4J.
- Lombok for reducing boilerplate code.

## Technologies

- Java 21
- Spring Boot 3.5.x
- Maven
- PostgreSQL
- Lombok

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.8+
- PostgreSQL

## API Endpoints

### Questions
- `POST /api/v1/questions` — Create a new question
- `GET /api/v1/questions/{questionId}` — Get a question by ID
- `DELETE /api/v1/questions/{questionId}` — Delete a question by ID
- `GET /api/v1/questions/all` — List all question

### Comments
- `POST /api/v1/comments` — Create a new comment
- `GET /api/v1/comments/{commentId}` — Get a comment by ID
- `DELETE /api/v1/comments/{commentId}` — Delete a comment by ID
- `GET /api/v1/comments/all` — List all comments

## Testing
Run tests with:

```mvn test```

## License
This project is licensed under the MIT License.