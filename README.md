# Events

## ***In this task I used:***

* `Java 17`
* `Spring Framework`
* `Spring Boot`
* `Hibernate ORM`
* `PostgreSQL`
* `JUnit`
* `Liquibase`
* `Postman`
* `Lombok`
* `Log4j2`

## My application should run on port 8077. To work, you also need to deploy a local database `modsen_test_task`

***For the application, I deployed the `modsen_test_task` database locally and created Liquibase scripts to create and populate tables with test data***

1. Getting a list of all events (Method GET `http://localhost:8077/api/events`);
2. Getting a specific event by id (Method GET `http://localhost:8077/api/events/{id}`);
3. Registration (creation) of a new event (Method POST `http://localhost:8077/api/events`);
4. Updating information about an existing event (Method PUT `http://localhost:8077/api/events`);
5. Deleting an event (Method DELETE `http://localhost:8077/api/events/{id}`):

   *  Implemented the `Soft Delete` pattern - when deleting the event is not deleted, but changes the status canceled
