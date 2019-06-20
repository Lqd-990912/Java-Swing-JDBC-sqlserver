/** 创建数据库 
CREATE DATABASE NewStudentDB
ON
(
    NAME = 'NewStudentDB', --主数据文件逻辑名
    FILENAME = 'D:\数据库\数据库实验\Shcool-Lab\Student-新建数据库\NewStudentDB.mdf',
    SIZE = 1, -- 初始大小
    MAXSIZE = 50, -- 最大大小
    FILEGROWTH = 1 -- 每次增长的大小
)
LOG ON --如果不指明下面的日志文件，也会默认有，但是一些属性都是和主数据库文件一样
(
    NAME = 'NewStudentDB_Log', -- 事务日志文件逻辑名
    FILENAME = 'D:\数据库\数据库实验\Shcool-Lab\Student-新建数据库\NewStudentDB.ldf',
    SIZE = 2, -- 初始大小
    MAXSIZE = 100, -- 最大大小
    FILEGROWTH = 20% -- 每次增长的比例
)
*/

/** 修改数据库 
ALTER DATABASE NewStudentDB
ADD FILE --给数据库再添加一个次数据库文件
(
    NAME = NewStudentDB_2, -- 事务日志文件逻辑名
    FILENAME = 'D:\数据库\数据库实验\Shcool-Lab\Student-新建数据库\NewStudentDB.ndf',
    SIZE = 1MB,
    FILEGROWTH = 10%
)
*/


/** 删除数据库 
DROP DATABASE NewStudentDB;*/