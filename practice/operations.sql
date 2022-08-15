CREATE proc calbussum(@city char(50),@Yeardate int)
as begin
    declare @tolenergycons double precision = 0;
    declare @totalcarbonemi double precision;
	declare @elecons double precision;
	declare @equelec1 double precision;
	declare @equelec2 double precision;
	declare @equelec3 double precision;

    set @totalcarbonemi=(select sum(carbonemi) from 公交车 where city=@city and Yeardate=@Yeardate)
    if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
    begin
        set @equelec1=(select equelec from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
        set @tolenergycons += @equelec1
    end
    if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='混合动力车')
    begin
	    set @equelec2=(select equelec from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='混合动力车')
        set @tolenergycons += @equelec2
	end
    if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='天然气车')
    begin
	    set @equelec3=(select equelec from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='天然气车')
        set @tolenergycons += @equelec3
    end
    if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
    begin
	    set @elecons=(select elecons from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
        set @tolenergycons += @elecons
    end
   
	if exists (select * from 公交车合计 where city=@city and Yeardate=@Yeardate)
        update 公交车合计 set energycons=round(@tolenergycons,2), carbonemi=round(@totalcarbonemi,2) where city=@city and Yeardate=@Yeardate
	else insert into 公交车合计 values(@Yeardate,@city,round(@tolenergycons,2),round(@totalcarbonemi,2))
end
go

CREATE proc calsocsum(@city char(50),@Yeardate int)
as begin
    declare @tolenergycons double precision = 0;
    declare @totalcarbonemi double precision = 0;
	declare @elecons double precision;
	declare @equelec double precision;

    set @totalcarbonemi=(select sum(carbonemi) from 社会车辆 where city=@city and Yeardate=@Yeardate)
    if exists(select * from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
    begin
        set @equelec=(select equelec from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
        set @tolenergycons = @tolenergycons+@equelec
    end
    if exists(select * from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
    begin
        set @elecons=(select elecons from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
        set @tolenergycons = @tolenergycons+@elecons
    end
	if exists (select * from 社会车辆合计 where city=@city and Yeardate=@Yeardate)
		update 社会车辆合计 set energycons=round(@tolenergycons,2), carbonemi=round(@totalcarbonemi,2) where city=@city and Yeardate=@Yeardate
	else insert into 社会车辆合计 values(@Yeardate,@city,round(@tolenergycons,2),round(@totalcarbonemi,2))
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
        for select carbonemi from 出租车 where city=@city and Yeardate=@Yeardate
    open my_cursor
    fetch next from my_cursor into @carbonemi
    while @@FETCH_STATUS=0
        begin
            set @totalcarbonemi += @carbonemi
            fetch next from my_cursor into @carbonemi
        end
    close my_cursor
    deallocate my_cursor
    if exists(select * from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
    begin
        set @equelec=(select equelec from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
        set @tolenergycons += @equelec
    end
    if exists(select * from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
    begin
        set @elecons=(select elecons from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
        set @tolenergycons += @elecons
    end
	if exists (select * from 出租车合计 where city=@city and Yeardate=@Yeardate)
			update 出租车合计 set energycons=round(@tolenergycons,2), carbonemi=round(@totalcarbonemi,2) where city=@city and Yeardate=@Yeardate
	else insert into 出租车合计 values(@Yeardate,@city,round(@tolenergycons,2),round(@totalcarbonemi,2))
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
	if exists(select * from 能耗合计 where city=@city and Yeardate=@Yeardate)
	begin
	     set @tolcons = (select totalenergycons from 能耗合计 where city=@city and Yeardate=@Yeardate)
         set @tolemi = (select totalcarbonemi from 能耗合计 where city=@city and Yeardate=@Yeardate)
         set @traencons = @tolcons-@phvoldis-@garburndis
    end
	if exists(select * from 国家电网碳排放因子 where city=@city and Yeardate=@Yeardate)
	begin
	     set @caremipar = (select caremipar from 国家电网碳排放因子 where city=@city and Yeardate=@Yeardate)
         set @renewendis = (@phvoldis+@garburndis)*@caremipar/1000
         set @tracarboncons = @tolemi-@renewendis
	end
	if(@phvoldis>0 and @garburndis>0)
        update 能耗合计 set phvoldis=round(@phvoldis,2),garburndis=round(@garburndis,2),renewendis=round(@renewendis,2),
                        traencons=round(@traencons,2),tracarboncons=round(@tracarboncons,2) where city=@city and Yeardate=@Yeardate
    else update 能耗合计 set traencons=round(@traencons,2),tracarboncons=round(@tracarboncons,2) where city=@city and Yeardate=@Yeardate
end
go

CREATE proc statistic(@city char(50),@Yeardate int)
as begin
	declare @gascons double precision = 0;   /*汽油消耗量*/
	declare @equelec double precision = 0;   /*折算电力*/
	declare @elecons double precision = 0;    /*电耗量*/
	declare @socgascons double precision; declare @socequelec double precision; declare @socelec double precision;
	declare @taxigascons double precision;declare @taxiequelec double precision;declare @taxielec double precision;
	declare @busgascons double precision; declare @busequelec double precision; declare @buselec double precision;
	declare @railelec double precision;   declare @busmixgascons double precision; declare @busmixequelec double precision;

	if exists(select * from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
	begin
	      set @socgascons=(select gascons from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
	      set @socequelec=(select equelec from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
          set @equelec += @socequelec
          set @gascons += @socgascons
	end
	if exists(select * from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
	begin
	    set @socelec=(select elecons from 社会车辆 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
        set @elecons += @socelec
	end
	if exists(select * from 轨道交通 where city=@city and Yeardate=@Yeardate)
	begin
	    set @railelec=(select elecons from 轨道交通 where city=@city and Yeardate=@Yeardate)
        set @elecons += @railelec
   end
	if exists(select * from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
	begin
	    set @taxigascons=(select gascons from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
	    set @taxiequelec=(select equelec from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
        set @equelec += @taxiequelec
        set @gascons += @taxigascons
	end
	if exists(select * from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
	begin
	    set @taxielec=(select elecons from 出租车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
        set @elecons += @taxielec
	end
	if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
	begin
	    set @busgascons=(select gascons from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
	    set @busequelec=(select equelec from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='燃油车')
        set @equelec += @busequelec
        set @gascons += @busgascons
	end
	if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
	begin
	    set @buselec=(select elecons from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='电动车')
        set @elecons += @buselec
	end
    if exists(select * from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='混合动力车')
	begin
	      set @busmixgascons=(select gascons from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='混合动力车')
	      set @busmixequelec=(select equelec from 公交车 where city=@city and Yeardate=@Yeardate and vehtype='混合动力车')
          set @equelec += @busmixequelec
          set @gascons += @busmixgascons
    end
	if exists (select * from 统计 where city=@city and Yeardate=@Yeardate)
        update 统计 set gascons=round(@gascons,2), equelec=round(@equelec,2),elecons=round(@elecons,2) where city=@city and Yeardate=@Yeardate
	else insert into 统计 values(@Yeardate,@city,round(@gascons,2),round(@equelec,2),round(@elecons,2))
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
   if exists(select * from 社会车辆合计 where city=@city and Yeardate=@Yeardate)
    begin
        set @soctolcons = (select energycons from 社会车辆合计 where city=@city and Yeardate=@Yeardate)
	    set @soctolemi = (select carbonemi from 社会车辆合计 where city=@city and Yeardate=@Yeardate)
        set @tolcons += @soctolcons
        set @tolemi += @soctolemi
    end 
    if exists(select * from 出租车合计 where city=@city and Yeardate=@Yeardate)
    begin
	    set @taxitolcons = (select energycons from 出租车合计 where city=@city and Yeardate=@Yeardate)
	    set @taxitolemi = (select carbonemi from 出租车合计 where city=@city and Yeardate=@Yeardate)
        set @tolcons += @taxitolcons
        set @tolemi += @taxitolemi
	end
    if exists(select * from 轨道交通 where city=@city and Yeardate=@Yeardate)
    begin
        set @railtolcons = (select elecons from 轨道交通 where city=@city and Yeardate=@Yeardate)
        set @railtolemi = (select carbonemi from 轨道交通 where city=@city and Yeardate=@Yeardate)
        set @tolcons += @railtolcons
        set @tolemi += @railtolemi
    end
    if exists(select * from 公交车合计 where city=@city and Yeardate=@Yeardate)
    begin
        set @bustolcons = (select energycons from 公交车合计 where city=@city and Yeardate=@Yeardate)
        set @bustolemi = (select carbonemi from 公交车合计 where city=@city and Yeardate=@Yeardate)
        set @tolcons += @bustolcons
        set @tolemi += @bustolemi
    end 
   
     if exists (select * from 能耗合计 where city=@city and Yeardate=@Yeardate)
        update 能耗合计 set totalenergycons=round(@tolcons,2), totalcarbonemi=round(@tolemi,2) where city=@city and Yeardate=@Yeardate
     else insert into 能耗合计 values(@Yeardate,@city,round(@tolcons,2),round(@tolemi,2),null,null,null,null,null)

end
go