# Web Application Project: Computer Store with Service

The goal of this project was to develop a web application for a computer store with an integrated service component. The application leverages various technologies and follows a three-tier architecture:

- **JSF (JavaServer Faces)**
- **EJB (Enterprise JavaBeans)**
- **JPA (Java Persistence API)**

The application includes three main user roles, each with distinct functionalities:

- **Admin:**
  - Manage employees and customers (e.g., add and remove users)
  - Manage products

- **Employee:**
  - Place and fulfill product orders
  - Manage service requests (e.g., add new repairs to the calendar)
  - Configure computer hardware

- **Customer:**
  - Purchase products
  - Create and manage a shopping cart
  - Configure computers
  - Create and manage an account

## MVC Pattern

The application implements the Model-View-Controller (MVC) pattern to structure the web application and create a graphical user interface. The MVC pattern divides the application into three main components:

- **Model:** Represents the application's data and business logic.
- **View:** Displays data from the model in the user interface. It may consist of sub-views for different parts of the interface.
- **Controller:** Handles user input, updates the model, and refreshes the view.

### MVC Example in the Application

1. **View Layer:** When a user adds a product to the cart, the view sends a request to the controller.
2. **Controller Layer:** The controller processes the request and interacts with the model to perform the necessary database operations.
3. **Model Layer:** The model executes the required operations on the database and returns the result to the controller.
4. **Controller Layer:** The controller updates the view with the result from the model.
5. **View Layer:** The updated view is displayed to the user.

## Implementation Details

### Database Schema

![image](https://github.com/user-attachments/assets/48df0a0a-1b45-4f6a-bdba-e06692271479)

### Security

The application employs the `j_security_check` mechanism to protect access to XHTML files and prevent unauthorized access to admin pages.

- **Example Usage:**
  - Each actor's folder is secured using this mechanism.

Passwords are hashed using SHA-256 for security:

- **Example Usage:**
  - `j_security_check` on the server compares hashed passwords.

## User Documentation

### Main Interface

Upon accessing the site, users are presented with the main interface.

- **Product Menu:** Users can hover over the menu to select products.
- **Search Functionality:** Users can search for products using the search button or Enter key.

### Account Management

- **Creating an Account:** Click on "Załóż konto" to create a new account. Fill in the required fields to complete the registration.
- **Logging In:** Click on the login button in the top menu to access the login page.

### As a Customer

- **Shopping Cart:** View and manage items in the cart, apply discounts, and proceed to checkout.
- **Chat with Consultant:** Send messages by entering text and pressing the blue button or Enter key.

### As an Admin

- **Order Management:** View and manage orders.
- **Product Management:** Add or modify products.
- **Employee Management:** View, add, and edit employee information.
- **Customer Management:** View, delete, and edit customer details.
- **Notifications:** Add and manage notifications.

### As an Employee

- **Product Management:** View and manage products.
- **Discount Management:** Add and remove discount codes.
- **Chat with Customers:** Communicate with customers via chat.

## Practical Example of MVC Usage

For instance, when a customer deletes a saved cart:

1. The customer clicks "Delete."
2. The view layer sends this request to the controller.
3. The controller processes the request and interacts with the model to perform the delete operation.
4. The model updates the database and informs the controller.
5. The controller updates the view with the new state of the cart.

## Testing

Unit testing was performed using JUnit and Mockito. While JUnit was effective for separating tests from code, Mockito presented challenges. The issues with Mockito led to incomplete unit testing of the model layer.

## UI

![image](https://github.com/user-attachments/assets/0f3b615b-f12c-4f0f-a656-942710140233)

![image](https://github.com/user-attachments/assets/1c158bf4-3456-49d1-9571-eed4d2cbe5d7)

## Conclusions

Developing this application was challenging, particularly due to the complexity of configuring Java EE technologies and maintaining the MVC architecture. Despite the difficulties, the experience with MVC was invaluable, and it is now seen as essential for future web applications.
