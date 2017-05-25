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
/*
 * 查询生活中的大师类
 * http://www.192.168.1.101:8080/
 * */
public class SelectCareer extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("GBK");
		PrintWriter outt = resp.getWriter();  
		Connection connection=Dbutil.getConnection();
		 try { Statement statement=connection.createStatement();
			String sql="select car_id,name,description from career where spec=0";//
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

}
