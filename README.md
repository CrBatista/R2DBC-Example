# R2DBC Example

This base project implements a reactive example with the R2DBC libraries.
Have configured a database connection pool, flyway in order to create the 'Greeting' table.
In order to run this project, it is mandatory to provide the properties in a ConfigService at the url specified in the bootstrap.yml. Also a PostgreSQL instance.


### Requirements
For building and running the application, it is mandatory to have:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Running the application locally

We highly recommend to run the app directly using the mvn command due to it's imported and configured the plugin <b>spring-boot-maven-plugin</b>:

```bash
mvn spring-boot:run
```

#### Authors

This project is possible because of the next developers:

<ul>
    <li>:pouting_man: Cristian Batista Herrera <i>batistaherreracristian@gmail.com</i></li>
</ul>
