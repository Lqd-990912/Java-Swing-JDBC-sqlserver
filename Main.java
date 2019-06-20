package JavaGUI;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *	JavaGUI包中都是写的图形界面版本
 * */

public class Main {
	final static String cfn = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Student";
    static Connection con = null;
     
    static JFrame frame = null;//登录窗口
    static JPanel panel = null;//登录面板
    
//    static JFrame frameOP = null;//操作窗口(包括了三个面板: 查询\更新\自定义操作)
//    static JPanel panelSearch = null;//查询面板
//    static JPanel panelUpdate = null;//更新面板
//    static JPanel panelDiy = null;//自定义操作面板
    
    public static void main(String[] args) {
        try                                                                                                                                         
        {
            Class.forName(cfn);
            
            //实例化一个窗口对象
            frame = new JFrame("学生信息管理数据库系统");
            frame.setSize(470,500);
            
    		//实例化出一个面板对象
    		panel = new JPanel();
    		//将面板添加到窗口里面
    		frame.add(panel);
    		panel.setLayout(null);
    		
            //将窗口设为可视化
    		frame.setVisible(true);
    		
            //登录功能: 输入账号密码
            Login login = new Login(); //new一个Login类执行登录操作
    		
            
//    		System.out.println("本数据库的基本表有: ");
//			System.out.println("学生基本信息表: Student");
//			System.out.println("课程信息表: Course");
//			System.out.println("学生成绩表: Scores");
//			System.out.println("爱好表: Hobby");
//			System.out.println("学生的爱好情况表: StuHobby");
//			System.out.println("大学信息表: University");
//			System.out.println("学生研究生录取情况表: Graduate");
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
            	if(con != null) con.close();
            } 
            catch (Exception e2) 
            {
                e2.printStackTrace();
            }
        }
    }

    //使用Robot类来模拟在控制台单击右键 + ctrl+r的清屏效果
    public static void clear() throws AWTException
    {
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R);                    // 按下R键
        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
        r.delay(100); 
    }
    
}
