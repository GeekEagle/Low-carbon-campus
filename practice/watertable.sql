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

insert ���������ˮ���� values('�Խ�',0,null,null)
insert ���������ˮ���� values('����',0.369,null,null)
insert ���������ˮ���� values('����',0.315,null,null)
insert ���������ˮ���� values('����',0.286,null,null)
insert ���������ˮ���� values('����',0.275,null,null)
insert ���������ˮ���� values('����',0.239,null,null)
insert ���������ˮ���� values('����',0.220,null,null)
insert ���������ˮ���� values('����',0.194,null,null)

insert �Խ���ˮ���� values('������',0,null,null)
insert �Խ���ˮ���� values('<140',0.80,null,null)
insert �Խ���ˮ���� values('140-700',0.62,null,null)
insert �Խ���ˮ���� values('700-1400',0.55,null,null)
insert �Խ���ˮ���� values('1400-14000',0.36,null,null)
insert �Խ���ˮ���� values('>14000',0.30,null,null)
