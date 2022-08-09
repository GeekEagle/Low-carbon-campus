create table 给水系统
( 
  popuinfo int not null,
  avgwatercons double precision,
  subrewaterrate char(15),
  avgdewater double precision,
  yeardewater double precision,
  getforwaterelecons double precision,
  pureforwaterelecons double precision,
  matforwaterelecons double precision,
  tolforwaterelecons double precision,
  cirforwatercaremi double precision,
  getrewaterelecons double precision,
  purerewaterelecons double precision,
  matrewaterelecons double precision,
  tolrewaterelecons double precision,
  cirewatercaremi double precision
);

create table 地理分区污水处理厂
( 
  locationinfo char(30) not null primary key,
  avgenercons double precision,
  accsewage double precision,
  tolelecons double precision
);

create table 自建污水处理厂
( 
  proscale char(50) not null primary key,
  avgenercons double precision,
  accsewage double precision,
  tolelecons double precision
);