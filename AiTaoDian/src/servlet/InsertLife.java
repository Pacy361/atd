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
 *发表话题
 **/
public class InsertLife extends HttpServlet{
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
		response.setCharacterEncoding("UTF-8");//便是response的响应的编码方式为utf-8 
		response.setHeader("content-type","text/html;charset=UTF-8");
		String userId=request.getParameter("phone");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String titlestr = new String(title.getBytes("ISO-8859-1"),"utf-8");//还原字节序列使用utf-8重新编码
		String contentstr = new String(content.getBytes("ISO-8859-1"),"utf-8");//还原字节序列使用utf-8重新编码
		
		Date date=new Date();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString=dateFormat.format(date);
		
		PrintWriter outt = response.getWriter();  
		Connection connection=Dbutil.getConnection();
		 
		try {
			Statement statement=connection.createStatement();
			String sql="insert into message(user_id,title,content,time,spec) values('"+userId+"','"+titlestr+"','"+contentstr+"','"+dateString+"',0)";
			statement.execute(sql);
			
			outt.print(request.getParameter("phone")+""+titlestr+contentstr);
			System.out.print(request.getParameter("phone")+""+titlestr+contentstr);
			outt.flush();
			outt.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//"'"+content+"'"
		
		
		
		
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
