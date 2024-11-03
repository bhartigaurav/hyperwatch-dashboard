<%@ page import="java.util.Scanner"%>
<%@ page import="java.io.File"%>
<%@ page import="com.aemcentral.hyperwatch.dashboard.logging.Logg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logs</title>
</head>
<body>

<%
  String name = (String) session.getAttribute("username");
  String ldap = (String) session.getAttribute("useremail");  
  String type = (String) session.getAttribute("usertype");
//Checking if the user has logged in and preventing Unauthorised access request
try {
	if(type.equalsIgnoreCase("USER")){
		response.sendRedirect("../vw/homepage.jsp");
	}
else if (name.equals(null)||name.equals("")) {
		response.sendRedirect("../vw/signin.jsp");
	}
} catch (Exception e) {
	//Logg.writetofile("Unauthorised access request at /weekend.oncall.ui/src/main/webapp/vw/vw-main.jsp",3);
	response.sendRedirect("../vw/signin.jsp");
}
%>

<%

String filepath ="C:\\outputs\\la-log.txt";

try{
File file = new File(filepath);
Scanner sc = new Scanner(file);

	while (sc.hasNextLine()) {
		String temp = sc.nextLine();
		%>
		<p><%out.println(temp); %></p>
		<%	
	}
	sc.close();
}
catch(Exception e){
	Logg.writetofile("at admin/view-analyser-logs.jsp Exception Handled ERROR While Reading File"+filepath+"", 2);
}
	%>

</body>
</html>