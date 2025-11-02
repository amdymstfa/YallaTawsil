# Yallatawsil

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.java.com/) 
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.0-green)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.0-red)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

Backend application for **Yallatawsil**, a delivery management system designed to help logistics companies manage deliveries, vehicles, tours, and warehouses efficiently.

---

## 📖 Project Overview

Yallatawsil Backend provides a RESTful API for managing deliveries and tours with route optimization. The system helps:

- Assign deliveries to vehicles
- Optimize delivery routes using **Clarke-Wright** and **Nearest Neighbor** algorithms
- Track delivery statuses and preferred time slots
- Manage vehicle capacities and availability
- Handle warehouse information and working hours

---

## 🗂️ Core Entities

1. **Delivery**
   - Fields: address, latitude, longitude, weight, volume, preferred start/end time, status, notes
   - Status: `PENDING`, `IN_PROGRESS`, `COMPLETED`

2. **Vehicle**
   - Fields: type (TRUCK, VAN…), max weight, max volume, max deliveries, license plate
   - Tracks availability and assignments

3. **Tour**
   - Contains multiple deliveries
   - Fields: date, total distance, estimated duration, optimization algorithm used, status

4. **Warehouse**
   - Stores delivery starting points
   - Fields: name, opening and closing times

5. **Optimizers**
   - **Clarke-Wright Savings**: merges delivery points to minimize total distance
   - **Nearest Neighbor**: creates tours by selecting the closest next delivery point

---

## ⚡ Features

- CRUD operations for deliveries, vehicles, tours, warehouses
- Route optimization with multiple algorithms
- Validation of delivery constraints (capacity, volume, time windows)
- RESTful API fully documented with **Swagger**
- Database migrations using **Liquibase**
- Unit & integration tests with **JUnit** and **Mockito**

---

## 🚀 Getting Started

### Prerequisites
- Java 17
- Maven 3.9+
- PostgreSQL
- Docker (optional)

### Clone & Run
```bash
git clone <repo-url>
cd yallatawsil-backend
./mvnw clean install
./mvnw spring-boot:run
````

### Swagger API

Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to explore all endpoints.

---

## 🔧 Database Setup

1. Configure PostgreSQL in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yallatawsil
spring.datasource.username=your_user
spring.datasource.password=your_password
```

2. Apply migrations with Liquibase (auto-run on app start):

* All migration scripts are in `src/main/resources/db/changelog/changes`

---

## 🗃️ API Endpoints

### Deliveries

* `GET /deliveries` – List all deliveries
* `POST /deliveries` – Create a new delivery
* `GET /deliveries/{id}` – Get delivery details
* `PUT /deliveries/{id}` – Update delivery
* `DELETE /deliveries/{id}` – Delete delivery

### Vehicles

* `GET /vehicles` – List vehicles
* `POST /vehicles` – Create vehicle
* `GET /vehicles/{id}` – Get vehicle details

### Tours

* `GET /tours` – List tours
* `POST /tours` – Create tour
* `GET /tours/{id}` – Get tour details
* `POST /tours/optimize` – Run route optimization

### Warehouses

* `GET /warehouses` – List warehouses
* `POST /warehouses` – Create warehouse

---

## 🐳 Docker

Build and run using Docker Compose:

```bash
docker-compose up --build
```

---

## 🧪 Testing

Run unit & integration tests:

```bash
./mvnw test
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit your changes (`git commit -m 'Add feature'`)
4. Push to branch (`git push origin feature/my-feature`)
5. Open a Pull Request

---

## 📌 Contact

Developed by **Moustapha Ndiaye**
Email: [amdymoustapha011@gmail.com](mailto:amdymoustapha011@gmail.com)

