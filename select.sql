CREATE FUNCTION demo2 (@className varchar(50))
    RETURNS @table TABLE (
	    className nvarchar(50),
	    Name nvarchar(50),
	    interest nvarchar(50)
	  )
AS
BEGIN

 --定义表变量，用于存放临时数据
 DECLARE @table2 TABLE 
 (
    className nvarchar(50),
	Name nvarchar(50),
	interest nvarchar(50)
 )

 INSERT INTO @table2   
  select className,Name,interest from Class where age>6  --查询班级信息中年龄大于6的学生，存放到表变量中

  INSERT INTO @table   
  select className,Name,interest from @table2 where className=@className  --读取表变量中的数据（将表变量当成表格使用）
	 
RETURN
END