package com.aemcentral.hyperwatch.dashboard.alerts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class Slack {
	
	public static void main(String Args[]) {
		sendslackmessage("Test");
	}
	
	public static int metricmessage(String name, String status) {
    	int result=0;
    	LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        
    	try {
    		
    		String payload = 
    		        "{\r\n"
    		        + "    \"text\": \" :siren:Metrics not under Control! \nWebapp - "+name+" - "+status+"  \n TimeStamp Reported : "+formattedDate+" \"\r\n"
    		        + "}";
    		        StringEntity entity = new StringEntity(payload,
    		                ContentType.APPLICATION_JSON);

    		        HttpClient httpClient = HttpClientBuilder.create().build();
    		        HttpPost request = new HttpPost("https://hooks.slack.com/services/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");
    		        request.setEntity(entity);

    		        HttpResponse response = httpClient.execute(request);
    		    
    		        Logg.writetofile("com.aemcentral.hyperwatch.dashboard.alerts.metricmessage Slack Message Status:"+response.getStatusLine().getStatusCode()+response.getStatusLine(), 1);
    		        result=1;
    	}
    	catch(Exception e) {
    		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.alerts.metricmessage Exception Handled! "+e.toString(), 2);
    		result=0;
    	}
        return result;
    }
	
	 public static int sendslackmessage(String name) {
	    	int result=0;
	    	LocalDateTime myDateObj = LocalDateTime.now();
	        System.out.println("Before formatting: " + myDateObj);
	        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	        String formattedDate = myDateObj.format(myFormatObj);
	        
	    	try {
	    		
	    		String payload = 
	    		        "{\r\n"
	    		        + "    \"text\": \" :siren: Webapp - "+name+" is not responding! \n TimeStamp Reported : "+formattedDate+" \"\r\n"
	    		        + "}";
	    		        StringEntity entity = new StringEntity(payload,
	    		                ContentType.APPLICATION_JSON);

	    		        HttpClient httpClient = HttpClientBuilder.create().build();
	    		        HttpPost request = new HttpPost("https://hooks.slack.com/services/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");
	    		        request.setEntity(entity);

	    		        HttpResponse response = httpClient.execute(request);
	    		    
	    		        Logg.writetofile("com.aemcentral.hyperwatch.dashboard.alert.sendslackmessages Slack Message Status:"+response.getStatusLine().getStatusCode()+response.getStatusLine(), 1);
	    		        result=1;
	    	}
	    	catch(Exception e) {
	    		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.alerts.sendslackmessage Exception Handled! "+e.toString(), 2);
	    		result=0;
	    	}
	        return result;
	    }
	
	
}
