# Product Search Microservices based Application

### Overwiev
This project consists of two microservices: ProductService and SearchService, which work together to provide product management and search functionalities. The architecture leverages Apache Kafka for communication between the services and Elasticsearch for efficient search capabilities.

- Product Service
  - Responsibilities: Manages product data, including creating, updating, and deleting products.
  - Technologies Used: Spring Boot, Spring Data JPA, MySQL database.
  - Endpoints:
    - `POST /products`: Create a new product.
    - `PUT /products/{id}`: Update an existing product.
    - `DELETE /products/{id}`: Delete a product.
  - Kafka Integration: Sends product data changes (create/update/delete actions) to Kafka topics.
- Search Service
  - Responsibilities: Listens to product data changes from Kafka and indexes the data into Elasticsearch for searching.
  - Technologies Used: Spring Boot, Spring Data Elasticsearch.
  - Endpoints:
    - `GET /search`: Search for products based on query parameters.
  - Kafka Integration: Consumes messages from Kafka topics and updates the Elasticsearch index accordingly.
 
### Getting Started
##### Prerequisites
- Java 17 or later
- Apache Maven 3.6.3 or later
- Docker and Docker Compose
##### Setting Up the Environment
1. Clone the repository:
```
git clone https://github.com/Astan28/Kafka-Elasticsearch.git
cd kafka
```
2. Build and install the common module:
```
cd kafka-common
mvn clean install
cd ..
```
3. Build the `ProductService` and `SearchService`:
```
cd ProductService
mvn clean package
cd ../SearchService
mvn clean package
cd ..
```
##### Running the Services with Docker Compose
1. Start the environment (Kafka, Elasticsearch, Database) using Docker Compose:
```
docker-compose up -d
```
2. Run the `ProductService`:
```
cd ProductService
mvn spring-boot:run
```
3. Run the `SearchService`:
```
cd SearchService
mvn spring-boot:run
```
##### Testing the Services
You can test the microservices using tools like curl or Postman.
- **Product Service**
  - Create a product:
  ```
  curl -X POST -H "Content-Type: application/json" -d '{"name": "Product1", "description": "Description1"}' http://localhost:8080/products
  ```
  - Update a product:
  ```
  curl -X PUT -H "Content-Type: application/json" -d '{"name": "Updated Product", "description": "Updated Description"}'     http://localhost:8080/products/1
  ```
  - Delete a product:
  ```
  curl -X DELETE http://localhost:8080/products/1
  ```
- **Search Service**
  - Search for products:
  ```
  curl -X GET http://localhost:8081/search?query=Product1
  ```
##### Shutting Down the Environment
To stop and remove all Docker containers created by Docker Compose:
```
docker-compose down
```

### License
This project is licensed under the MIT License - see the LICENSE file for details.

### Acknowledgments
- **Spring Boot**
- **Apache Kafka**
- **Elasticsearch**
- **Docker**
