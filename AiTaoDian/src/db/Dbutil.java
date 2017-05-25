package db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;

public class Dbutil {

	/**
	 * @param args
	 *///characterEncoding=utf-8
	/*private static String URL="jdbc:mysql://127.0.0.1:3306/imooc?useUnicode=true&characterEncoding=utf8";
	private static String USER="root";
	private static String PASSWORD="123";
	private static Connection connection=null;*/
	private static String URL="jdbc:mysql://123.207.252.135:3306/imooc?useUnicode=true&characterEncoding=utf8";
	private static String USER="root";
	private static String PASSWORD="2wsx@WSX";
	private static Connection connection=null;
	
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=(Connection) DriverManager.getConnection(URL,USER,PASSWORD);
	
			System.out.println("SUCCESSFUL");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static Connection getConnection(){
		return connection;
	}
	public String getPath(){
		 String rootPath=getClass().getResource("/").getFile().toString();  
		return rootPath;
	}
	
	public static void main(String[] agsStrings){
		
		System.out.println(new Dbutil().getPath());
	}
}
