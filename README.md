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

3) Access the API

Please refer to the Swagger documentation for details of the endpoints available: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Sample requests

1) POST - create request

```
http://localhost:8080/car
{
	"make": "Nissan",
	"model": "Altima",
	"colour": "White",
	"year": 1992
}
```

2) GET - retrieve request

```
http://localhost:8080/car/1
```

3) PUT - update request

```
http://localhost:8080/car
{
	"id": 1,
	"make": "Toyota",
	"model": "Land Cruiser",
	"colour": "White",
	"year": 2015
}
```

4) DELETE - remove request

```
http://localhost:8080/car/1
```

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
