# Automobile Insurance System

Automobile Insurance System is a web application built with Spring Boot and Angular that allows users to manage insurance policies, proposals, claims, vehicles, and payments seamlessly.

## Features

- **User Management**: Users can register, log in, and manage their profiles.
- **Policy Management**: View available insurance policies.
- **Proposal Submission**: Users can submit insurance proposals for approval.
- **Claim Processing**: Users can submit claims for insured vehicles.
- **Payments**: Users can make premium payments securely.
- **Admin Dashboard**: Officers can approve/reject proposals and manage claims.


## Technologies Used

### Frontend
- Angular (Standalone Components)
- Bootstrap (For UI Styling)
- Typescript
- RouterModule (For Navigation)
- FormsModule (For Handling Forms)

### Backend
- Spring Boot
- Spring Data JPA
- MySQL
- REST APIs
- Spring Security
- JWT (JSON Web Token Authentication)


## Installation

### Prerequisites
- Node.js (v16+ recommended)
- Angular CLI (if not installed, run npm install -g @angular/cli)
- Java 17+
- Maven
- MySQL Database

### Steps to Run Locally
#### Backend
1. Navigate to the backend folder:
   ```console
   cd automobile-insurance-system/backend
   ```
2. Configure ```application.properties``` with database credentials:
   ```console
   spring.datasource.url=jdbc:mysql://localhost:3306/insurancesystem
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Build and run the Spring Boot application:
   ```console
   mvn spring-boot:run
   ```
4. The backend will be available at ```http://localhost:8080/```.

#### Frontend
1. Clone the repository:
   ```console
   git clone https://github.com/kishlaykiku/Automobile-Insurance-System.git
   cd automobile-insurance-system/frontend
   ```
2. Install dependencies:
   ```console
   npm install
   ```
3. Start the development server:
   ```console
   ng serve
   ```
4. Open your browser and navigate to ```http://localhost:4200/```.

## Usage

- **User Registration & Login**: Users can sign up and log in securely.
- **Managing Policies**: View available policies and select the best fit.
- **Proposal Submission**: Fill out the insurance proposal form and submit.
- **Claim Processing**: Submit claims and track their approval status.
- **Payments**: Make premium payments online.
- **Admin Panel**: Officers can approve/reject proposals and claims.
