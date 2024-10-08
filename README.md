# Setup Guide for SWEN3 Document Management System

## Prerequisites
Before starting the app on a new computer, ensure you have the following installed:
1. **Docker**: Ensure Docker and Docker Compose are installed.
    - [Docker Install Guide](https://docs.docker.com/get-docker/)
2. **Node.js and NPM (if not using Docker for frontend development)**:
    - Install Node.js: [Node.js Install Guide](https://nodejs.org/en/download/) (NPM comes bundled with Node.js)

3. **Java**: Ensure JDK 17 is installed (required by Spring Boot). Verify by running `java -version`.

---

## Initial Setup Steps

### 1. Clone the Repository
Open a terminal or command prompt and clone the repository:
```bash
git clone https://github.com/JohannesHenz/SWEN3.git
cd SWEN3-Document-Management-Backend
```
### 2. Install Backend Dependencies
Navigate to the Backend folder and build the backend using Maven:
```bash
cd Backend
./mvnw clean package
```

### 3. Install Frontend Dependencies
Navigate to the Frontend folder and install the necessary dependencies:
```bash
cd ../Frontend
npm install
```

### 4. Docker Setup
**Ensure Docker is running** and navigate back to the root project directory (SWEN3-Document-Management-Backend):
```bash
cd ..
docker-compose up --build
```

### 5. Access the Application
Once everything is set up and the containers are running:

Visit http://localhost in your browser to access the frontend.
The backend API will be available on http://localhost:8081.