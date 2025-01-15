# Spring Batch Transaction Processing 💳🔄

This project demonstrates the usage of **Spring Batch** for processing transactions, implementing key features such as **pagination**, **search**, and **update** operations. Additionally, the project utilizes the **Factory Pattern** to manage transaction processing components such as **processors**, **writers**, and **readers**.

### Features ✨

- **Pagination & Search 🔍**: Filter and fetch transaction records by `customerId`, `accountNumber`, and `description` with support for pagination.
- **Optimistic Locking 🔒**: Ensures safe concurrent updates to transaction records through versioning (an alternative to traditional optimistic locking).
- **Factory Pattern 🏭**: Uses a factory pattern to create and manage transaction components dynamically (processors, writers, readers).
- **Transactional Updates 🔄**: Allows the updating of transaction descriptions while managing transaction lifecycles effectively.

### ⚡ Setup & Running the Project

Prerequisites 🛠️

Before running the project, ensure you have the following installed:
1. **Java 8 or above ☕**

2. **Maven for dependency management 📦**


### Steps to Run the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/LinnLatHtun-MM/spring-batch-txn-processing.git

2. **Navigate to the project directory**:
   ```bash
    cd spring-batch-txn-processing

3. **Build the project using Maven**:
   ```bash
    mvn clean install

4. **Run the application**:
   ```bash
    mvn spring-boot:run
   
### 🖥️ Test the Application with Swagger UI

How to Access Swagger UI 🌐
1. **Open your web browser and go to the following URL**:
     ```bash
    http://localhost:8080/swagger-ui.html
     
2. **You will see an interactive interface where you can test the following API endpoints**:
    ```bash
    GET /: Retrieve transaction records with pagination and search. 📑

    PATCH /description/{id}: Update the description of a specific transaction by its ID. 📝
    
Test the API directly from the Swagger UI for a seamless experience! 💻✨

### The project structure is clearly laid out in the diagram below, showcasing the flow and architecture of the system.
![Diagram](https://github.com/LinnLatHtun-MM/spring_batch_txn/blob/main/src/main/resources/diagram.png)

### 📚 Acknowledgments

Spring Framework for its powerful batch processing and transaction management tools. 🏗️

Class Diagrams for helping visualize the architecture and flow of the system. 📐

Thank you for checking out this project! 🙏 We hope it serves as a valuable resource for learning and building robust batch processing applications. 🌱

Feel free to ⭐️ this repo, open issues, and contribute if you want to improve it! 😊



  
