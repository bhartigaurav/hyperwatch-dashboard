package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Fetchlast {

	
	//SELECT * FROM `vmm-2` ORDER BY c1  LIMIT 1;
	public static void main(String Args[]) {
	System.out.print(getdisk("vmm-2"));
	}

public static String getdisk(String VMMcode) {
		
		String sql = "SELECT * FROM `"+VMMcode+"` ORDER BY C1 LIMIT 15;";
		String disk="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			
			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				disk=disk+rs.getString(8)+",";
				//System.out.println(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(5)+" - "+rs.getString(8));
			}
			
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.Fetchlast.getdisk JDBC Connection ERROR", 2);
		}
		return disk;
	
	}
	
public static String getmem(String VMMcode) {
		
		String sql = "SELECT * FROM `"+VMMcode+"` ORDER BY C1 LIMIT 15;";
		String mem="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			
			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				mem=mem+rs.getString(5)+",";
				//System.out.println(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(5)+" - "+rs.getString(8));
			}
			
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.Fetchlast.getmem JDBC Connection ERROR", 2);
		}
		return mem;
	
	}
	
	public static String getcpu(String VMMcode) {
		
		String sql = "SELECT * FROM `"+VMMcode+"` ORDER BY C1 LIMIT 15;";
		String cpu="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			
			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cpu=cpu+rs.getString(2)+",";
				//System.out.println(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(5)+" - "+rs.getString(8));
			}
			
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.Fetchlast.getcpu JDBC Connection ERROR", 2);
		}
		return cpu;
	
	}
	
	public static String gettimeinfo(String VMMcode) {
	
		String sql = "SELECT * FROM `"+VMMcode+"` ORDER BY C1 LIMIT 15;";
		String time="";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			
			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				time=time+"'"+rs.getString(1)+"',";
				//System.out.println(rs.getString(1)+" - "+rs.getString(2)+" - "+rs.getString(5)+" - "+rs.getString(8));
			}
			
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.Fetchlast.getvalues JDBC Connection ERROR", 2);
		}
		return time;
	
	}
	
	public static ArrayList<String> getlastvalues(String VMMcode) {
		
		 ArrayList<String> values =new  ArrayList<String>();
		 values.add(FetchDB.getname(VMMcode));
		/*String c1 = "ERROR";String c2 = "ERROR";String c3 = "ERROR";
		String c4 = "ERROR";String c5 = "ERROR";String c6 = "ERROR";
		String c7 = "ERROR";String c8 = "ERROR";String c9 = "ERROR";*/

		String sql = "SELECT * FROM `"+VMMcode+"` ORDER BY c1 DESC LIMIT 1";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				values.add(rs.getString(1));
				values.add(rs.getString(2));
				values.add(rs.getString(3));
				values.add(rs.getString(4));
				values.add(rs.getString(5));
				values.add(rs.getString(6));
				values.add(rs.getString(7));
				values.add(rs.getString(8));
				values.add(rs.getString(9));
				
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			 Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.Fetchlast.getvalues JDBC Connection ERROR", 2);
		}
		return values;

	}
	
}
