create table 城市信息
(
  city char(50) not null primary key,
  populationinfo int,
  avgcarposs double precision,
  avgyearmile double precision,
  avgbusnum double precision,
  avgdaybusmile double precision,
  avgtaxinum double precision,
  avgyeartaximile double precision
 );
go

create table 社会车辆
(
    Yeardate int,
	city char(50),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	vehtype char(50) not null primary key(Yeardate,city,vehtype),
     rate char(15),
    gasconsint  double precision,
	emissionint  double precision,
	gascons double precision,
	equelec double precision,
	eleconsint double precision,
	elecons double precision,
	carbonemi double precision
)
go

create table 国家电网碳排放因子
(
    Yeardate int not null,
	city char(50) not null primary key(Yeardate,city),
	/*FOREIGN KEY(city) REFERENCES 城市信息(city),*/
	caremipar double precision
)
go

create table 社会车辆合计
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	energycons double precision,
	carbonemi double precision
)
go

create table 轨道交通
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	energyconsint double precision,
	avgmileage double precision,
	elecons double precision,
	carbonemi double precision
)
go

create table 公交车
(
    Yeardate int,
	city char(50),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	vehtype char(50) not null primary key(Yeardate,city,vehtype),
    rate char(15),    /*比例*/
    gasconsint  double precision,      /*油（气）耗强度*/
	emissionint  double precision,    /*碳排放强度*/ 
	gascons double precision,        /*油（气）消耗*/
	equelec double precision,       /*折算电力*/
	eleconsint  double precision,  /*电耗强度*/
	elecons double precision,      /*电耗*/
	carbonemi double precision     /*碳排放量*/
)
go

create table 公交车合计
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	energycons double precision,
	carbonemi double precision
)
go

create table 出租车
(
    Yeardate int not null,
	city char(50) not null,
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	vehtype char(50) not null primary key(Yeardate,city,vehtype),
     rate char(15),
    gasconsint  double precision,
	emissionint  double precision,
	gascons double precision,
	equelec double precision,       /*折算电力*/
	eleconsint  double precision,  /*电耗强度*/
	elecons double precision,      /*电耗*/
	carbonemi double precision
)
go

create table 出租车合计
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	energycons double precision,
	carbonemi double precision
)
go

create table 能耗合计
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	totalenergycons double precision,
	totalcarbonemi double precision,
	phvoldis double precision,
	garburndis double precision,
	renewendis double precision,
	traencons double precision,
	tracarboncons double precision
)
go

create table 统计
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES 城市信息(city),
	gascons double precision,   /*汽油消耗量*/
	equelec double precision,   /*折算电力*/
	elecons double precision    /*电耗量*/
)
go

create table 登录信息表
(
    userid char(50) not null primary key,
	password char(50)
)
go

insert 登录信息表 values('147258','123456')
go