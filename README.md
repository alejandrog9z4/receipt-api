# Receipt-api

# Stack
- Java 8
- Springboot
- Maven
- H2 DB
- String JPA

# About
- This api helps to calculate the points obtained for the receipt information by saving the data in the database and helps to consult the number of points obtained per receipt

# Intructions
- Clone repository
- Use the IDE of your choice, import the maven project, configure the java 8 version for the project
- Implement maven clean in the IDE
- There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the de.codecentric.springbootsample Application class from your IDE.
- The project will be executed on port 8080 in a local environment
- If you want to obtain the jacoco report to view the code coverage of the unit tests, run a maven clean and a maven test, the report will be found in the target/my-reports/index.html folder.

# Endpoint
- post  localhost:8080/receipt/process
- get localhost:8080/receipt/{id}/points

# Body Request Example
- {
  "retailer": "M&M Corner Market",
  "purchaseDate": "2022-03-20",
  "purchaseTime": "14:33",
  "items": [
    {
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    },{
      "shortDescription": "Gatorade",
      "price": "2.25"
    }
  ],
  "total": "9.00"
}


