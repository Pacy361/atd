package servlet;

import java.io.BufferedReader;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.util.Enumeration;  
   
import javax.servlet.ServletException;  
import javax.servlet.ServletInputStream;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

public class JSONServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;  
	 
	 
	/**
	 * Constructor of the object.
	 */
	public JSONServlet() {
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

		doPost(request, response);
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

		response.setCharacterEncoding("GBK");
		String result = "";  
		 try {  
	            /* 读取数据 */  
	            BufferedReader br = new BufferedReader(  
	                    new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));  
	            StringBuffer sb =new StringBuffer("");  
	            String temp;  
	            while((temp=br.readLine())!=null){  
	                sb.append(temp);
	            }  
	            br.close();  
	            result = sb.toString();  
	            System.out.println("请求报文:" + result);  
	        } catch (Exception e) {  
	            result = "{err:\"error\"}";  
	        } finally {  
	            /* 返回数据 */  
	            System.out.println("返回报文:" + result);  
	            PrintWriter pw = response.getWriter();  
	            pw.write(result);  
	            pw.flush();  
	            pw.close();  
	        }  
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
