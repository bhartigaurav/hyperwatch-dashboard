package com.aemcentral.hyperwatch.dashboard.dbinteraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.aemcentral.hyperwatch.dashboard.logging.Logg;

public class DeleteFns {

	public static void main(String Args[]) {
		vmmtable("vmm-5");
	}

	public static String mastertabledeleterow(String VMMcode) {
		String result = "Error";

		String sql = "DELETE FROM `vmm-mastertable` WHERE `c2`='" + VMMcode + "';";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement st = con.createStatement();

			st.executeUpdate(sql);

			con.close();
			result = "Row Deleted";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logg.writetofile(
					" com.aemcentral.hyperwatch.dashboard.dbinteraction.DeleteFns.mastertabledeleterow JDBC ERROR - "
							+ e.toString(),
					2);
			result = "Exception";
		}
		return result;
	}

	public static String vmmtable(String VMMcode) {
		String result = "error";

		String sql = "DROP TABLE `"+VMMcode+"`;";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
					Credentials.getDbpass());
			Statement st = con.createStatement();

			st.executeUpdate(sql);

			con.close();
			result = "Table Deleted";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logg.writetofile(" com.aemcentral.hyperwatch.dashboard.dbinteraction.DeleteFns.vmmtable JDBC ERROR - "
					+ e.toString(), 2);
			result = "Exception";
		}
		return result;
	}

}
