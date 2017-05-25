package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import db.Dbutil;

public class UserDao {
	
	

	public int  query(String pass,String name) throws SQLException{
		Connection connection=Dbutil.getConnection();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select * from god where name='"+name+"' and pass='"+pass+"'");//and 
		if(resultSet.next()){
			return 0;
			//System.out.println(resultSet.getString("name")+","+resultSet.getString("pass"));
		}else{
			return 1;
		}
		}
	/*public static void main(String[] args) {
		try {
			int flag=new UserDao().query("1231","111111");
			System.out.println(flag);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
}
