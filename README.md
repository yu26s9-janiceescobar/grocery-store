# Grocery Store API

A SpringBoot Rest API for a grocery store.

## Technologies Used
- Java / SpringBoot
- Spring Data JPA
- MySQL
- Insomnia

# Features

- User registration and login authentication.
- Role based access control (user, admin)
- Product Catalog with search/filtering 
- Category Management (admin only)
- Shopping Cart (add product, update quantity, remove product, clear cart)
- User Profile Management
- Order Placement

## Project Structure

src/main/java/org/yearup/
├── controllers/              # REST endpoints
├── service/                  # Business logic
├── repository/                # Spring Data JPA repositories
├── models/                    # Entity and model classes
│   └── authentication/        # Login/register DTOs
├── exception/                  # Custom exceptions + global exception handler
└── security/                   # Security config
└── jwt/                    # JWT token provider, filter

## How To Run
1. Clone Repository
2. Create a MySQL database by running schema script (database folder found in project)
3. Update 'application.properties' with your database connection details
4. Run the application
5. API URL - 'http://localhost:8080'

## Author
Janice Escobar-Hernandez
