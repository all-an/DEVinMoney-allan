package principal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class Teste {

	public static void main(String[] args) throws IOException, InterruptedException {
		
	    {
	        // getting the URL class object
	        URL url = new URL("http://www.google.com");
	  
	        // opening the connection
	        HttpURLConnection httpCon
	            = (HttpURLConnection)url.openConnection();
	  
	        // getting the date of URL connection
	        long date = httpCon.getDate();
	  
	        /*
	          Other working of program
	        */
	  
	        // if date is 0,it means there is no 
	        // information regarding date
	        if (date == 0)
	            System.out.println("No date information.");
	        else {
	            // print the date using object of Date class
	            System.out.println("Date: " + new Date(date));
	        }
	    }
	}
}