package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import db.RongCloudUtil;

public class TestToken extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//http://192.168.1.102:8080/AiTaoDian/servlet/TestToken?phone1=333333&name1=张三&phone2=2&name2=2
		response.setCharacterEncoding("GBK");
		 PrintWriter outt = response.getWriter();  
		 String userPhone1=req.getParameter("phone1");
		 String userName1=req.getParameter("name1");
		 String userUrl1="http://img1.imgtn.bdimg.com/it/u=3835393025,865701498&fm=23&gp=0.jpg";
		 
		 String userPhone2=req.getParameter("phone2");
		 String userName2=req.getParameter("name2");
		 String userUrl2="http://img0.imgtn.bdimg.com/it/u=885748648,2807985908&fm=23&gp=0.jpg";
		 
		String userTokenString1=getToken(userPhone1,userName1,userUrl1);
		String userTokenString2=getToken(userPhone2,userName2,userUrl2);
		
		outt.println(userTokenString1);
        System.out.println(userTokenString1);
        
        outt.println(userTokenString2);
        System.out.println(userTokenString2);
        
        outt.flush();
		outt.close();
	}
	
	public static String getToken(String userId, String name, String portraitUri) {  
        String getToken = "https://api.cn.rong.io/user/getToken.json";  
        Map<String, String> params = new HashMap<String, String>();  
        params.put("userId", userId);  
        params.put("name", name);  
        params.put("portraitUri", portraitUri);  
        byte[] resultArray;  
        String token = null;  
        try {  
            resultArray = RongCloudUtil.post(getToken, params, "UTF-8", 20000);  
            String result = new String(resultArray);  
            JSONObject obj = JSONObject.fromObject(result);  
            token = obj.get("token").toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("没获取到token");  
        }  
        return token;  
    }  
	
	

}
