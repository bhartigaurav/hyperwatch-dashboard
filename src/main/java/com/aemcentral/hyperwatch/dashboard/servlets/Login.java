package com.aemcentral.hyperwatch.dashboard.servlets;

//Author Gaurav Bharti

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;

import com.aemcentral.hyperwatch.dashboard.encryption.Secrets;

//import com.aemcentral.instance.portal.dbinteraction.Track;


/**
* Servlet implementation class Login
*/
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Login() {
      super();
      // TODO Auto-generated constructor stub
  }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Forbidden GET! Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		String url = "";
		if((String) request.getParameter("url")!=null)
		 url = ((String) request.getParameter("url")).substring(22); 
		else 
		 url = "homepage.jsp";
		
		String path= "";
		String fname= "";
		if((String) request.getParameter("path")!=null)
			path = "&path=" +(String) request.getParameter("path");
		if((String) request.getParameter("fname")!=null)
			fname = "&fname=" +(String) request.getParameter("fname");
		
		
		
		String email = request.getParameter("username");

		String password = request.getParameter("password");
		
		//New API based Authentication
		try {
  		
  		String payload = 
  		        "{\n"
  		        + "    \"username\" : \""+email+"\", \n"
  		        + "    \"securitycode\" : \"s9jg3na\",\n"
  		        + "    \"appid\": \"apx3\",\n"
  		        + "    \"password\" : \""+password+"\"\n"
  		        + "}\n"
  		        + "";
  		        StringEntity entity = new StringEntity(payload,
  		                ContentType.APPLICATION_JSON);
  		       
  		        HttpClient httpClient = HttpClientBuilder.create().build();
  		        
  		        HttpPost logreq = new HttpPost(Secrets.getAuthurl()+"/auth/cred/login");
  		        logreq.setEntity(entity);

  		        HttpResponse res = httpClient.execute(logreq);
  		        System.out.println("Login:");
  		        
  		        HttpEntity entity1 = res.getEntity();

  		        if (entity1 != null) {
  		            String content = EntityUtils.toString(entity1);
  		            JSONObject json = new JSONObject(content);
  		            // do something with the JSON object
  		            HttpSession session=request.getSession();
  		            if(json.getString("name").equals("invalid creds")  ) {
  		            	//Track.useractivity(email, "Failed Login Attempt");
  		            	response.sendRedirect("../../vw/signin.jsp?msg=Invalid Login Credential!");
  		            }
  		            else {
  		            	session.removeAttribute("startupMsg");
  					session.setAttribute("username", json.getString("name"));
  					session.setAttribute("usertype", json.getString("role"));
  					session.setAttribute("feedback", "false");
  					session.setAttribute("useremail", email);
  					
  					//login track removed temporarily
  					//Track.useractivity(email, "Login Success");
  		           // System.out.println(json.getString("name")+"usertype"+ json.getString("role"));
  		            //System.out.println("Redirecting to: "+url );
  		            response.sendRedirect("../../vw/"+url+path+fname);
  		        } }
  		    
  		       
  	}
  	catch(Exception e) {
  		
  	}
	}

}