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

import com.mysql.jdbc.Connection;

import db.Dbutil;

public class SelectLife extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 response.setCharacterEncoding("GBK");
		 PrintWriter outt = response.getWriter();  
		 Connection connection=Dbutil.getConnection();
			try { Statement statement=connection.createStatement();
				String sql="select username,mes_id,phone,title,content,time from god join message on message.user_id=god.phone where spec=0";//
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
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}

	

}
