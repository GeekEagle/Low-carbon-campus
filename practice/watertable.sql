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

insert 地理分区污水处理厂 values('自建',0,null,null)
insert 地理分区污水处理厂 values('西北',0.369,null,null)
insert 地理分区污水处理厂 values('东北',0.315,null,null)
insert 地理分区污水处理厂 values('华北',0.286,null,null)
insert 地理分区污水处理厂 values('西南',0.275,null,null)
insert 地理分区污水处理厂 values('华中',0.239,null,null)
insert 地理分区污水处理厂 values('华东',0.220,null,null)
insert 地理分区污水处理厂 values('华南',0.194,null,null)

insert 自建污水处理厂 values('区域性',0,null,null)
insert 自建污水处理厂 values('<140',0.80,null,null)
insert 自建污水处理厂 values('140-700',0.62,null,null)
insert 自建污水处理厂 values('700-1400',0.55,null,null)
insert 自建污水处理厂 values('1400-14000',0.36,null,null)
insert 自建污水处理厂 values('>14000',0.30,null,null)
