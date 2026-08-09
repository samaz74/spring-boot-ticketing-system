# Spring Boot Ticketing System

A RESTful ticketing system built with Spring Boot, Spring Data JPA, Spring Security, and MariaDB.
Features include JWT authentication, token blacklisting, multi-subsystem support, role-based access, ticket assignment, messaging, notifications, and file attachments.

## Technologies
- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Spring Security + JWT
- MariaDB
- Lombok
- Maven

## Project Structure
The project follows a layered architecture:
- **Model** — Entities: User, Ticket, SubSystem, TicketingSystem, Message, Notification, Attachment, SupportAccess, InvalidatedToken
- **Repository** — Spring Data JPA repositories
- **Service** — Business logic
- **Controller** — REST API endpoints
- **DTO** — Request/Response objects
- **Security** — JWT Filter, UserDetails, SecurityConfig
- **Exception** — Global exception handling

## Security
JWT-based authentication with token blacklisting support.
- Tokens expire after 20 minutes
- Logged out tokens are blacklisted and cannot be reused
- Expired tokens are automatically cleaned up

### How to use
Add token to every request header:

```
Authorization: Bearer <token>
```


### API Endpoints

### Auth `/api/auth`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/auth/register` | Register new user |
| POST | `/api/auth/login` | Login and get JWT token |
| POST | `/api/auth/logout` | Logout and invalidate token |

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

### Support Access `/api/access`
| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/access/{userId}/{subSystemId}` | Grant access to supporter |
| GET | `/api/access/user/{userId}` | Get accesses by user |
| GET | `/api/access/subsystem/{subSystemId}` | Get supporters by subsystem |
| DELETE | `/api/access/{userId}/subsystem/{subSystemId}` | Revoke access |

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
jwt.secret=yourSecretKey
jwt.expiration=1200000
```

4. Run the project
```bash
mvn spring-boot:run
```

## Roles
| Role | Access |
|------|--------|
| ADMIN | Full access |
| SUPPORT | Assigned tickets and subsystems |
| USER | Own tickets only |