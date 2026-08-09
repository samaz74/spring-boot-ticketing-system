# Spring Boot Ticketing System

A RESTful ticketing system built with Spring Boot, Spring Data JPA, and MariaDB.
Features include multi-subsystem support, role-based access, ticket assignment, messaging, notifications, and file attachments.

## Technologies
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- MariaDB
- Lombok
- Maven

## Project Structure
The project follows a layered architecture:
- **Model** — Entities: User, Ticket, SubSystem, TicketingSystem, Message, Notification, Attachment, SupportAccess
- **Repository** — Spring Data JPA repositories
- **Service** — Business logic
- **Controller** — REST API endpoints
- **DTO** — Request/Response objects
- **Exception** — Global exception handling

## API Endpoints

### Users `/api/users`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/users` | Create user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/username/{userName}` | Get user by username |
| GET | `/api/users/email/{email}` | Get user by email |
| PATCH | `/api/users/{id}/role` | Update user role |
| DELETE | `/api/users/{id}` | Delete user |

### Tickets `/api/tickets`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/tickets/{userId}/{subSystemId}` | Create ticket |
| GET | `/api/tickets/{id}` | Get ticket by ID |
| GET | `/api/tickets/user/{userId}` | Get tickets by user |
| GET | `/api/tickets/subsystem/{subSystemId}` | Get tickets by subsystem |
| GET | `/api/tickets/assigned/{supporterId}` | Get assigned tickets |
| GET | `/api/tickets/visible/{userId}` | Get visible tickets for supporter |
| PATCH | `/api/tickets/{ticketId}/assign/{userId}` | Assign ticket |
| PATCH | `/api/tickets/{ticketId}/status` | Change ticket status |

### Systems `/api/systems`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/systems` | Create system |
| GET | `/api/systems` | Get all systems |
| GET | `/api/systems/{id}` | Get system by ID |
| PATCH | `/api/systems/{id}/toggle` | Toggle system status |

### SubSystems `/api/subsystems`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/subsystems/{systemId}` | Create subsystem |
| GET | `/api/subsystems/{id}` | Get subsystem by ID |
| GET | `/api/subsystems/systems/{systemId}` | Get subsystems by system |
| PATCH | `/api/subsystems/{id}/toggle` | Toggle subsystem status |

### Messages `/api/messages`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/messages/{ticketId}/{userId}` | Send message |
| GET | `/api/messages/{ticketId}` | Get messages by ticket |

### Notifications `/api/notifications`
| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/notifications/user/{userId}` | Get unread notifications |
| PATCH | `/api/notifications/{id}/read` | Mark as read |
| PATCH | `/api/notifications/readAll/{userId}` | Mark all as read |

### Attachments `/api/attachments`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/attachments/ticket/{ticketId}` | Upload file for ticket |
| POST | `/api/attachments/message/{messageId}` | Upload file for message |
| GET | `/api/attachments/ticket/{ticketId}` | Get ticket attachments |
| GET | `/api/attachments/message/{messageId}` | Get message attachments |
| DELETE | `/api/attachments/{id}` | Delete attachment |

## How to Run

### Prerequisites
- Java 21
- MariaDB
- Maven

### Setup
1. Clone the repository
```bash
git clone https://github.com/samaz74/spring-boot-ticketing-system.git
```

2. Create database
```sql
CREATE DATABASE ticketing;
```

3. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mariadb://localhost/ticketing
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

4. Run the project
```bash
mvn spring-boot:run
```