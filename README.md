# Theater Booking Management System

This is a Java-based theater booking management system that interacts with a PostgreSQL database. The system allows users to manage theater details, book and cancel seats, and retrieve information about theaters.

## Features

- Display all theater information
- Display information for a specific theater by ID
- Book seats in a theater
- Cancel booked seats in a theater

## Prerequisites

- Java Development Kit (JDK) version 8 or higher
- PostgreSQL database

## Project Structure

- src/org/example/DbConnection.java: Manages database connections.
- src/org/example/movieDAO.java: Gives and shows datas about the movies that are being screened
- src/org/example/Booking.java: Gives the booking seats and cancel seats functionalities.
- src/org/example/Pricing.java: This provides the functionalities of the pricing features for the tickets that are being purchased and cancelled.
- src/org/example/TheaterDAO.java: Data Access Object (DAO) for managing theater data.
- src/org/example/Main.java: Main class with the user interface for interacting with the system.

## Database Schema

The project uses a PostgreSQL database with the following schema:

create table theaters(
    theaterid serial primay key,
    theatername varchar(255) not null,
    theateraddress varchar(255) not null,
    theaternumber int not null,
    theatercapacity int not null,
    noofrows int not null,
    shows references show=showid
);

create table movies(
    movieid serial primary key,
    moviename varchar(255),
    moviedescription varchar(255),
    movierating double not null
);

