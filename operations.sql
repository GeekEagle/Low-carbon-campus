CREATE proc calbussum(@city char(50),@Yeardate int)
as begin
    declare @tolenergycons double precision = 0;
    declare @totalcarbonemi double precision;
	declare @elecons double precision;
	declare @equelec1 double precision;
	declare @equelec2 double precision;
	declare @equelec3 double precision;

    set @totalcarbonemi=(select sum(carbonemi) from ������ where city=@city and Yeardate=@Yeardate)
    if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
    begin
        set @equelec1=(select equelec from ������ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
        set @tolenergycons += @equelec1
    end
    if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='��϶�����')
    begin
	    set @equelec2=(select equelec from ������ where city=@city and Yeardate=@Yeardate and vehtype='��϶�����')
        set @tolenergycons += @equelec2
	end
    if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='��Ȼ����')
    begin
	    set @equelec3=(select equelec from ������ where city=@city and Yeardate=@Yeardate and vehtype='��Ȼ����')
        set @tolenergycons += @equelec3
    end
    if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
    begin
	    set @elecons=(select elecons from ������ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
        set @tolenergycons += @elecons
    end
   
	if exists (select * from �������ϼ� where city=@city and Yeardate=@Yeardate)
        update �������ϼ� set energycons=round(@tolenergycons,2), carbonemi=round(@totalcarbonemi,2) where city=@city and Yeardate=@Yeardate
	else insert into �������ϼ� values(@Yeardate,@city,round(@tolenergycons,2),round(@totalcarbonemi,2))
end
go

CREATE proc calsocsum(@city char(50),@Yeardate int)
as begin
    declare @tolenergycons double precision = 0;
    declare @totalcarbonemi double precision = 0;
	declare @elecons double precision;
	declare @equelec double precision;

    set @totalcarbonemi=(select sum(carbonemi) from ��ᳵ�� where city=@city and Yeardate=@Yeardate)
    if exists(select * from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
    begin
        set @equelec=(select equelec from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
        set @tolenergycons = @tolenergycons+@equelec
    end
    if exists(select * from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
    begin
        set @elecons=(select elecons from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
        set @tolenergycons = @tolenergycons+@elecons
    end
	if exists (select * from ��ᳵ���ϼ� where city=@city and Yeardate=@Yeardate)
		update ��ᳵ���ϼ� set energycons=round(@tolenergycons,2), carbonemi=round(@totalcarbonemi,2) where city=@city and Yeardate=@Yeardate
	else insert into ��ᳵ���ϼ� values(@Yeardate,@city,round(@tolenergycons,2),round(@totalcarbonemi,2))
end
go

CREATE proc caltaxisum(@city char(50),@Yeardate int)
as begin
    declare @tolenergycons double precision = 0;
    declare @totalcarbonemi double precision = 0;
	declare @elecons double precision;
    declare @carbonemi double precision
	declare @equelec double precision;
    declare my_cursor cursor FORWARD_ONLY
        for select carbonemi from ���⳵ where city=@city and Yeardate=@Yeardate
    open my_cursor
    fetch next from my_cursor into @carbonemi
    while @@FETCH_STATUS=0
        begin
            set @totalcarbonemi += @carbonemi
            fetch next from my_cursor into @carbonemi
        end
    close my_cursor
    deallocate my_cursor
    if exists(select * from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
    begin
        set @equelec=(select equelec from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
        set @tolenergycons += @equelec
    end
    if exists(select * from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
    begin
        set @elecons=(select elecons from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
        set @tolenergycons += @elecons
    end
	if exists (select * from ���⳵�ϼ� where city=@city and Yeardate=@Yeardate)
			update ���⳵�ϼ� set energycons=round(@tolenergycons,2), carbonemi=round(@totalcarbonemi,2) where city=@city and Yeardate=@Yeardate
	else insert into ���⳵�ϼ� values(@Yeardate,@city,round(@tolenergycons,2),round(@totalcarbonemi,2))
end
go

CREATE proc renewsum(@city char(50),@Yeardate int,@phvoldis double precision,@garburndis double precision)
as begin
	declare @renewendis double precision = 0;
	declare @traencons double precision = 0;
	declare @tracarboncons double precision = 0;
	declare @tolcons double precision;
	declare @tolemi double precision;
	declare @caremipar double precision;
	if exists(select * from �ܺĺϼ� where city=@city and Yeardate=@Yeardate)
	begin
	     set @tolcons = (select totalenergycons from �ܺĺϼ� where city=@city and Yeardate=@Yeardate)
         set @tolemi = (select totalcarbonemi from �ܺĺϼ� where city=@city and Yeardate=@Yeardate)
         set @traencons = @tolcons-@phvoldis-@garburndis
    end
	if exists(select * from ���ҵ���̼�ŷ����� where city=@city and Yeardate=@Yeardate)
	begin
	     set @caremipar = (select caremipar from ���ҵ���̼�ŷ����� where city=@city and Yeardate=@Yeardate)
         set @renewendis = (@phvoldis+@garburndis)*@caremipar/1000
         set @tracarboncons = @tolemi-@renewendis
	end
	if(@phvoldis>0 and @garburndis>0)
        update �ܺĺϼ� set phvoldis=round(@phvoldis,2),garburndis=round(@garburndis,2),renewendis=round(@renewendis,2),
                        traencons=round(@traencons,2),tracarboncons=round(@tracarboncons,2) where city=@city and Yeardate=@Yeardate
    else update �ܺĺϼ� set traencons=round(@traencons,2),tracarboncons=round(@tracarboncons,2) where city=@city and Yeardate=@Yeardate
end
go

CREATE proc statistic(@city char(50),@Yeardate int)
as begin
	declare @gascons double precision = 0;   /*����������*/
	declare @equelec double precision = 0;   /*�������*/
	declare @elecons double precision = 0;    /*�����*/
	declare @socgascons double precision; declare @socequelec double precision; declare @socelec double precision;
	declare @taxigascons double precision;declare @taxiequelec double precision;declare @taxielec double precision;
	declare @busgascons double precision; declare @busequelec double precision; declare @buselec double precision;
	declare @railelec double precision;   declare @busmixgascons double precision; declare @busmixequelec double precision;

	if exists(select * from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
	begin
	      set @socgascons=(select gascons from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
	      set @socequelec=(select equelec from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
          set @equelec += @socequelec
          set @gascons += @socgascons
	end
	if exists(select * from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
	begin
	    set @socelec=(select elecons from ��ᳵ�� where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
        set @elecons += @socelec
	end
	if exists(select * from �����ͨ where city=@city and Yeardate=@Yeardate)
	begin
	    set @railelec=(select elecons from �����ͨ where city=@city and Yeardate=@Yeardate)
        set @elecons += @railelec
   end
	if exists(select * from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
	begin
	    set @taxigascons=(select gascons from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
	    set @taxiequelec=(select equelec from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
        set @equelec += @taxiequelec
        set @gascons += @taxigascons
	end
	if exists(select * from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
	begin
	    set @taxielec=(select elecons from ���⳵ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
        set @elecons += @taxielec
	end
	if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
	begin
	    set @busgascons=(select gascons from ������ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
	    set @busequelec=(select equelec from ������ where city=@city and Yeardate=@Yeardate and vehtype='ȼ�ͳ�')
        set @equelec += @busequelec
        set @gascons += @busgascons
	end
	if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
	begin
	    set @buselec=(select elecons from ������ where city=@city and Yeardate=@Yeardate and vehtype='�綯��')
        set @elecons += @buselec
	end
    if exists(select * from ������ where city=@city and Yeardate=@Yeardate and vehtype='��϶�����')
	begin
	      set @busmixgascons=(select gascons from ������ where city=@city and Yeardate=@Yeardate and vehtype='��϶�����')
	      set @busmixequelec=(select equelec from ������ where city=@city and Yeardate=@Yeardate and vehtype='��϶�����')
          set @equelec += @busmixequelec
          set @gascons += @busmixgascons
    end
	if exists (select * from ͳ�� where city=@city and Yeardate=@Yeardate)
        update ͳ�� set gascons=round(@gascons,2), equelec=round(@equelec,2),elecons=round(@elecons,2) where city=@city and Yeardate=@Yeardate
	else insert into ͳ�� values(@Yeardate,@city,round(@gascons,2),round(@equelec,2),round(@elecons,2))
end
go

CREATE procedure totalsum(@city char(50),@Yeardate int)
as begin
    declare @soctolcons double precision;
    declare @taxitolcons double precision;
    declare @railtolcons double precision;
	declare @bustolcons double precision;
	declare @soctolemi double precision;
    declare @taxitolemi double precision;
    declare @railtolemi double precision;
	declare @bustolemi double precision;
    declare @tolcons double precision = 0;
	declare @tolemi double precision = 0;
   if exists(select * from ��ᳵ���ϼ� where city=@city and Yeardate=@Yeardate)
    begin
        set @soctolcons = (select energycons from ��ᳵ���ϼ� where city=@city and Yeardate=@Yeardate)
	    set @soctolemi = (select carbonemi from ��ᳵ���ϼ� where city=@city and Yeardate=@Yeardate)
        set @tolcons += @soctolcons
        set @tolemi += @soctolemi
    end 
    if exists(select * from ���⳵�ϼ� where city=@city and Yeardate=@Yeardate)
    begin
	    set @taxitolcons = (select energycons from ���⳵�ϼ� where city=@city and Yeardate=@Yeardate)
	    set @taxitolemi = (select carbonemi from ���⳵�ϼ� where city=@city and Yeardate=@Yeardate)
        set @tolcons += @taxitolcons
        set @tolemi += @taxitolemi
	end
    if exists(select * from �����ͨ where city=@city and Yeardate=@Yeardate)
    begin
        set @railtolcons = (select elecons from �����ͨ where city=@city and Yeardate=@Yeardate)
        set @railtolemi = (select carbonemi from �����ͨ where city=@city and Yeardate=@Yeardate)
        set @tolcons += @railtolcons
        set @tolemi += @railtolemi
    end
    if exists(select * from �������ϼ� where city=@city and Yeardate=@Yeardate)
    begin
        set @bustolcons = (select energycons from �������ϼ� where city=@city and Yeardate=@Yeardate)
        set @bustolemi = (select carbonemi from �������ϼ� where city=@city and Yeardate=@Yeardate)
        set @tolcons += @bustolcons
        set @tolemi += @bustolemi
    end 
   
     if exists (select * from �ܺĺϼ� where city=@city and Yeardate=@Yeardate)
        update �ܺĺϼ� set totalenergycons=round(@tolcons,2), totalcarbonemi=round(@tolemi,2) where city=@city and Yeardate=@Yeardate
     else insert into �ܺĺϼ� values(@Yeardate,@city,round(@tolcons,2),round(@tolemi,2),null,null,null,null,null)

end
go