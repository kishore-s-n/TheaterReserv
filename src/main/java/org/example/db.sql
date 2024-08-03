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

create table shows(
    morningshow
)
create table show(
    theaterid int references theater(theaterid),
    showid serial primary key,
    seatsbooked Integer[],
    showtime timestamp,
)