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

public class SelectHappyContent extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, response);
		response.setCharacterEncoding("GBK");
		String idString=req.getParameter("ID");
		int id=Integer.parseInt(idString);
		PrintWriter outt = response.getWriter();  
		try {
			 Connection connection=Dbutil.getConnection();
			 Statement statement=connection.createStatement();
			 String sql="select id,conetent,price,transport from happytime where speace="+id+"";//
					ResultSet re=statement.executeQuery(sql);
					//将数据库查询的结果转换为json格式
			        String result=resultSetToJson(re);
			        //outt.println("ID为"+name);
			        outt.println(result);
			        System.out.println(result);
			        outt.flush();
					outt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
