package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.sql.Connection;
import java.sql.DriverManager;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class Checks {

	//logging enabled
	public static void main(String Args[])
	{
	System.out.print(DbConnectivityCheck());
	}
	
	public static int DbConnectivityCheck() {
		int result=0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());

			result=1;
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.dbinteraction.Checks.DbConnectivityCheck ERROR", 2);
		}
		return result;
	}
}


