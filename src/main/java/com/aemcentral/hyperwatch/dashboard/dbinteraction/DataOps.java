package com.aemcentral.hyperwatch.dashboard.dbinteraction;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aemcentral.hyperwatch.dashboard.alerts.Analyse;
import com.aemcentral.hyperwatch.dashboard.alerts.Slack;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class DataOps {
	//Logging Enabled
	//fetch data --> filterData --> Adddata
	
	
	public static void main(String Args[]) {
		Fetchactive("vmm-5");
	}
	
public static int Fetchactive(String VMMcode) {
		
		String response ="";
		int active=0;
		
		String domain = FetchDB.getdomain(VMMcode);
		String secret= FetchDB.getVMMcode(VMMcode);//"q1342we09x";//fetch from DB master table
		 try {
	            // Step 1: Create a URL object
	            URL url = new URL(domain+":9000/remotemachine/"+secret+"/monitoring/currentstats");

	            // Step 2: Open a connection
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Step 3: Set the request method to GET
	            connection.setRequestMethod("GET");

	            // Step 4: Read the response
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            StringBuilder content = new StringBuilder();
	            while ((inputLine = in.readLine()) != null) {
	                content.append(inputLine);
	            }

	            // Close the connections
	            in.close();
	            connection.disconnect();
	            active=1;
	            // Step 5: Convert to String
	            //response = content.toString();
	            //System.out.println(response);
	            
	            
	        } catch (Exception e) {
	        	
	        	Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps.FetchData Exception Handled "+e.toString(),2);
			
	        	//Handling not responding VMs.
	   
	            e.printStackTrace();
	        }
		
		return active;
	}
	
	public static int FetchData(String VMMcode) {
		
		String response ="";
		
		int filterdataop =0;
		
		String domain = FetchDB.getdomain(VMMcode);
		String secret= FetchDB.getVMMcode(VMMcode);//"q1342we09x";//fetch from DB master table
		 try {
	            // Step 1: Create a URL object
	            URL url = new URL(domain+":9000/remotemachine/"+secret+"/monitoring/currentstats");

	            // Step 2: Open a connection
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	            // Step 3: Set the request method to GET
	            connection.setRequestMethod("GET");

	            // Step 4: Read the response
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            StringBuilder content = new StringBuilder();
	            while ((inputLine = in.readLine()) != null) {
	                content.append(inputLine);
	            }

	            // Close the connections
	            in.close();
	            connection.disconnect();

	            // Step 5: Convert to String
	            response = content.toString();
	            Logg.writetofile(VMMcode+"-"+response, 1);
	            //System.out.println(response);
	            filterdataop = FilterData(response);
	            
	        } catch (Exception e) {
	        	
	        	Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps.FetchData Exception Handled "+e.toString(),2);
				
	        	//Handling not responding VMs.
	        	int Adddataop= AddData( VMMcode,  "",  "", 
		    			 "", "", "", "",
		    			 "",  "Not Available");
	        	
	        	String webappname= FetchDB.getname(VMMcode);
	        	//Alert
	        	Slack.sendslackmessage(webappname);
	        	
	            e.printStackTrace();
	        }
		
		return filterdataop;
	}
	
	public static int FilterData(String jsonString) {
		
	        // Create Gson instance
	        Gson gson = new Gson();

	        // Convert string to JSON object
	        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);

	        // Fetch values
	        String VMMCode = jsonObject.get("VMMCode").getAsString();
	      
	        String CpuLoadPercentage = jsonObject.get("CpuLoadPercentage").getAsString();
	        String TotalMemoryMB=jsonObject.get("TotalMemoryMB").getAsString();
			String MemoryinuseMB=jsonObject.get("MemoryinuseMB").getAsString();
			String PercentageMemoryUse =jsonObject.get("PercentageMemoryUse").getAsString();
			String TotalDiskSpaceGB=jsonObject.get("TotalDiskSpaceGB").getAsString();
			String DiskSpaceinUseGB=jsonObject.get("DiskSpaceinUseGB").getAsString();
			String PercentageDiskUse=jsonObject.get("PercentageDiskUse").getAsString();
			String Status=jsonObject.get("Status").getAsString();
	        
	      //call to AddData  
	       
	       //Alerting System based on status - Email and Slack
	       if(Status.contains("metrics under control")) {
	    	   //All Good
	       }
	       else {
	    	   //Add to Alerts
	    	   //vmmcode,timestamp,status
	    	   
	    	   //check last status and alert accordingly
	    	  Analyse.merticalert(VMMCode, Status );
	    	  
	    	  AddData.alerts(VMMCode, Status);
	       }
	       
	       int Adddataop= AddData( VMMCode,  CpuLoadPercentage,  TotalMemoryMB, 
	    			 MemoryinuseMB, PercentageMemoryUse, TotalDiskSpaceGB, DiskSpaceinUseGB,
	    			 PercentageDiskUse,  Status);
		
	       
		return Adddataop;
	}

	public static int AddData(String VMMCode, String CpuLoadPercentage, String TotalMemoryMB, 
			String MemoryinuseMB,String PercentageMemoryUse,String TotalDiskSpaceGB,String DiskSpaceinUseGB,
			String PercentageDiskUse, String Status) {
		
		int result=0;
		//Add to Database table - VMMCode
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		String formattedDate = myDateObj.format(myFormatObj);
		
		String sql ="INSERT INTO `"+VMMCode+"`(`c1`, `c2`, `c3`, `c4`, `c5`, `c6`, `c7`, `c8`, `c9`) VALUES ('"+formattedDate+"','"+CpuLoadPercentage+
				"','"+TotalMemoryMB+"','"+MemoryinuseMB+"','"+PercentageMemoryUse+"','"+TotalDiskSpaceGB+
				"','"+DiskSpaceinUseGB+"','"+PercentageDiskUse+"','"+Status+"')";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement st = con.createStatement();

		 st.executeUpdate(sql);

			con.close();
			result=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps.AddData JDBC ERROR - "+e.toString(), 2);

		}
		
		return result;
	}

}
