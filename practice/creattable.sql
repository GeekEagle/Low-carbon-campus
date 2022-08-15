create table ������Ϣ
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

create table ��ᳵ��
(
    Yeardate int,
	city char(50),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
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

create table ���ҵ���̼�ŷ�����
(
    Yeardate int not null,
	city char(50) not null primary key(Yeardate,city),
	/*FOREIGN KEY(city) REFERENCES ������Ϣ(city),*/
	caremipar double precision
)
go

create table ��ᳵ���ϼ�
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	energycons double precision,
	carbonemi double precision
)
go

create table �����ͨ
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	energyconsint double precision,
	avgmileage double precision,
	elecons double precision,
	carbonemi double precision
)
go

create table ������
(
    Yeardate int,
	city char(50),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	vehtype char(50) not null primary key(Yeardate,city,vehtype),
    rate char(15),    /*����*/
    gasconsint  double precision,      /*�ͣ�������ǿ��*/
	emissionint  double precision,    /*̼�ŷ�ǿ��*/ 
	gascons double precision,        /*�ͣ���������*/
	equelec double precision,       /*�������*/
	eleconsint  double precision,  /*���ǿ��*/
	elecons double precision,      /*���*/
	carbonemi double precision     /*̼�ŷ���*/
)
go

create table �������ϼ�
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	energycons double precision,
	carbonemi double precision
)
go

create table ���⳵
(
    Yeardate int not null,
	city char(50) not null,
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	vehtype char(50) not null primary key(Yeardate,city,vehtype),
     rate char(15),
    gasconsint  double precision,
	emissionint  double precision,
	gascons double precision,
	equelec double precision,       /*�������*/
	eleconsint  double precision,  /*���ǿ��*/
	elecons double precision,      /*���*/
	carbonemi double precision
)
go

create table ���⳵�ϼ�
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	energycons double precision,
	carbonemi double precision
)
go

create table �ܺĺϼ�
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	totalenergycons double precision,
	totalcarbonemi double precision,
	phvoldis double precision,
	garburndis double precision,
	renewendis double precision,
	traencons double precision,
	tracarboncons double precision
)
go

create table ͳ��
(
    Yeardate int  not null,
	city char(50) not null primary key(Yeardate,city),
	FOREIGN KEY(city) REFERENCES ������Ϣ(city),
	gascons double precision,   /*����������*/
	equelec double precision,   /*�������*/
	elecons double precision    /*�����*/
)
go

create table ��¼��Ϣ��
(
    userid char(50) not null primary key,
	password char(50)
)
go

insert ��¼��Ϣ�� values('147258','123456')
go