# finalpc Project

This is a Maven-based Java project named "finalpc". It is designed to demonstrate the use of Java 21.0.7 and includes the DJL (Deep Java Library) for machine learning capabilities.

## Project Structure

```
finalpc
├── src
│   ├── main
│   │   ├── java
│   │   │   └── App.java
│   │   └── resources
│   └── test
│       ├── java
│       │   └── AppTest.java
│       └── resources
├── pom.xml
└── README.md
```

## Requirements

- Java 21.0.7
- Maven 3.9.10

## Building the Project

To build the project, navigate to the root directory of the project and run the following command:

```
mvn clean install
```

## Running the Application

After building the project, you can run the application using the following command:

```
mvn exec:java -Dexec.mainClass="App"
```

## Running Tests

To run the tests, use the following command:

```
mvn test
```

## Dependencies

This project includes the DJL library for machine learning. The dependencies are managed in the `pom.xml` file.