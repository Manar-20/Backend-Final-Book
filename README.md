# Backend-Final-Book
## at this url repository you will find my breves work steps and commits from the beginning  (https://github.com/Manar-20/BookProject-Backend.git)
# 
Read Rate React
Read Rate React is a Java-based web application designed to manage a Book Review database. The application provides functionalities for user authentication, book management, and user roles to ensure secure and efficient interaction within the system.

## Table of Contents
Entities
Controllers
Security Configuration
Usage
Dependencies
Build and Run
Contributing
License
Entities
# The project includes several entities representing key elements within the system:

Book: Represents information about a book, including title, author, description, image URL, category, and associated reviews and ratings.
Category: Enum defining different book categories such as Fiction, Non-Fiction, Personal Development, and History.
Rating: Represents a user's rating for a book, including a custom-generated ID, rating value, and associations with the corresponding book and user.
Review: Represents a user's review for a book, including a custom-generated ID, user comment, and associations with the corresponding book and user.
Role: Represents the role of a user in the system, with a generated ID and a role name.
User: Represents a user in the system, including name, email, password, and associated role.
Controllers
# The project includes multiple controllers responsible for handling various operations:

AuthController: Manages user authentication and authorization, providing endpoints for user signup and token verification.
BookController: Handles operations related to books, including adding new books, retrieving all books, getting books by author, title, or ID, updating books, and deleting books.
RatingController: Manages operations related to book ratings, including getting all ratings, getting ratings by user or book, adding a new rating, and deleting a rating.
ReviewController: Responsible for operations related to book reviews, including getting all reviews, getting reviews by user or book, adding a new review, and deleting a review.
UserController: Manages user-related operations, including getting all users and finding a user by name.
Security Configuration
The security configuration ensures proper authentication and authorization for different roles:

Uses Spring Security for handling authentication and authorization.
Configures custom filters for authentication and authorization logic.
Defines rules for permitting access to various endpoints based on user roles.
## Usage
The application provides a set of RESTful endpoints for interacting with the bookstore database. Users with different roles have varying levels of access to these endpoints. Refer to the controllers and security configuration for details on available endpoints and their respective permissions.
## Note for Future Update (FUser Work):
Users (FUser) can now make reviews by providing a comment and rating from 1 to 5. This functionality enhances the user experience by allowing them to express their opinions and provide valuable feedback.

Users with the ROLE_USER role can utilize this feature, contributing to the collaborative review system.
# Dependencies
The project relies on the following key dependencies:

Spring Boot: Facilitates the development of robust Java applications.
Spring Security: Provides authentication and authorization features.
Lombok: Simplifies the creation of Java classes by reducing boilerplate code.
Hibernate: Enables interaction with a relational database using JPA (Java Persistence API).
Ensure that these dependencies are configured in your development environment.

# Build and Run
To build and run the application:

Clone the repository: git clone [repository-url]
Navigate to the project directory: cd read-rate-react
Build the project: ./gradlew build
Run the application: ./gradlew bootRun
The application will be accessible at http://localhost:8080.

# Contributing
Contributions to the project are welcome. If you would like to contribute, please follow these steps:

Fork the repository.
Create a new branch for your feature or bug fix: git checkout -b feature-name
Make your changes and commit them: git commit -m 'Description of your changes'
Push your changes to the forked repository: git push origin feature-name
Open a pull request on the original repository.

## Additional Resources
Trello Board
Link to Trello Board (https://trello.com/invite/b/2ahy7clL/ATTI9efab39ec5fdf5dc1d793c55ea0dc97eEB604443/mid-project)

UML Diagrams

Class Diagram :

![image](https://github.com/Manar-20/Backend-Final-Book/assets/111026905/bc79823f-a381-442d-ae3d-b6b87ae397c0)
Use-case-diagram : 

![image](https://github.com/Manar-20/Backend-Final-Book/assets/111026905/fed3ad9b-cd05-49b7-a11a-89733c6fe148)

Project Presentation:

Link to Project Presentation (https://www.canva.com/design/DAF09NI0iTI/EyNwOPsOM8xklCFMJin2Dw/view?utm_content=DAF09NI0iTI&utm_campaign=designshare&utm_medium=link&utm_source=editor)

