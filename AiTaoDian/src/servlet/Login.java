package servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import dao.UserDao;
import db.Dbutil;

public class Login extends HttpServlet {
	
	//用户名和密码都正确
	private static final int NAME_CODE_RIGHT = 0; //  
	//用户名和密码都错误
	private static final int NAME_CODE_WRONG = 2; 
	private static final long serialVersionUID = 1L;  

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		 response.setCharacterEncoding("text/html;charset=utf-8");
		 response.setCharacterEncoding("UTF-8");  
		 PrintWriter out = response.getWriter();  
       
	          /*
	           * 需要客户端指定两个参数的值
	           * */
		/* String NAME="111111";
		 String CODE="123";*/
	        String name = request.getParameter("NAME");  
	        String code = request.getParameter("CODE"); 
	        
	        int res=checkSubmit(name,code);
	        
	        out.println(res);
	        out.flush();
	        out.close();
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
	
	
	

	private int checkSubmit(String name, String code) {
		// TODO Auto-generated method stub
		int ret=-2;
		Connection connection=Dbutil.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from god where phone='"+name+"' and pass='"+code+"'");
			if(resultSet.next()){
				ret=NAME_CODE_RIGHT;
			}else{
				ret=NAME_CODE_WRONG;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ret);
		return ret;
	}
	
	
	

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		
	}

}
