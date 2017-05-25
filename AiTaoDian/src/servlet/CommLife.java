package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;

import db.Dbutil;

public class CommLife extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");//便是response的响应的编码方式为utf-8 
		response.setHeader("content-type","text/html;charset=UTF-8");
		String userId=request.getParameter("phone");
		String mesId=request.getParameter("mesId");
		String content=request.getParameter("content");
		String str = new String(content.getBytes("ISO-8859-1"),"utf-8");//还原字节序列使用utf-8重新编码
		
		Date date=new Date();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String dateString=dateFormat.format(date);
		
		PrintWriter outt = response.getWriter();  
		 Connection connection=Dbutil.getConnection();
		 try {
			 int id = 0;
			 int msId=Integer.parseInt(mesId);
			Statement statement=connection.createStatement();//"'"+content+"'"
			String sql="insert into reply SELECT '"+mesId+"',god.uid,"+"'"+content+"'"+"," +"'"+dateString+"'" +" from god where phone='222222'";
			//String sql2="select * from god";
			statement.execute(sql);

			outt.print(request.getParameter("phone")+""+msId+str);
			System.out.print(request.getParameter("phone")+""+msId+str);
			outt.flush();
			outt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
