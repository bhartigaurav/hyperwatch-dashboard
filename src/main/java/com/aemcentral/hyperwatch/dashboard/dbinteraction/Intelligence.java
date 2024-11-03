package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class Intelligence {

	public static void main(String Args[]) {
	System.out.println(countalerts("vmm-2"));
	
	}
	
	public static int countalerts(String VMMCode) {
		int temp = 0;
		String sql="SELECT COUNT(*) FROM `"+VMMCode+"` WHERE c9 NOT IN ('metrics under control', 'Not Available');";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				temp = rs.getInt(1);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.Intelligence.countnotavailable JDBC Connection ERROR", 2);
		}
		return temp;

	}
	
	private static final DecimalFormat decfor = new DecimalFormat("0.00");  
	
	public static String percentageuptime(String VMMCode) {
		double uptime =0;
		int countall=countall( VMMCode) ;
		int countavailable= countall - countnotavailable( VMMCode);
		
		uptime = ( (double)countavailable/(double)countall)*100;
		
		return decfor.format(uptime);
	}
	
	
	public static int countnotavailable(String VMMCode) {

		int temp = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `"+VMMCode+"` WHERE `c9`='Not Available';";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				temp = rs.getInt(1);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.Intelligence.countnotavailable JDBC Connection ERROR", 2);
		}
		return temp;

	}
	
	public static int countall(String VMMCode) {

		int count = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `"+VMMCode+"`;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.Intelligence.countall JDBC Connection ERROR", 2);
		}
		return count;

	}
	
	
}

