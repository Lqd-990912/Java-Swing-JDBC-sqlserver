/* 创建三个表 */
CREATE TABLE Student
(
    SNo char(8) PRIMARY KEY,
    SName char(8) NOT NULL,
    Sex char(2) CHECK(Sex IN('男','女')),
    Age SMALLINT DEFAULT 20,
    Dept char(20),
    Class char(20)
);

CREATE TABLE Course
(
    CNo char(5) PRIMARY KEY,
    CName char(20),
    Teacher char(20),
);

CREATE TABLE Scores
(
    SNo char(8) FOREIGN KEY(SNo) REFERENCES Student(SNo),
    CNo char(5) FOREIGN KEY(CNo) REFERENCES Course(CNo),
    Grade SMALLINT CHECK(Grade >= 0 AND Grade <= 100)
    CONSTRAINT PK_Scores PRIMARY KEY(SNo,CNo)
);


/* 修改基本表 */
--先为Student表增加Address列，然后删除Student表中Address列
ALTER TABLE Student ADD Address CHAR(30);

DROP TABLE Student DROP Address;


 
/* 向三个表中插入数据 */
INSERT INTO Student
VALUES
('2806501','张华','男',20,'电信学院','02信息1'),
('2806502','钱红','女',19,'电信学院','02信息2'),
('4807501','张丽','女',20,'计算机学院','04电教2'),
('4807502','张红芳','女',20,'计算机学院','04电教1'),
('4809501','张三','男',20,'计算机学院','04信息2'),
('4809502','李四','男',19,'计算机学院','04信息1');

INSERT INTO Course
VALUES
('C0001','数据库原理与应用','常老师'),
('C0002','信息系统分析与设计','钱老师'),
('C0003','Linux操作系统','郭老师');

INSERT INTO Scores
VALUES
('2806501','C0001','70'),
('2806502','C0001','90'),
('4807501','C0001','96'),
('4807502','C0002','90'),
('4807502','C0003','90'),
('4809501','C0001','90'),
('4809501','C0002','87'),
('4809502','C0001','95'),
('4809502','C0002','98');


/* 为三个表都创建一个索引 */
--为表Student的SNo创建一个唯一值索引(默认升序)
CREATE UNIQUE INDEX Stusno ON Student(SNo);

--为表Course的CNo创建一个唯一值索引(默认升序)
CREATE UNIQUE INDEX Coucno ON Course(CNo);

--为表Scores的SNo和CNo创建一个唯一值索引(SNo升序、CNo降序)(默认升序)
CREATE UNIQUE INDEX SC ON Scores(SNo ASC,CNo DESC);

/* 删除索引(指定表名) */
DROP INDEX Student.Stusno;

DROP INDEX Course.Coucno;

DROP INDEX Scores.SC;



