package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import db.Dbutil;
/*
 * 总结花费
 * */
public class InsertCount extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		response.setCharacterEncoding("UTF-8");//便是response的响应的编码方式为utf-8 
		response.setHeader("content-type","text/html;charset=UTF-8");
		String usercontent=request.getParameter("content");
		String userId=request.getParameter("id");
		String money=request.getParameter("money");
		int price=Integer.parseInt(money);
		String contentstr = new String(usercontent.getBytes("ISO-8859-1"),"utf-8");//还原字节序列使用utf-8重新编码
		
		Date date=new Date();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=dateFormat.format(date);
		//http://192.168.1.101:8080/AiTaoDian/servlet/InsertCount?count=牛肉面&id=111111&money=10
		PrintWriter outt = response.getWriter();  
		Connection connection=Dbutil.getConnection();
		try {
		Statement statement=connection.createStatement();
		String sql="insert into hmony values('"+userId+"','"+dateString+"','"+contentstr+"',"+price+")";
		
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outt.print(userId+contentstr+dateString);
		System.out.print(userId+contentstr+dateString);
		outt.flush();
		outt.close();	
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
