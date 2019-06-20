/* 创建视图 */
--创建计算机学院所有学生的视图
CREATE VIEW CS_VIEW
AS
SELECT SNo '学号',SName '姓名',Sex '性别',Age '年龄',Class '班级'
FROM Student
WHERE Dept = '计算机学院';

--WITH CHECK OPTION的用法。建立所有计算机学院学生的视图，并要求进行修改和插入操作时仍须保证该视图只有计算机学院的学生
CREATE VIEW CS_VIEW_V1
AS
SELECT SNo '学号',SName '姓名',Sex '性别',Age '年龄',Class '班级'
FROM Student
WHERE Dept = '计算机学院'
WITH CHECK OPTION;


/* 查询视图 */
--建立视图后，就可以把它作为基本表来进行查询操作。
SELECT *
FROM CS_VIEW_V1;


/* 删除视图 */
--删除一个视图 (建立在该视图之上的视图虽然存在，将会失效)
DROP VIEW CS_VIEW;
