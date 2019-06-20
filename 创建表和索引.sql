--Student表
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
*/

--向上面三个表插入数据                      
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

--兴趣爱好表
CREATE TABLE Hobby
(
    HNo char(8) NOT NULL,--爱好编号
    HName char(20) ,--爱好名称
);
 
INSERT INTO Hobby
VALUES
('1','健身'),
('2','篮球'),
('3','跑步'),
('4','唱歌'),
('5','刷知乎或者b站');

--学生的爱好情况表
CREATE TABLE StuHobby
(
    SNo char(8) FOREIGN KEY(SNo) REFERENCES Student(SNo),--学生编号
    HNo char(8) ,--爱好编号
    HLevel char(20) ,--对于爱好的擅长程度
);

INSERT INTO StuHobby
VALUES
('1','2','NBA普通球员'),
('2','2','CBA全明星球员'),
('2806501','1','业余'),
('2806502','2','业余'),
('4807501','3','业余'),
('4807502','4','业余'),
('4809501','4','业余'),
('4809502','5','业余');


--大学表
CREATE TABLE University
(
    UNo char(8) NOT NULL,--大学编号
    UName char(20) ,--大学名称
    Info char(20) ,--大学的基本描述信息
);

INSERT INTO University
VALUES
('1','清华大学','中国顶尖名校'),
('2','北京大学','中国顶尖名校'),
('3','上海交通大学','华东五校之一'),
('4','复旦大学','华东五校之一'),
('5','北京邮电大学','两电一邮的老大'),
('6','华中科技大学','华中地区工科强校'),
('7','中南大学','湖南最好大学'),
('8','湖南大学','千年学府'),
('9','南京航空航天大学','食堂好'),
('10','南京理工大学','男女比例惊人'),
('11','哈佛大学','美国顶尖名校'),
('12','麻省理工学院','世界顶尖工科名校');

--学习保研或者考研的情况表
CREATE TABLE Graduate
(
    SNo char(8) FOREIGN KEY(SNo) REFERENCES Student(SNo),--学生编号
    UNo char(8) ,--研究生院校编号
    Diff char(8) ,--看是保研还是考研
);

INSERT INTO Graduate
VALUES
('1','11','保研'),
('2','2','保研'),
('2806501','1','保研'),
('2806502','3','考研'),
('4807501','5','保研'),
('4807502','6','考研'),
('4807504','7','保研'),
('4807505','10','考研'),
('4809501','9','保研'),
('4809502','8','考研');
