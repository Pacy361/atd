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

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;

import db.Dbutil;

public class SelectCreate extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");//便是response的响应的编码方式为utf-8 
		response.setHeader("content-type","text/html;charset=UTF-8");
		 PrintWriter outt = response.getWriter();  
		 Connection connection=Dbutil.getConnection();
			try { Statement statement=connection.createStatement();
				String sql="select username,mes_id,phone,title,content,time,task from god join message on message.user_id=god.phone where spec=5";//
				//String sql2="select * from reply";
			ResultSet re=statement.executeQuery(sql);
				//将数据库查询的结果转换为json格式
		        String result=resultSetToJson(re);
		        outt.println(result);
		        System.out.println(result);
		     
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outt.flush();
			outt.close();
	}
	public String resultSetToJson(ResultSet rs) throws SQLException,JSONException{
		JSONArray jaArray=new JSONArray();
		ResultSetMetaData metaData=rs.getMetaData();
		int columnCount=metaData.getColumnCount();
		String name[]=new String[columnCount];
		//遍历每条数据
		while(rs.next()){
			JSONObject jsonObject=new JSONObject();
			for(int i=0;i<columnCount;i++){
				String columnNmae=metaData.getColumnLabel(i+1);
				String value=rs.getString(columnNmae);
				jsonObject.put(columnNmae,value);	
			}
			jaArray.add(jsonObject);
		}
		return jaArray.toString();	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
