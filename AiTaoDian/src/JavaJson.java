import javax.mail.search.FromStringTerm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JavaJson {
	
	public void javaJson(){
		
		String json = "{'status':200,'message':'查询成功','data':[{'id':1,'name':'name1','describe':'第一条数据'}]}";
		System.out.println(json);
		//解析json
		JSONObject data=JSONObject.fromObject(json);
		int statues=data.optInt("status");
		JSONArray data1=data.getJSONArray("data");
		if(statues==200){
			for(int i=0;i<data1.size();i++){
				String s=data1.getString(i);
				System.out.println(s);
				JSONObject data2=JSONObject.fromObject(s);
				System.out.println(data2.getString("id"));
				System.out.println(data2.getString("name"));
				System.out.println(data2.getString("describe"));
			}
			
			
		}
		else if(statues==500){
			String msg=data.getString("message");
			System.out.println(msg);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new JavaJson().javaJson();
	}

}
