package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import com.aemcentral.hyperwatch.dashboard.logging.Logg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class EnvDetails {

	public static void main (String Args[])
	{
		getmachinfo("Auth Service");
		
	}
	
	public static ArrayList<String> printcurrentinfo(String currentinfourl) {
		ArrayList<String> val=new ArrayList<String> ();
		String response ="";
		
		try {
	            // Step 1: Create a URL object
	            URL url = new URL(currentinfourl);

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
	            response = content.toString();
	            
	            
	            Gson gson = new Gson();

		        // Convert string to JSON object
		        JsonObject jsonResponsecs = gson.fromJson(response, JsonObject.class);
	            
	            System.out.println(response);
	            
	          //  String CpuLoadPercentage = jsonObject.get("CpuLoadPercentage").getAsString();
	            
	            val.add(jsonResponsecs.get("CpuLoadPercentage").getAsString());
	            val.add(jsonResponsecs.get("TotalMemoryMB").getAsString());;

	            val.add(jsonResponsecs.get("MemoryinuseMB").getAsString());;
	            val.add(jsonResponsecs.get("PercentageMemoryUse").getAsString());;

	            val.add(jsonResponsecs.get("TotalDiskSpaceGB").getAsString());;
	            val.add(jsonResponsecs.get("DiskSpaceinUseGB").getAsString());
	            val.add( jsonResponsecs.get("PercentageDiskUse").getAsString());
	            val.add(jsonResponsecs.get("Status").getAsString());

	           //System.out.println(VMlevel+"  - "+TotalDiskSpaceGB);
	            
	        } catch (Exception e) {
	        	
	        	Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps.FetchData Exception Handled "+e.toString(),2);
				
	            e.printStackTrace();
	        }
		return val;
	}
	
	
	public static ArrayList<String> printmachinfo(String inpurl) {
		ArrayList<String> val=new ArrayList<String> ();
		String response ="";
		
		try {
	            // Step 1: Create a URL object
	            URL url = new URL(inpurl);

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
	            response = content.toString();
	            
	            
	            Gson gson = new Gson();

		        // Convert string to JSON object
		        JsonObject jsonResponse = gson.fromJson(response, JsonObject.class);
	            
	            System.out.println(response);
	            
	          //  String CpuLoadPercentage = jsonObject.get("CpuLoadPercentage").getAsString();
	            
	            val.add(jsonResponse.get("VMMCode").getAsString());
	            val.add(jsonResponse.get("VMlevel").getAsString());

	            val.add(jsonResponse.get("MachineEnv").getAsString());
	            val.add(jsonResponse.get("OSName").getAsString());

	            val.add( jsonResponse.get("CpuCores").getAsString());
	            val.add( jsonResponse.get("TotalMemoryMB").getAsString());
	            val.add( jsonResponse.get("TotalDiskSpaceGB").getAsString());

	            
	           //System.out.println(VMlevel+"  - "+TotalDiskSpaceGB);
	            
	        } catch (Exception e) {
	        	
	        	Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps.FetchData Exception Handled "+e.toString(),2);
				
	            e.printStackTrace();
	        }
		return val;
	}
	
	public static String getmachinfo(String VMMname) {
		String finalurl="ERROR";
		String url = "ERROR";
		String secret = "ERROR";

		String sql = "SELECT `c3`,`c4` FROM `vmm-mastertable` WHERE `c7`=\"" + VMMname + "\";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				url = rs.getString(1);
				secret = rs.getString(2);
			}
			finalurl=url+":9000/remotemachine/"+secret+"/information";
			System.out.print(finalurl);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return finalurl;

	}
	
	public static String getcurrentstats(String VMMname) {
		String finalurl="ERROR";
		String url = "ERROR";
		String secret = "ERROR";

		String sql = "SELECT `c3`,`c4` FROM `vmm-mastertable` WHERE `c7`=\"" + VMMname + "\";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				url = rs.getString(1);
				secret = rs.getString(2);
			}
			finalurl=url+":9000/remotemachine/"+secret+"/monitoring/currentstats";
			System.out.print(finalurl);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return finalurl;

	}

	
}
