CREATE FUNCTION demo2 (@className varchar(50))
    RETURNS @table TABLE (
	    className nvarchar(50),
	    Name nvarchar(50),
	    interest nvarchar(50)
	  )
AS
BEGIN

 --�������������ڴ����ʱ����
 DECLARE @table2 TABLE 
 (
    className nvarchar(50),
	Name nvarchar(50),
	interest nvarchar(50)
 )

 INSERT INTO @table2   
  select className,Name,interest from Class where age>6  --��ѯ�༶��Ϣ���������6��ѧ������ŵ��������

  INSERT INTO @table   
  select className,Name,interest from @table2 where className=@className  --��ȡ������е����ݣ�����������ɱ��ʹ�ã�
	 
RETURN
END