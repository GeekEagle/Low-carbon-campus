create table ��ˮϵͳ
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

create table ���������ˮ����
( 
  locationinfo char(30) not null primary key,
  avgenercons double precision,
  accsewage double precision,
  tolelecons double precision
);

create table �Խ���ˮ����
( 
  proscale char(50) not null primary key,
  avgenercons double precision,
  accsewage double precision,
  tolelecons double precision
);