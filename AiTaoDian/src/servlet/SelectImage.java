package servlet;

import it.sauronsoftware.base64.Base64;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import db.Dbutil;

public class SelectImage extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//便是response的响应的编码方式为utf-8 
		resp.setContentType("text/html");
		 PrintWriter outt = resp.getWriter(); 
		// TODO Auto-generated method stub
		 Connection connection=Dbutil.getConnection();
			/*try { Statement statement=connection.createStatement();
				String sql="select idea.id,god.username,title,tu1,tu2,tu3,tu4,tu5,tu6,tu7,tu8,tu9 from idea  join god ON idea.name=god.phone";
				
				ResultSet re=statement.executeQuery(sql);
				ResultSetMetaData resultSetMetaData=re.getMetaData();
				int coloun=resultSetMetaData.getColumnCount();
				while(re.next()){
				for(int i=4,j=1;i<12&&j<=9;i++,j++){
					if(!re.getString("tu"+j).equals("")){
						//outt.println(re.getString("tu"+j));
						outt.println(pathToImage(re.getString("tu"+j)));
					}
				}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outt.flush();
			outt.close();*/
		 FileInputStream fis = new FileInputStream("d:/tu.jpg");    
         java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();    
         byte[] buff = new byte[1024];    
         int len = 0;      
           
         while ((len = fis.read(buff)) != -1)    
         {    
             bos.write(buff, 0, len);    
         }    
           
      // 得到图片的字节数组    
         byte[] result = bos.toByteArray();    
         outt.write(Arrays.toString(result));
         System.out.println("图片的字节数组:\n" + Arrays.toString(result));    
                     
	}
	private String pathToImage(String string) throws IOException {
		// TODO Auto-generated method stub
		
		   byte[] data = null;
		    FileImageInputStream input = null;
		    try {
		        input = new FileImageInputStream(new File(string));
		        ByteArrayOutputStream output = new ByteArrayOutputStream();
		        byte[] buf = new byte[1024];
		        int numBytesRead = 0;
		        while ((numBytesRead = input.read(buf)) != -1) {
		        output.write(buf, 0, numBytesRead);
		        }
		        
		        data = output.toByteArray();
		        output.close();
		        input.close();
		      }
		      catch (FileNotFoundException ex1) {
		        ex1.printStackTrace();
		      }
		      catch (IOException ex1) {
		        ex1.printStackTrace();
		      }
		    String str = new String(data, "utf-8");  //unicode 2位
		      return str;
		    }
		    
	}
	
	

