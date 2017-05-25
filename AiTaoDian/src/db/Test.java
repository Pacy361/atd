package db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;

public class Test {

	/**
	 * @param args
	 */
	public static String resultSetToJson(ResultSet rs) throws SQLException,JSONException{
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
	public static void main(String[] args){
		new Dbutil();
		Connection connection=Dbutil.getConnection();
		try { Statement statement=connection.createStatement();
			String sql="select username,mes_id,phone,title,content,time from god join message on message.user_id=god.phone where spec=0";//
			//String sql2="select * from reply";
		ResultSet re=statement.executeQuery(sql);
			//将数据库查询的结果转换为json格式
	        String result=resultSetToJson(re);
	        System.out.println(result);
	     
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
