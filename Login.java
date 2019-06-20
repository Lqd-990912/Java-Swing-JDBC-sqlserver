package JavaGUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *	用于实现数据库登录的类
 * */

public class Login {

	public String zh,mm;

	public Login() {
        
		//创建登录界面欢迎标签
		JLabel LoginLabel = new JLabel("数据库登录界面");
		LoginLabel.setFont(new Font("微软雅黑",1,20)); 
		LoginLabel.setBounds(140, 20,200, 20);//(x,y,width,height)
		Main.panel.add(LoginLabel);
		
		//放一个图片在欢迎标签下面
		JLabel LoginPhoto = new JLabel(new ImageIcon("src/JavaGUI/登录界面1.png"));
		LoginPhoto.setBounds(10,40,80,80);
		Main.panel.add(LoginPhoto);
		
		//创建输入账号和密码的标签\文本域\按钮
		JLabel ZHLabel = new JLabel("账号: ");
		ZHLabel.setBounds(100, 60, 80, 25);
		Main.panel.add(ZHLabel);
		// 创建文本域用于输入账号
		JTextField ZHText = new JTextField(20);
		ZHText.setBounds(150, 60, 100, 25);
		Main.panel.add(ZHText);
		// 创建确认输入账号的按钮
		JButton ZHButton = new JButton("确认");
		ZHButton.setBounds(260, 60, 70, 25);
		Main.panel.add(ZHButton);
		//为按钮添加一个点击事件(如果点击按钮就会执行对应的actionPerformed()方法
		ActionListener al1 = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{					
				//把账号文本域中的字符串赋值给zh
				String inputZH = ZHText.getText();
				zh = inputZH;
				System.out.println("账号为: "+zh);
			}
		};
		ZHButton.addActionListener(al1);
		
		//创建输入账号和密码的标签\文本域\按钮
		JLabel MMLabel = new JLabel("密码: ");
		MMLabel.setBounds(100, 90, 80, 25);
		Main.panel.add(MMLabel);
		// 创建密码框用于输入密码
		JPasswordField MMText = new JPasswordField(20);
		MMText.setBounds(150, 90, 100, 25);
		Main.panel.add(MMText);
		// 创建确认输入密码的按钮
		JButton MMButton = new JButton("确认");
		MMButton.setBounds(260, 90, 70, 25);
		Main.panel.add(MMButton);
		//为按钮添加一个点击事件(如果点击按钮就会执行对应的actionPerformed()方法
		ActionListener al2 = new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{					
				//把账号文本域中的字符串赋值给mm
				char[] inputMM = MMText.getPassword(); // 问题1：怎么获取密码框字符串
				mm = String.valueOf(inputMM);;
				
				System.out.println("密码为: "+mm);
				
				//当输入完账号和密码之后开始检查登录是否成功
				if(!Check())
				{
					//登录失败就直接退出了
					System.exit(-1);
				}
				else 
				{
					//登录成功需要: 1.关闭登录窗口 2.打开执行对数据库操作的窗口

//					Main.frameOP = new JFrame("学生信息管理数据库系统");
//					Main.frameOP.setSize(480,500);
//		            
//					Main.panelSearch = new JPanel();
//					Main.frameOP.add(Main.panelSearch);
//					Main.panelSearch.setLayout(null);
//		    		
//					Main.frameOP.setVisible(true);
					
//		            查询功能: 登录完成以后直接在新窗口的panelSearch面板进行查询操作
					
					//直接在登录面板上进行查询操作
		            Search search = new Search(); //new一个Search类执行查询操作
		            
		            
				}
			}
		};
		MMButton.addActionListener(al2);
		
	}

	//检查登录是否成功
	public boolean Check()
	{
        try 
        {
        	Main.con = DriverManager.getConnection(Main.url,zh,mm);
        	System.out.println("登录成功！");
        	
        	//bug：打印不出来这个标签信息
    		JLabel LoginTrueLabel = new JLabel(" 登录成功！ ");
    		LoginTrueLabel.setFont(new Font("微软雅黑",1,15)); 
    		LoginTrueLabel.setBounds(100,120, 100, 25);
    		Main.panel.add(LoginTrueLabel);
        	return true;
		} 
        catch (Exception e) 
        {
			System.out.println("账号密码输入错误，即将退出系统!");
    		JLabel LoginFlaseLabel_1 = new JLabel(" 账号密码输入错误，即将退出系统! ");
    		LoginFlaseLabel_1.setFont(new Font("微软雅黑",1,15)); 
    		LoginFlaseLabel_1.setBounds(10, 120, 300, 25);
    		Main.panel.add(LoginFlaseLabel_1);	
			try 
			{
				Thread.sleep(3000);
			} 
			catch (InterruptedException e1) 
			{
				e1.printStackTrace();
			}
//			System.out.println("已退出系统！");
			JLabel LoginFlaseLabel_2 = new JLabel(" 已退出系统！");
			LoginFlaseLabel_2.setBounds(10, 130, 100, 25);
			LoginFlaseLabel_2.setFont(new Font("微软雅黑",1,15)); 
    		Main.panel.add(LoginFlaseLabel_2);
			return false;
		}	
	}
}
