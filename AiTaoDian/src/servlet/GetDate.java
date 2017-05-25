package servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date=new Date();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String s=dateFormat.format(date);
		System.out.print(s);
	}

}
