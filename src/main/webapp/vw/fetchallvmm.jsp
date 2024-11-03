<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import=" java.sql.DriverManager" %>
<%@ page import=" java.sql.ResultSet" %>
<%@ page import=" java.sql.Statement" %>
<%@ page import="com.aemcentral.hyperwatch.dashboard.dbinteraction.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>
<table>

 <tr>
 <th>Webapp</th>
  <th>TimeStamp</th>
  <th>CpuLoadPercentage</th>
   <th>TotalMemory(MB)</th>
    <th>Memoryinuse(MB)</th>
     <th>PercentageMemoryUse</th>
      <th>TotalDiskSpace(GB)</th>
       <th>DiskSpaceinUse(GB)</th>
       <th>PercentageDiskUse</th>
        <th>Status</th>
 </tr>


<%
try{
//{"VMMCode":"vmm-1","CpuLoadPercentage":1,"TotalMemoryMB":46079.0,"MemoryinuseMB":14112.0,"PercentageMemoryUse":30,"TotalDiskSpaceGB":149,
	//"DiskSpaceinUseGB":28,"PercentageDiskUse":18,"Status":"metrics under control"}
ArrayList<String> VMMcodes = FetchDB.getvmmcodes();

for(String x: VMMcodes ){
	
	 ArrayList<String> temp= Fetchlast.getlastvalues(x);
	 {
		 %>
		 <tr>
		 <td><%out.println(temp.get(0)); %></td>
		 <td><%out.println(temp.get(1)); %></td>
		 
		  <td><%out.println(temp.get(2)); %></td>
		 <td><%out.println(temp.get(3)); %></td>
		 
		  <td><%out.println(temp.get(4)); %></td>
		 <td><%out.println(temp.get(5)); %></td>
		 
		 <td><%out.println(temp.get(6)); %></td>
		 <td><%out.println(temp.get(7)); %></td>
		 
		  <td><%out.println(temp.get(8)); %></td>
		    <td><%out.println(temp.get(9)); %></td>
		 
		 
		 </tr>
		 <br>
		 <% 
		 
		// out.println(temp.get(1));
			
	 }
}
}
catch (Exception e){
	
}

%>
</table>

<h3>Alerts</h3>

<table>
<th>Timestamp</th>
<th>Webapp</th>
<th>Alert</th>

<tr>

</tr>

<%

	String sql = "SELECT * FROM `alerts`";

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(Credentials.getDburl(), Credentials.getDbusr(),
				Credentials.getDbpass());
		Statement stmt = con.createStatement();

		// Retrieving values from table
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			%>
			 <tr>
		 <td><%=rs.getString(1) %></td>
		  <td><%= FetchDB.getname(rs.getString(2)) %></td>
		   <td><%=rs.getString(3) %></td>
		 </tr>
			<% 
			 
		}

		
		con.close();

	} catch (Exception e) {
		e.printStackTrace();
		// Logg.writetofile("at com.aemcentral.instance.portal.dbinteraction.serialno()
		// JDBC Connection ERROR", 2);
	}
	


%>
</table>

</body>
</html>