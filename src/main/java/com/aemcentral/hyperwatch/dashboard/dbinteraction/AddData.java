package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;

public class AddData {

	public static int alerts( String VMMCode, String Status) {
		
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		String formattedDate = myDateObj.format(myFormatObj);
		
		//Add date and time of addition
		String sql="INSERT INTO `alerts`(`c1`, `c2`, `c3`) VALUES ('"+formattedDate+"','"+VMMCode+"','"+Status+"')";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement st = con.createStatement();

			st.executeUpdate(sql);

			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.AddData.alerts JDBC ERROR "+e.toString(), 2);

		}
		
		return 0;
	}
		
	
	public static int createtable(String VMMCode) {
		
		String sql = "CREATE TABLE `hyperwatch`.`"+VMMCode+"` ( `c1` TEXT NOT NULL ,  `c2` TEXT NOT NULL ,  `c3` TEXT NOT NULL ,  `c4` TEXT NOT NULL ,  `c5` TEXT NOT NULL ,  `c6` TEXT NOT NULL ,`c7` TEXT NOT NULL ,`c8` TEXT NOT NULL ,  `c9` TEXT NOT NULL ) ENGINE = InnoDB;";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement st = con.createStatement();

			st.executeUpdate(sql);

			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.AddData.createtable JDBC ERROR "+e.toString(), 2);

		}
			return 0;
		}
	
	public static int mastertable(String domain, String VMMCode, String Secret, String MachineEnvironment, String VMlevel, String WebApp) {
		
		LocalDateTime myDateObj = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

		String formattedDate = myDateObj.format(myFormatObj);
		
		//Add date and time of addition
		String sql="INSERT INTO `vmm-mastertable`(`c1`, `c2`, `c3`, `c4`, `c5`, `c6`, `c7`, `c8`) VALUES ('"+formattedDate+"','"+VMMCode+"','"+domain+"','"+Secret+"','"+MachineEnvironment+"','"+VMlevel+"','"+WebApp+"','1')";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement st = con.createStatement();

			st.executeUpdate(sql);

			con.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.AddData.mastertable JDBC ERROR "+e.toString(), 2);

		}
		
		return 0;
	}
	
}
