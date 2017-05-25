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

public class ReplyLife extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPut(request, response);
	}
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("GBK");
		// response.setCharacterEncoding("GBK");
		/*response.setCharacterEncoding("UTF-8");//便是response的响应的编码方式为utf-8 
		response.setHeader("content-type","text/html;charset=UTF-8");*/
		 String name = request.getParameter("ID"); 
		 PrintWriter outt = response.getWriter();  
	try {
		 Connection connection=Dbutil.getConnection();
		 Statement statement=connection.createStatement();
		 String sql="SELECT username,re_content,re_time from message " +
		 		"join reply on reply.mes_id=message.mes_id join god on god.uid=reply.re_id " +
		 		"WHERE  message.mes_id='"+name+"'";
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
}
