package com.aemcentral.hyperwatch.dashboard.alerts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.aemcentral.hyperwatch.dashboard.dbinteraction.Credentials;
import com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class Analyse {

	public static void main(String Args[]) {
		updatec8("vmm-3","1");
	}
	
	public static void merticalert(String VMMcode,  String Status ) {
		
		String result ="";
		String notify = getnotifyval(VMMcode);
		String lastmetrc = getlastmetrc(VMMcode);
		if(notify.equals("1")) {
			
			
			if(lastmetrc.equals("metrics under control")||lastmetrc.equals("Not Available")) {
				
			}
			
			else if(Status.equals(lastmetrc) ){
				
				//Slack message
				
				Slack.metricmessage(FetchDB.getname(VMMcode), Status);
				//Update c8 in master table to 0
				 result = updatec8(VMMcode, "0");
				
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.alerts.Analyse.merticalert [Notify] Status- "+Status +" | Last Metric - "+lastmetrc+" | updation[0] result - "+result,1);
				
				
			}
			
		}
		
		if(notify.equals("0")) {
			
			if(lastmetrc.equals("metrics under control") && Status.equals("metrics under control")) {
				
				//Update c8 in master table to 1
				
				 result = updatec8(VMMcode, "1");
				 
	 Logg.writetofile("com.aemcentral.hyperwatch.dashboard.alerts.Analyse.merticalert [Update] Status- "+Status +" | Last Metric - "+lastmetrc+" | updation[1] result - "+result,1);
						
				
			}
			
			
		}
		
		
	}
	
	//
	
	
	public static String updatec8(String VMMcode, String Val) {
		String result="error";
		
		String sql="UPDATE `vmm-mastertable` SET `c8`='"+Val+"' WHERE `c2`='"+VMMcode+"';";
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
				Credentials.getDbpass());
		Statement st = con.createStatement();

		st.executeUpdate(sql);

		con.close();
		result="updated";
	} catch (Exception e) {
		// TODO Auto-generated catch block
	Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.alerts.Analyse.updatec8 JDBC ERROR - "+e.toString(), 2);
	result="exception";
	}
	return result;
	}
	
	public static String getnotifyval(String VMMcode) {
		String notifyval = "ERROR";

		String sql = "SELECT `c8` FROM `vmm-mastertable` WHERE `c2`= '"+VMMcode+"';";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				notifyval = rs.getString(1);
			}

			//System.out.print(notifyval);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.alerts.Analyse.getnotifyval JDBC ERROR - "+e.toString(), 2);
		}
		return notifyval;

	}
	
	
	public static String getlastmetrc(String VMMcode) {
		String lastmetrc = "ERROR";

		String sql = "SELECT `c9` FROM `"+VMMcode+"` ORDER BY c1 DESC LIMIT 1;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				lastmetrc = rs.getString(1);
			}

			//System.out.print(lastmetrc);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.alerts.Analyse.getlastmetrc JDBC ERROR - "+e.toString(), 2);
		}
		return lastmetrc;

	}
	
}
