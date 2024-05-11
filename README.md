Microservices Ecommerce Platform - Overview

Welcome to our Microservices Ecommerce Platform repository! This platform is designed to provide a robust foundation for building and scaling ecommerce websites, leveraging microservices architecture and a suite of core functionalities essential for any ecommerce operation. Below, you'll find an overview of the architecture, key features, and the technologies used in this project.

Architecture Overview:
Our platform is architected using microservices, allowing for modular development, deployment, and scalability. Key components of our architecture include:

Product Catalog Service:

Manages product information, including CRUD operations, categorization, and tagging.
Payment Gateway Integration:

Integration with Razorpay payment gateway for secure transaction processing.
Order Service:

Handles order creation, management, and processing, including order history and status tracking.
Notification Service:

Event-driven service responsible for sending emails at scale across different services within the platform.
ElasticSearch for Product Discovery:

Powerful sorting and filtering capabilities using ElasticSearch, enabling efficient product discovery for users.
Caching with Redis:

Optimized response time of APIs by caching static data using Redis Cache, reducing latency from ~500 ms to ~50 ms.
Event-Driven Email Service:
Our platform includes an event-driven email service that allows seamless communication across services. Key features of the email service include:

Asynchronous processing using Kafka for reliable event handling and message delivery.
Scalable architecture for sending emails at large scale without impacting service performance.
Integration with other microservices to trigger email notifications based on various events such as order confirmation, password reset, etc.
* Technologies Used:
* Backend Framework: Spring Boot, Spring Cloud
* Database: MySQL
* Caching: Redis
* Payment Gateway: Razorpay
* Message Broker: Kafka
* Testing: JUnit
