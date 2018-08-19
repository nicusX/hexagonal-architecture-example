# Example of hexagonal architecture

This example is meant to demonstrate one possible internal organisation to apply principles of the
hexagonal architecture.

The application is not fully functional and is not meant to be an example of clean code ;)

I use SpringBoot, Service annotations and autowiring extensively for simplicity.


The application implements a Cat Clinic (very loosely based on the classic "Pet Clinic" application)":
* The application received notification of new Cats arrived, from a Kafka topic
* All new Cats are registered in a Directory, stored in a relational DB, using Spring Data JPA
* A REST Client retrieves Medical Records from an external service
* An exposed REST API allows to send enquiries about the state of a Cat, including his registration and medical records