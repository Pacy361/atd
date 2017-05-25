package servlet;
//172.10.3.23
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.sun.javafx.geom.Path2D;

import db.Dbutil;

import sun.misc.BASE64Decoder;

/*
 * http://atd.createclouds.cn/images/*/

public class ReceiveImage extends HttpServlet {
	static String ip="http://atd.createclouds.cn/images/";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");//便是response的响应的编码方式为utf-8 
		req.setCharacterEncoding("utf-8");//便是response的响应的编码方式为utf-8 
		response.setContentType("text/html");
		String photo=req.getParameter("photo");
		String content=req.getParameter("content");
		String phone=req.getParameter("id");
		String contentstr = new String(content.getBytes("ISO-8859-1"),"utf-8");
		
		String[] imageStrings=photo.split("#");
		
		System.out.println("tupian"+imageStrings[0].length());
		System.out.println("tupian"+imageStrings[1].length());
		System.out.println("tupian"+imageStrings[2].length());
		System.out.println(contentstr);
		String  realPath=req.getRealPath("/")+"images";
		/*三个图片字节数组*/
		byte[] photoString0=new BASE64Decoder().decodeBuffer(imageStrings[0]);
		byte[] photoString1=new BASE64Decoder().decodeBuffer(imageStrings[1]);
		byte[] photoString2=new BASE64Decoder().decodeBuffer(imageStrings[2]);
		/*第一个文件写入*/
		String path0="tu"+System.currentTimeMillis()+"1.jpeg";//文件名
		File file=new File("/images");
		if(!file.exists()){
			file.mkdirs();
		}//创建文件夹
		file = new File("/images",path0);//创建文件
		FileOutputStream outputStream=new FileOutputStream(file);
		outputStream.write(photoString0);
		System.out.print(ip+path0);
		/*第二个文件写入*/
		String path1="tu"+System.currentTimeMillis()+"2.jpeg";//文件名
		file = new File("/images",path1);//创建文件
		FileOutputStream outputStream1=new FileOutputStream(file);
		outputStream1.write(photoString1);
		/*第三个文件写入*/
		String path2="tu"+System.currentTimeMillis()+"3.jpeg";
		file = new File("/images",path2);//创建文件
		FileOutputStream outputStream2=new FileOutputStream(file);
		outputStream2.write(photoString2);
		
		outputStream.flush();
		outputStream.close();
		
		
		Date date=new Date();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString=dateFormat.format(date);
		PrintWriter outt = response.getWriter();  
		Connection connection=Dbutil.getConnection();
		try {
			Statement statement=connection.createStatement();
			String sql="insert into idea(name,title,tu1,tu2,tu3,publishdata) values('111111','"+contentstr+"','"+ip+path0+"','"+ip+path1+"','"+ip+path2+"','"+dateString+"')";
			statement.execute(sql);
			outt.flush();
			outt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
