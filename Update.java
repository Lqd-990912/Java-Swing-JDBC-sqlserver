package JavaGUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *	本类用于执行更新(插入\删除\修改)功能
 * */

public class Update {
	
	Statement statement = null;
	
	int highth = 500;
	public Update()
	{
		try 
		{
			// 创建请求更新的标签
    		JLabel AskLabel = new JLabel("请问是否要进行更新(yes/no)? ");
    		AskLabel.setBounds(10,highth, 200, 25);
    		Main.panel.add(AskLabel);
    		// 创建文本框
    		JTextField Ask = new JTextField(20);
    		Ask.setBounds(190, highth, 100, 25);
    		Main.panel.add(Ask);
    		// 创建确认输入的按钮
    		JButton AskButton = new JButton("确认");
    		AskButton.setBounds(300, highth, 70, 25);
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
    					//开始进行更新
    					try 
    					{
    						System.out.println("开始进行更新");
    						
    			    		JLabel OPLabel = new JLabel("你输入您要执行的更新操作SQL语句:");
    			    		highth += 30;
    			    		OPLabel.setBounds(10,highth, 250, 25);
    			    		Main.panel.add(OPLabel);
    			    		// 创建文本框
    			    		JTextField OPText = new JTextField(20);
    			    		highth += 23;
    			    		OPText.setBounds(10, highth, 300, 25);
    			    		Main.panel.add(OPText);
    			    		// 创建确认输入的按钮
    			    		JButton AskButton = new JButton("确认");
    			    		AskButton.setBounds(320, highth, 70, 25);
    			    		Main.panel.add(AskButton);
    			    		ActionListener al2 = new ActionListener() 
    			    		{
    			    			@Override
    			    			public void actionPerformed(ActionEvent e) 
    			    			{					
    			    				String OPSQL = OPText.getText();
    			    				System.out.println("执行的语句为: "+OPSQL);
    			    				
    			    				try 
    			    				{
										statement = Main.con.createStatement();
										statement.executeUpdate(OPSQL);
										
										System.out.println("执行成功！");
										
							    		JLabel TrueLabel = new JLabel("执行成功！");
							    		TrueLabel.setFont(new Font("微软雅黑",1,15)); 
							    		highth += 18;
							    		TrueLabel.setBounds(10,highth, 200, 25);
							    		Main.panel.add(TrueLabel);
    			    				}
    			    				catch (Exception e2) 
    			    				{
    			    					
    			    					System.out.println("执行失败，您的SQL语句可能有错误。");
    			    					
							    		JLabel TrueLabel = new JLabel("执行失败，您的SQL语句可能有错误。");
							    		highth += 18;
							    		TrueLabel.setBounds(10,highth, 200, 25);
							    		Main.panel.add(TrueLabel);
										e2.printStackTrace();
									}
    			    			}
    			    		};
    			    		AskButton.addActionListener(al2);
    			    			
    					}
    					catch (Exception e1) 
    					{
							e1.printStackTrace();
						}
    				}
    				else 
    				{
    					//不需要进行更新
    					System.out.println("不需要进行更新");
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
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
	}
}
