package com.aemcentral.hyperwatch.dashboard.dbinteraction;

public class Credentials {

	
	public final static String DBURL="jdbc:mysql://10.42.34.84:3306/hyperwatch";
	public final static String DBUSR="aemcreator";
	public final static String DBPASS="aemcreator";
	
	public static String getDburl() {
		return DBURL;
	}
	public static String getDbusr() {
		return DBUSR;
	}
	public static String getDbpass() {
		return DBPASS;
	}

	
}
