/**
    存储过程就是SQL Server为了实现特定任务，而将一些需要多次调用的固定操作语句编写成程序段，这些程序段存储在服务器上，有数据库服务器通过程序来调用。
  */

/* 创建存储过程 
--在查询分析器中，输入根据SN进行查询的存储过程语句
GO
USE student
IF EXISTS(
            SELECT NAME FROM SYSOBJECTS
            WHERE NAME = 'StuQuery_Proc' AND = 'P'
         )
        DROP PROCEDURE StuQuery_Proc
GO
CREATE PROCEDURE StuQuery_Proc
@SNo VARCHAR(8) = NULL
AS --向该存储过程输入一个Sno,然后进入下去的操作
IF @SNo IS NULL
    SELECT 'Please Input SNo!'
ELSE
    SELECT *
    FROM Student
    WHERE SNo = @SNo;
*/

/* 测试存储过程 
--不带参数
use student
EXEC StuQuery_Proc;

--带参数
use student
EXEC StuQuery_Proc '2806501';
*/

/* 修改存储过程 
ALTER PROCEDURE StuQuery_Proc
@SName VARCHAR(8) = NULL
AS
IF @SName IS NULL
    SELECT 'Please Input SName!'
ELSE
    SELECT *
    FROM Student
    WHERE SName = @SName
*/


/* 继续测试修改后的 
--不带参数
use student
EXEC StuQuery_Proc;

--带参数
use student
EXEC StuQuery_Proc '张华';
*/

/* 在创建一个无参的存储过程，调用就直接打印出三个表的所有信息 
GO
IF EXISTS(
            SELECT NAME FROM SYSOBJECTS
            WHERE NAME = 'PrintAllTableInfo' AND TYPE = 'P'
        )
        DROP PROCEDURE PrintAllTableInfo
GO
CREATE PROCEDURE PrintAllTableInfo
@TAG VARCHAR(8) = NULL
AS
IF @TAG = 'No'
    SELECT 'Please Input Yes' 
ELSE
    SELECT *
    FROM Student
    SELECT *
    FROM Course
    SELECT *
    FROM Scores

--测试存储过程
EXEC PrintAllTableInfo 'Yes';
EXEC PrintAllTableInfo 'No'; --为啥输入No下面的也执行了
*/

/* 查看存储过程 
--使用SP_HELPTEXT系统存储过程(位于master数据库)查看存储过程文本信息
EXEC SP_HELPTEXT StuQuery_Proc;

--使用SP_HELP系统存储过程查看存储过程一般信息
EXEC SP_HELP StuQuery_Proc;
*/

/* 删除存储过程 
DROP PROCEDURE StuQuery_Proc
*/

