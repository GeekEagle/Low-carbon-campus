create table citygarinfo
(city char(50) not null primary key,
  populationinfo int,
  avgprogar double precision
 );
go

create table garinfo
(
   city char (50) not null,
   FOREIGN KEY(city) REFERENCES citygarinfo(city),
   yeardate int not null,
   typeinfo char(30) not null primary key(city,yeardate,typeinfo),
   rate char(15),
   carint double precision,
   carbonemi double precision
);
go
