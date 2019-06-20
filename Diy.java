package JavaGUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *	自定义功能：自己任意输入一条语句并指明是何种类型的语句
 * */

public class Diy {

	PreparedStatement stateSelect = null;//查询用的state
	Statement stateUpdate = null;//更新用的state
    ResultSet res = null;
    
    public Diy()
    {
        try 
        {
        	while(true)
        	{
	            System.out.println("请问要是否进行自定义操作(yes/no)?");
	            Scanner scan = new Scanner(System.in);
	            String flag = scan.nextLine();
	            if(flag.equals("no"))
	            {
	            	break;
	            }
                String SelfSQL,SQLType;
                System.out.println("请输入您要执行的sql语句:");

                SelfSQL = scan.nextLine();
                System.out.println("指明该sql语句的类型(插入\\删除\\修改\\查询):");
                SQLType = scan.nextLine();
                if(SQLType.equals("修改") || SQLType.equals("插入") || SQLType.equals("删除"))
                {
                	//insert into Student VALUES ('3','李庆东','男',20,'北京邮电大学','17计科')
                	boolean flag1 = true;
                	stateUpdate = Main.con.createStatement();
                	try {
                		stateUpdate.executeUpdate(SelfSQL);
        			} catch (Exception e) {
        				System.out.println("执行错误！");
        				flag1 = false;
        			}
                	if(flag1)
                		System.out.println("执行成功！");
                }
                else if(SQLType.equals("查询"))
                {
                    //String sql = "select *from "+tableName;//查询Student表
                	stateSelect = Main.con.prepareStatement(SelfSQL);
                    res = stateSelect.executeQuery();
                }
        	}
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(stateSelect != null) stateSelect.close();
				if(stateUpdate != null) stateUpdate.close();
				if(res != null) res.close();
			} 
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
    }
}
