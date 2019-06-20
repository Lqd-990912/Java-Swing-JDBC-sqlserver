/* 单表查询 */
--1.基本查询语句
--例1：查询Student表的部分属性：SN、SName
SELECT SNo,SName
FROM Student;

--例2：查询Student表的全部属性
SELECT *
FROM Student;

--2.定义显示标题
--例 3：查询 Student 表的所有信息，SN 用“学号”，SName 用“姓名”，Sex 用“性别”，
--Age 用“年龄”，Dept 用“系部”，Class 用“班级”来显示
SELECT SNo '学号',SName '姓名',Sex '性别',Age '年龄',Dept '系部',Class '班级'
FROM Student;

--3.避免出现重复数据
--例 4 ：查询 Student 表中包含哪些系部
SELECT DISTINCT Dept
FROM Student;

--4.设置查询条件
--例 5：查询所有计算机学院学生的所有信息。
SELECT *
FROM Student
WHERE Dept = '计算机学院';

--例 6 查询所有姓“张”的并且是计算机学院的学生的 SN、SName。
SELECT SNo,SName
FROM Student
WHERE SName LIKE '张%' AND Dept = '计算机学院';

--例 7 查询所有姓“张”名为一个字并且是计算机学院的学生的 SN、SName。
SELECT SNo,SName
FROM Student
WHERE SName LIKE '张_' AND Dept = '计算机学院';

--例 8 查询 SN 中前面 4 个字符为“480”，第 5 个字符为“7”或“9”。
SELECT SNo,SName
FROM Student
WHERE SNo LIKE '480[79]%';

--例 9 查询 SName 中不是姓“张”或姓“李”的学生。
SELECT SNo,SName
FROM Student
WHERE SName LIKE '[^张李]%';

--5.对查询结果排序
--例 10：查询所有选修 C0001 课程的学生的全部信息，查询结果按成绩降序排列。
SELECT *
FROM Scores
WHERE CNo = 'C0001'
ORDER BY Grade DESC; 

--6.聚集函数
--例 11：计算选修 C0001 课程的学生平均成绩。
SELECT AVG(Grade) 'C0001的平均成绩'
FROM Scores
WHERE CNo = 'C0001';

--7.分组查询
--例 12：查询与各个课程号相对应的选课人数。
SELECT CNO,COUNT(*) '选课人数'
FROM Scores
GROUP BY CNo;

--例 13 查询选修某门课程 2 人以上的课程号和选课人数。
SELECT CNO,COUNT(*) '选课人数'
FROM Scores
GROUP BY CNo 
HAVING COUNT(*) >= 2;

/* 多表查询 */
--1.等值与非等值连接查询

--例14 查询每个学生的SN、SName及其选修的CN、Grade。
SELECT Student.SNo,SName,Scores.Grade
FROM Student,Scores
WHERE Student.SNo = Scores.SNo;


--2.自身连接
--例15在Scores表中，查询课程号为C0001和C0002的考试成绩，要求查询同一个学生两门课程的成绩。
SELECT S1.SNo '学号',S1.Grade 'C0001成绩',S2.Grade 'C0002成绩'
FROM Scores S1,Scores S2
WHERE S1.SNo = S2.SNo AND S1.CNo = 'C0001' AND S2.CNo = 'C0002';


--3.外连接查询
--例16 以Student表为主体列出每个学生的SN、SName、CN及Grade。
SELECT Student.SNo,Student.SName,Scores.CNo,Scores.Grade
FROM Student LEFT JOIN Scores --如果Scores中没有Student中对应的SNo,左外连接仍然会输出SNo,只是Cno\Grade都是NULL
ON Student.SNo = Scores.SNo

--也可以这样写
SELECT Student.Sno,Student.SName,Scores.Cno,Scores.Grade
FROM Student,Scores
WHERE Student.Sno = Scores.Sno;


--例17 以Scores表为主体列出每个学生的SN、SName、CN及Grade。
SELECT Student.SNo,Student.SName,Scores.CNo,Scores.Grade
FROM Student RIGHT JOIN Scores --同上的条件，右外连接只会输出选了课的学生
ON Student.SNo = Scores.SNo


--4.复合条件查询
--例18 查询CN为C0001并且Grade75以上的所有学生的SN、SName、Dept和Grade。
SELECT Student.SNo,SName,Dept,Scores.Grade
FROM Student,Scores
WHERE Student.SNo = Scores.SNo AND Scores.CNo = 'C0001' AND Scores.Grade > 75;


--例19 查询每个学生的SN、SName及其选修的CName及其Grade。
SELECT Student.SNo,SName,Course.CName,Scores.Grade
FROM Student,Scores,Course
WHERE Student.SNo = Scores.SNo AND Scores.CNo = Course.CNo;



/* 嵌套查询 */
--例20  查询与“张三”在同一个Dept的学生的SN和SName。 (用 IN )
SELECT Sno,SName
FROM Student
WHERE Dept IN (
                SELECT Dept
                FROM Student
                WHERE SName = '张三'
            );

--例21  查询与“张三”在同一个Dept的学生的SN和SName。 (用 = )
SELECT Sno,SName
FROM Student
WHERE Dept= (
                SELECT Dept
                FROM Student
                WHERE SName = '张三'
            );


--例22 查询其他Dept中比“计算机学院”任一(注意不是任意)学生年龄小的学生SN、SName、Age。
SELECT SNo,SName,Age
FROM Student
WHERE Dept != '计算机学院' AND Age < ANY(
                                            SELECT Age
                                            FROM Student
                                            WHERE Dept = '计算机学院'
                                        );


--例23 查询其他Dept中比“计算机学院”所有学生年龄小的学生SN、SName、Age。
SELECT SNo,SName,Age
FROM Student
WHERE Dept != '计算机学院' AND Age < ALL(
                                            SELECT Age
                                            FROM Student
                                            WHERE Dept = '计算机学院'
                                        );


--带有EXISTS谓词的子查询 (相关子查询)
--例24 查询所有选修了CN为C0001的学生SN、SName。
SELECT Student.SNo,SName
FROM Student
WHERE EXISTS -- 根据EXISTS的结果，逐行判断Student的每一个元组看是不是存在 在下面的查询结果中
(
    SELECT *
    FROM Scores
    WHERE Student.SNo = Scores.SNo AND Scores.CNo = 'C0001'
);

--也可以这样1
SELECT Student.SNo,SName
FROM Student
WHERE SNo IN -- 也是逐行判断看满不满足下面的要求
(
    SELECT SNo
    FROM Scores
    WHERE Scores.CNo = 'C0001'
);

--也可以这样2
SELECT Student.Sno,SName
FROM Student,Scores
WHERE Student.SNo = Scores.SNo AND Scores.CNo = 'C0001';


--例25查询至少选修了SN为4809501学生选修的全部课程的学生SN、SName。
--至少选修了学生p选修的全部课程的学生 <=> 不存在学生p选修了的课程，该学生没有选修
SELECT DISTINCT Student.Sno,Student.SName
FROM Scores S1,Student
WHERE Student.SNo = S1.SNo AND NOT EXISTS(
                    SELECT *
                    FROM Scores S2
                    WHERE S2.SNo = '4809501' 
                    AND
                    NOT EXISTS
                    (
                        SELECT *
                        FROM Scores S3
                        WHERE S3.SNo = S1.SNo AND S3.CNo = S2.CNo
                    )
                );


--集合查询: INTERSECT-交 UNION-并 MINUS-差()
--例26 查询计算机学院的学生及Age不大于20岁的学生的并集。
SELECT *
FROM Student
WHERE Dept = '计算机学院'
UNION
SELECT *
FROM Student
WHERE Age <= 20;


--例27 查询计算机学院的学生与Age小于21岁的学生记录的交集。
SELECT *
FROM Student
WHERE Dept = '计算机学院'
INTERSECT
SELECT *
FROM Student
WHERE Age < 21;


--例28查询选修课程为C0001的学生与选修课程为C0002的学生的差集。
--相当于就是选择出只选修了C0001的学生
SELECT Sno
FROM Scores
WHERE CNo = 'C0001';

SELECT S1.SNo
FROM Scores S1,Scores S2
WHERE S1.SNo = S2.SNo AND S1.Cno = 'C0001' AND S2.Cno = 'C0002';

SELECT SNo
FROM Scores
WHERE Scores.CNo = 'C0001' AND SNo NOT IN
                                        (
                                            SELECT S1.SNo
                                            FROM Scores S1,Scores S2
                                            WHERE S1.SNo = S2.SNo AND S1.Cno = 'C0001' AND S2.Cno = 'C0002'
                                        );

