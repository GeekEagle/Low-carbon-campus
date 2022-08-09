create table ��·����
( 
  roadname char(50) not null,
  roaddiv char(50) not null primary key(roadname,roaddiv),
  waynum int,
  roadlength double precision,
  roadwidth double precision,
  lightpower double precision,
  lightime double precision,
  yearenercons double precision
);

create table ��������
( 
  zonenum char(50) not null,
  zonechara char(50) not null primary key(zonenum,zonechara),
  squqre double precision,
  density char(30),
  greenrate char(30),
  lightpower double precision,
  lightime double precision,
  yearenercons double precision
);

