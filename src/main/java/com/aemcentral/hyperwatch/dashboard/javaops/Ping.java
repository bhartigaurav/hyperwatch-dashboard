package com.aemcentral.hyperwatch.dashboard.javaops;

import java.net.HttpURLConnection;
import java.net.URL;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class Ping {
	//Logging enabled
	public static int client(String domain){
		
		int result=0;
		
		String url = ""+domain+":9000/remotemachine/system/check";
		
		try {
			URL urlObj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			
			// Set connection timeout
			con.setConnectTimeout(3000);
			con.connect();

			int code = con.getResponseCode();

			if (code == 200) {
				result = 200;
			}
			
		} catch (Exception e) {
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.javaops.Ping.client Exception Handled "+e.toString(),2);
			result = 404;
		}
		
		return result;
	}
	
}
