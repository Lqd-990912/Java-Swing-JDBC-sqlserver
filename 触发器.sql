/* 创建触发器 */
--先检查触发器是否存在，如果存在先删除，再重建
IF EXISTS(SELECT NAME FROM SYSOBJECTS WHERE NAME = 'StuInsert_Tr' AND TYPE = 'TR')
    DROP TRIGGER StuInsert_Tr;
GO
CREATE TRIGGER StuInsert_Tr 
ON Student 
FOR INSERT --触发事件
AS 
SELECT 'Please Check Student INSERT!' --执行事件
SELECT *
FROM Student
GO


/* 测试触发器，向表中插入数据 */
INSERT INTO Student
VALUES('4807504','王五','男',20,'计算机学院','04电教1');


/* 查看触发器 */
--查看数据库中的所有触发器
use student
GO
SELECT *
FROM sysobjects --sysobjects保存着数据库的对象
WHERE xtype = 'TR';--xtype为TR的记录即为触发器对象

--查看触发器内容
use student
GO
EXEC sp_helptext 'StuInsert_Tr';--sp_helptext会以表的形式显示内容(触发器、规则、默认值、未加密的存储过程、用户定义函数、视图文本)

--查看触发器的属性
use student
GO
EXEC sp_helptrigger Student;
--专门查看对Student表的Insert触发器
EXEC sp_helptrigger Student,[INSERT];


/* 修改触发器 */
GO
ALTER TRIGGER StuInsert_Tr 
ON Student 
FOR INSERT --触发事件
AS 
SELECT 'Please Check Student INSERT!' --执行事件
GO

use student
GO
EXEC sp_helptext 'StuInsert_Tr';


/* 禁用\启用触发器 */
ALTER TABLE Student disable TRIGGER StuInsert_Tr;
ALTER TABLE Student enable TRIGGER StuInsert_Tr;


/* 删除触发器 */
DROP TRIGGER StuInsert_Tr;
