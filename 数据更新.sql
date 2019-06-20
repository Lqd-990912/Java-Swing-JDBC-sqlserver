/* 插入数据 */
--插入单个记录
--例1：将一个新学生记录
--（学号：4807505；姓名：陈好；性别：女；年龄：20；所在系部：计算机学院；班级：04电教1）插入Student表中
INSERT INTO Student
VALUES('4807505','陈好','女',20,'计算机学院','04电教1');

--插入多行记录
--例2 对每个Dept求学生的平均年龄，并将结果存入数据库中。
CREATE TABLE DeptAge
(
    Dept char(20),
    AvgAge DECIMAL
);

INSERT INTO DeptAge
SELECT Dept,AVG(Age)
FROM Student
GROUP BY Dept;


/* 数据修改 */
--例3：将SN为2809502所在的Dept改为“计算机学院”。
UPDATE Student
SET Dept = '计算机学院'
WHERE SNo = 2809502;


--例4：将所有学生的年龄增加1岁。
UPDATE Student
SET Age += 1;

--例5：将计算机学院全体学生的成绩置0。
UPDATE Scores
SET Grade = 0
WHERE SNo IN(
                SELECT SNo
                FROM Student
                WHERE Dept = '计算机学院'
            );


/* 数据删除 */
--删除一个记录
--例6：删除SN为4809503的学生记录。
DELETE 
FROM Student
WHERE SNo = '4809503';

--删除多个记录
--例7：删除所有的学生选课成绩。
DELETE
FROM Scores;


--例8：删除计算机学院所有学生的选课成绩记录。
DELETE
FROM Scores
WHERE SNo IN(
                SELECT SNo
                FROM Student
                WHERE Dept = '计算机学院'
            );


