/* 安全性管理 
GO
--例1： 为用户student123创建一个SQL SERVER登录名，密码为123，默认数据库为student。
EXEC sp_addlogin 'student123','123','student';
GO
--也可以这样写: CREATE LOGIN student123 WITH PASSWORD = '123'

--例2：在student数据库中为SQL SERVERY用户账号student123添加数据库用户名，并取名为123。
GO
EXEC sp_grantdbaccess 'student123','123'
GO

--例3：通过对象授予、撒消或禁止对象权限

--例4 要对数据库student中的“db_Learning”角色进行授权操作。

--例5 将创建表的权限授予用户student和123
GRANT CREATE TABLE TO student,[123]

--例6 对象授权：授予角色public对表Scores的select权限，授予用户123对表Course的insert和delete的权限。
GRANT SELECT ON Scores TO public
GRANT INSERT,DELETE ON Course TO [123]

--例7 将对表Scores的属性“Grade”修改权限授予用户123
GRANT UPDATE(Grade) ON Scores TO [123]

--例8 禁止用户123的CREATE TABLE 语句权限
DENY CREATE TABLE TO [123]

--例9 禁止用户123对表Course的DELETE权限
DENY DELETE ON Course TO [123]

--例10 撤消用户123的CREATE TABLE 语句权限
REVOKE CREATE TABLE TO [123]


--例11 撤消用户123对表Course的DELETE权限
REVOKE DELETE ON Course TO [123]

--创建角色，赋予角色权限
USE student
CREATE ROLE role_123;
GRANT ALL ON Student TO role_123;

GRANT db_learning TO [123] WITH GRANT OPTION;--为什么出现语法错误*/

GRANT role_123 TO db_learning;
