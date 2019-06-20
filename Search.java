package JavaGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *	用于操作sql查询语句的类: 该类支持的查询只是全表查询
 * */

public class Search {

	PreparedStatement statement = null;
    ResultSet res = null;
	
	public Search()
	{
		try 
		{
//				创建请求查询的标签
//				JLabel AskLabel = new JLabel("数据库查询界面");
//				AskLabel.setBounds(160, 20,100, 20);//(x,y,width,height)
//				Main.panelSearch.add(AskLabel);
				
//	            System.out.println("请问是否要进行查询(yes/no)?");
				
				// 创建请求查询的标签
	    		JLabel AskLabel = new JLabel("请问是否要进行查询(yes/no)? ");
	    		AskLabel.setBounds(10,150, 200, 25);
	    		Main.panel.add(AskLabel);
	    		// 创建文本框
	    		JTextField Ask = new JTextField(20);
	    		Ask.setBounds(190, 150, 100, 25);
	    		Main.panel.add(Ask);
	    		// 创建确认输入的按钮
	    		JButton AskButton = new JButton("确认");
	    		AskButton.setBounds(300, 150, 70, 25);
	    		Main.panel.add(AskButton);
	    		//为按钮添加一个点击事件(如果点击按钮就会执行对应的actionPerformed()方法
	    		ActionListener al1 = new ActionListener() 
	    		{
	    			@Override
	    			public void actionPerformed(ActionEvent e) 
	    			{					
	    				String inputAsk = Ask.getText();
	    				if(inputAsk.equals("yes"))
	    				{
	    					//开始进行查询
	    					try 
	    					{
	    						System.out.println("开始进行查询");
//	    			            Scanner scan = new Scanner(System.in);
//	    			            System.out.println("你输入您要查询的表名:");
//	    			            String tableName = scan.nextLine();
	    						
	    			    		JLabel TableLabel = new JLabel("你输入您要查询的表名:");
	    			    		TableLabel.setBounds(10,180, 200, 25);
	    			    		Main.panel.add(TableLabel);
	    			    		// 创建文本框
	    			    		JTextField AskText = new JTextField(20);
	    			    		AskText.setBounds(190, 180, 100, 25);
	    			    		Main.panel.add(AskText);
	    			    		// 创建确认输入的按钮
	    			    		JButton AskButton = new JButton("确认");
	    			    		AskButton.setBounds(300, 180, 70, 25);
	    			    		Main.panel.add(AskButton);
	    			    		ActionListener al2 = new ActionListener() 
	    			    		{
	    			    			@Override
	    			    			public void actionPerformed(ActionEvent e) 
	    			    			{					
	    			    				String tableName = AskText.getText();
	    			    				System.out.println("要查询的表名: "+tableName);
	    			    				
	    			    				try 
	    			    				{
											String sql = "select *from "+tableName;//查询Student表
											statement = Main.con.prepareStatement(sql);
											res = statement.executeQuery();
											ShowTableInfo(tableName);
											
											System.out.println("查找成功！");
										
	    			    				}
	    			    				catch (Exception e2) 
	    			    				{
											System.out.println("查找失败！");
	    			    					
											e2.printStackTrace();
										}
	    			    			}
	    			    		};
	    			    		AskButton.addActionListener(al2);
	    			    		
	    			    		//查询以后开始更新操作
	    			    		Update update = new Update(); //new一个Update类执行更新操作
	    			    		
							} 
	    					catch (Exception e2) 
	    					{
								e2.printStackTrace();
							}
	    				}
	    				else 
	    				{
	    					//不查询就直接退出程序了
	    					System.exit(-1);
	    				}
	    			}
	    		};
	    		AskButton.addActionListener(al1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(statement != null) statement.close();
				if(res != null) res.close();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}

	}

	public void ShowTableInfo(String tableName)
	{
		try 
		{
			//查询主题 - 要放到gui中
			if(tableName.equals("Student"))
	        {
//	            System.out.println("Student:");
//	            System.out.println("学号\t\t姓名\t性别\t年龄\t系部\t\t班级");
				JTextField info1 = new JTextField("Student:");
				JTextField info2 = new JTextField("学号\t姓名\t性别\t年龄\t系部\t\t班级");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
				
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String Sno = res.getString("Sno");
	                String SName = res.getString("SName");
	                String Sex = res.getString("Sex");
	                String Age = res.getString("Age");
	                String Dept = res.getString("Dept");
	                String Class = res.getString("Class");
	                System.out.println(Sno+"\t"+SName+"\t"+Sex+"\t"+Age+"\t"+Dept+"\t"+Class);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(Sno+"\t"+SName+"\t"+Sex+"\t"+Age+"\t"+Dept+"\t"+Class);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	            System.out.println();
	        }
	        else if(tableName.equals("Course"))
	        {
	            System.out.println("Course:");
	            System.out.println("课程号\t课程名\t老师");
				JTextField info1 = new JTextField("Course:");
				JTextField info2 = new JTextField("课程号\t课程名\t老师");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
	            
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String Cno = res.getString("Cno");
	                String CName = res.getString("CName");
	                String Teacher = res.getString("Teacher");
	                System.out.println(Cno+"\t"+CName+"\t"+Teacher);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(Cno+"\t"+CName+"\t"+Teacher);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	            System.out.println();
	        }
	        else if(tableName.equals("Scores"))
	        {
	            System.out.println("Scores:");
	            System.out.println("学号\t课程号\t成绩");
				JTextField info1 = new JTextField("Scores:");
				JTextField info2 = new JTextField("学号\t课程号\t成绩");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
	            
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String Sno = res.getString("Sno");
	                String Cno = res.getString("Cno");
	                String Grade = res.getString("Grade");
	                System.out.println(Sno+"\t"+Cno+"\t"+Grade);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(Sno+"\t"+Cno+"\t"+Grade);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	    		System.out.println();
	        }
	        else if(tableName.equals("Hobby"))
	        {
	            System.out.println("Hobby:");
	            System.out.println("爱好编号\t\t爱好名称");
				JTextField info1 = new JTextField("Hobby:");
				JTextField info2 = new JTextField("爱好编号\t\t爱好名称");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
	            
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String HNo = res.getString("HNo");
	                String HName = res.getString("HName");
	                System.out.println(HNo+"\t"+HName);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(HNo+"\t"+HName);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	    		System.out.println();
	        }
	        else if(tableName.equals("StuHobby"))
	        {
	            System.out.println("StuHobby:");
	            System.out.println("学生编号\t\t爱好编号\t\t爱好程度");
				JTextField info1 = new JTextField("StuHobby:");
				JTextField info2 = new JTextField("学生编号\t\t爱好编号\t\t爱好程度");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
	            
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String SNo = res.getString("SNo");
	            	String HNo = res.getString("HNo");
	                String HLevel = res.getString("HLevel");
	                System.out.println(SNo+"\t"+HNo+"\t"+HLevel);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(SNo+"\t"+HNo+"\t"+HLevel);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	    		System.out.println();
	        }
	        else if(tableName.equals("University"))
	        {
	            System.out.println("University:");
	            System.out.println("大学编号\t大学名称\t\t大学描述信息");
				JTextField info1 = new JTextField("University:");
				JTextField info2 = new JTextField("大学编号\t\t大学名称\t\t大学描述信息");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
	            
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String UNo = res.getString("UNo");
	            	String UName = res.getString("UName");
	                String Info = res.getString("Info");
	                System.out.println(UNo+"\t"+UName+"\t"+Info);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(UNo+"\t"+UName+"\t"+Info);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	    		System.out.println();
	        }
	        else if(tableName.equals("Graduate"))
	        {
	            System.out.println("Graduate:");
	            System.out.println("学号\t\t大学编号\t\t录取信息");
				JTextField info1 = new JTextField("Graduate:");
				JTextField info2 = new JTextField("学号\t\t大学编号\t\t录取信息");
				info1.setBounds(10,210,100,20);
				info2.setBounds(10,230,540,20);
				Main.panel.add(info1);
				Main.panel.add(info2);
	            
				int highth = 230;
	            while(res.next()){
	            	//获取查询结果
	            	String SNo = res.getString("SNo");
	            	String UNo = res.getString("UNo");
	                String Diff = res.getString("Diff");
	                System.out.println(SNo+"\t"+UNo+"\t"+Diff);
	                //每次都创建一个文本框，往里面放内容
	                JTextField info = new JTextField(SNo+"\t"+UNo+"\t"+Diff);
	                highth += 18;
					info.setBounds(10,highth,540,20);
					Main.panel.add(info);
	            }
	    		JLabel TrueLabel = new JLabel("查找成功！");
	    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
	    		highth += 18;
	    		TrueLabel.setBounds(10,highth,100,20);
	    		Main.panel.add(TrueLabel);
	    		System.out.println();
	        }
	        else {
                JTextField info = new JTextField("输入错误，没有"+tableName+"表！");
				info.setBounds(10,210,540,20);
				Main.panel.add(info);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
