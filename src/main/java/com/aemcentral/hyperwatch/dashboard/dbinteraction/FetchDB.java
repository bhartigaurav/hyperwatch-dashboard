package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class FetchDB {

	public static void main(String Args[]) {
		System.out.print(getvmmcodes());
	}

	public static int countall() {

		int temp = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `vmm-mastertable`;";

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
			 Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB.countall JDBC Connection ERROR", 2);
		}
		return temp;

	}
	
	public static int countdev() {

		int temp = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `vmm-mastertable` WHERE c5='dev' ;";

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
			 Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB.countdev JDBC Connection ERROR", 2);
		}
		return temp;

	}

	public static int countstage() {

		int temp = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `vmm-mastertable` WHERE c5='stage' ;";

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
			 Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB.countstage JDBC Connection ERROR", 2);
		}
		return temp;

	}

	public static int countprod() {

		int temp = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `vmm-mastertable` WHERE c5='prod' ;";

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
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return temp;

	}

	public static ArrayList<String> getvmmcodes() {
		ArrayList<String> VMMcodes = new ArrayList<String>();

		String sql = "SELECT `c2` FROM `vmm-mastertable` ;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				VMMcodes.add(rs.getString(1));

			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return VMMcodes;

	}

	
	public static ArrayList<String> getallvmmcodes(String machenv) {
		ArrayList<String> VMMcodes = new ArrayList<String>();

		String sql = "SELECT `c2` FROM `vmm-mastertable` WHERE `c5`='" + machenv + "' ;";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				VMMcodes.add(rs.getString(1));

			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return VMMcodes;

	}

	public static String getname(String VMMcode) {
		String name = "ERROR";

		String sql = "SELECT `c7` FROM `vmm-mastertable` WHERE `c2`=\"" + VMMcode + "\";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString(1);
			}

			//System.out.print(name);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return name;

	}

	public static String getdomain(String VMMcode) {
		String domain = "ERROR";

		String sql = "SELECT `c3` FROM `vmm-mastertable` WHERE `c2`=\"" + VMMcode + "\";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				domain = rs.getString(1);
			}

			System.out.print(domain);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return domain;

	}

	public static String getVMMcode(String VMMcode) {
		String vmmcode = "ERROR";

		String sql = "SELECT `c4` FROM `vmm-mastertable` WHERE `c2`=\"" + VMMcode + "\";";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement stmt = con.createStatement();

			// Retrieving values from table
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				vmmcode = rs.getString(1);
			}

			System.out.print(vmmcode);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return vmmcode;

	}

	public static String VMMcode() {
		String vmmcode = "vmm-";
		int temp = 0;
		String sql = "SELECT COUNT(*) AS total_rows FROM `vmm-mastertable`;";

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
			temp++;
			vmmcode = vmmcode + temp;
			System.out.print(vmmcode);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
			// JDBC Connection ERROR", 2);
		}
		return vmmcode;

	}

}
