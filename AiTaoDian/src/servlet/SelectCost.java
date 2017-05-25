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
import javax.xml.ws.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.omg.CORBA.Request;

import com.mysql.jdbc.Connection;

import db.Dbutil;

public class SelectCost extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest Request, HttpServletResponse Response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(Request, Response);	 
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
	protected void doGet(HttpServletRequest Request, HttpServletResponse Response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Response.setCharacterEncoding("GBK");
		 PrintWriter outt = Response.getWriter();  
		 Connection connection=Dbutil.getConnection();
		 try { Statement statement=connection.createStatement();
			String sql="select id,time,content,price from hmony";//
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
	}

}
