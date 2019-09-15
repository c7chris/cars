# The Cars App

This application provides an API to access a repository of cars.

## Getting Started

This is a Maven-based Spring Boot application using the Web and Data components, with an H2 database.

### Prerequisites

Java 8 JDK along with an IDE like IntelliJ Idea or Eclipse.

### Installing

1) Clone the repository

```
$ git clone https://github.com/c7chris/cars.git
```

2) Run the application

```
$ mvn spring-boot:run
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

```
$ mvn test
```

### H2 Database console

Can be accessed at: [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Click on the 'Connect' button to open the database.

The gives a UI view of the database and allows queries to be run to check the database contents, such as:

```
SELECT * FROM CAR
```

## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [H2 Database](https://www.h2database.com) - The database
* [Datamuse](http://www.datamuse.com/api/) - The API used to find words that sound similar

## Authors

* **Christopher Coutinho** - [C7Chris](https://github.com/c7chris)

## Licence

This project has an open licence.

## Acknowledgments

* All the great people and companies I worked with.
* All the brilliant books and online courses I went through.
* All the online content I referenced and the authors that contributed to them.
