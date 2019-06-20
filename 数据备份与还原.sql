--例1：备份“student”数据库到“D:\数据库\数据库实验\Shcool-Lab\数据库备份”文件中
GO
BACKUP DATABASE student
TO DISK = 'D:\数据库\数据库实验\Shcool-Lab\数据库备份\student.bak';
GO

--例2 利用“D:\databasebak”文件还原student数据库(删除了就不能打开student数据库下的sql文件了，需要在dbms中打开服务器下的sql文件)
GO
RESTORE DATABASE Student
FROM DISK = 'D:\数据库\数据库实验\Shcool-Lab\数据库备份\student.bak';
GO
