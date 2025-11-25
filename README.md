# Hostel Management System

A Java-based web application for managing hostel operations such as student registration, room allocation, room management, and availability tracking. This project is implemented using JSP and Servlets and follows a standard Maven project layout for easy building and deployment on Apache Tomcat.

---

## Table of contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation & Quick Start](#installation--quick-start)
- [Project Structure](#project-structure)
- [Configuration](#configuration)
- [Database (MySQL) — Optional](#database-mysql---optional)
- [Usage](#usage)
- [Modules](#modules)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)
- [Author / Contact](#author--contact)

---

## Features
- Student management: Add, update, delete student details.
- Room management: Add rooms, update room capacity, track availability.
- Room allocation and de-allocation to/from students.
- View student room status and overall hostel occupancy.
- JSP / Servlet based UI and controllers.
- Maven-based project for easy building and dependency management.
- Simple deployment to Apache Tomcat (WAR file).

---

## Tech Stack
- Java (JDK 8+)
- JSP / Servlets
- Maven
- Apache Tomcat 9+
- MySQL (optional — can also use in-memory or file-based DB depending on implementation)
- HTML/CSS/JS (for frontend JSPs)

---

## Prerequisites
- Java JDK 8 or newer
- Apache Tomcat 9+ installed and configured
- Maven 3.x
- (Optional) MySQL server if you plan to persist data

---

## Installation & Quick Start

1. Clone the repository:
   ```
   git clone https://github.com/kartikey-kk/HostelManagement.git
   cd HostelManagement
   ```

2. Build the project using Maven:
   ```
   mvn clean package
   ```
   This will generate a WAR file under `target/HostelManagement.war`.

3. Deploy:
   - Copy the generated WAR file into your Tomcat's `webapps/` directory, or use Tomcat Manager to deploy.
   - Start Tomcat (if not already running):
     ```
     (TOMCAT_HOME)/bin/startup.sh   # Linux/macOS
     (TOMCAT_HOME)\bin\startup.bat  # Windows
     ```

4. Open the application in your browser:
   ```
   http://localhost:8080/HostelManagement
   ```

---

## Project Structure
```
HostelManagement/
├── src/
│   └── main/
│       ├── java/        # Java source code (servlets, services, DAO, models)
│       ├── webapp/      # JSP, HTML, CSS, JS, WEB-INF (web.xml)
│       └── resources/   # Configuration files (properties, SQL scripts)
├── pom.xml              # Maven config
└── README.md
```

---

## Configuration

- Database and application configuration is typically stored in a properties file under `src/main/resources` (for example `db.properties` or similar). If your project expects a JNDI datasource, configure a `context.xml` for Tomcat.

Example `db.properties`:
```
db.url=jdbc:mysql://localhost:3306/hostel_db
db.username=root
db.password=your_password
db.driver=com.mysql.cj.jdbc.Driver
```

Tomcat context.xml example (place in `META-INF/context.xml` or Tomcat's `conf/context.xml`):
```xml
<Context>
  <Resource name="jdbc/HostelDB" auth="Container" type="javax.sql.DataSource"
            maxActive="100" maxIdle="30" maxWait="10000"
            username="root" password="your_password" driverClassName="com.mysql.cj.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/hostel_db?useSSL=false&serverTimezone=UTC"/>
</Context>
```

If your application uses `web.xml` to look up `java:comp/env/jdbc/HostelDB`, make sure the resource name matches.

---

## Database (MySQL) — Optional

If using MySQL, here is a minimal schema example to get started. Adjust fields/types according to your actual implementation.

```sql
CREATE DATABASE hostel_db;
USE hostel_db;

CREATE TABLE students (
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  phone VARCHAR(20),
  department VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE rooms (
  room_id INT AUTO_INCREMENT PRIMARY KEY,
  room_number VARCHAR(50) UNIQUE NOT NULL,
  capacity INT NOT NULL DEFAULT 1,
  current_occupancy INT NOT NULL DEFAULT 0,
  status ENUM('AVAILABLE','FULL','MAINTENANCE') DEFAULT 'AVAILABLE',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE allocations (
  allocation_id INT AUTO_INCREMENT PRIMARY KEY,
  student_id INT NOT NULL,
  room_id INT NOT NULL,
  allocated_on DATE,
  deallocated_on DATE,
  FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
  FOREIGN KEY (room_id) REFERENCES rooms(room_id) ON DELETE CASCADE
);
```

Run these scripts in your MySQL client and ensure your application points to `hostel_db`.

---

## Usage

- Register students through the Student module (Add student).
- Add rooms and set capacities in the Room module.
- Allocate rooms to students and de-allocate when needed.
- Use the Dashboard to view occupancy and room availability at a glance.
- Admin/Student roles and authentication can be added as a future enhancement (see below).

---

## Modules
- Login Module (optional based on configuration)
- Student Module: Manage student CRUD operations.
- Room Module: Create/manage rooms and capacity.
- Room Allocation Module: Allocate and de-allocate rooms.
- Dashboard: Occupancy and availability metrics.

---

## Future Enhancements
- Role-based authentication (Admin / Student)
- REST API endpoints for the frontend or external integrations
- Modern UI using Bootstrap, React, or another frontend framework
- PDF/Excel reporting for allocations and occupancy
- Add JUnit tests and CI/CD pipeline
- Containerize the application with Docker for easier deployment

---

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature`
3. Make your changes and add tests where appropriate.
4. Commit and push: `git push origin feature/your-feature`
5. Open a Pull Request with a clear description of the changes.

Please follow existing code conventions and keep commits focused and well described.

---

## License
This project is provided under the MIT License. See the LICENSE file for details (add a LICENSE file to the repository if not already present).

---

## Author / Contact
Author: Kartikey Kumar  
GitHub: https://github.com/kartikey-kk

If you want help adding features, tests, or CI, feel free to open an issue or submit a pull request.

---
