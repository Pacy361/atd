package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.mysql.jdbc.Connection;

import db.Dbutil;

public class SelectFriend extends HttpServlet {
	//用户名和密码都正确
		private static final int NAME_CODE_RIGHT = 0; //  
		//用户名和密码都错误
		private static final int NAME_CODE_WRONG = 1;
		int ret=-2;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("GBK");//便是response的响应的编码方式为utf-8 
		//response.setHeader("content-type","text/html;charset=UTF-8");
		 PrintWriter outt = response.getWriter(); 
		 String name = req.getParameter("phone1"); 
		 String name2 = req.getParameter("phone2"); 
		 String nameStr = new String(name.getBytes("ISO-8859-1"),"utf-8");
		 String name2Str = new String(name2.getBytes("ISO-8859-1"),"utf-8");
		 //outt.println(nameStr);
		 Connection connection=Dbutil.getConnection();
			try { 
				Statement statement=connection.createStatement();//select * from message where message.title like '%小%'
				ResultSet resultSet = statement.executeQuery("select * from friend where phone1='"+nameStr+"' and phone2='"+name2Str+"' and id1=id2=1");
				if(resultSet.next()){
					ret=NAME_CODE_RIGHT;
				}else{
					ret=NAME_CODE_WRONG;
				}
		        outt.println(ret);
		        System.out.println(ret);
		     
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outt.flush();
			outt.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
