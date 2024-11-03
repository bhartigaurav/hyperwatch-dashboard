package com.aemcentral.hyperwatch.dashboard.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteUser
 */



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

/**
 * Servlet implementation class Deleteuser
 */
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("user");
		String username="";
		try {
			String payload = 
    		        "{\n"
    		        + "    \"username\" : \""+email+"\", \n"
    		        + "    \"securitycode\" : \"s9jg3na\",\n"
    		        + "    \"appid\": \"apx3\"\n"
    		        + "}";
    		        StringEntity entity = new StringEntity(payload,
    		                ContentType.APPLICATION_JSON);
    		       
    		        HttpClient httpClient = HttpClientBuilder.create().build();
    		        HttpPost logreq = new HttpPost(Secrets.getAuthurl()+"/auth/list/userid");
    		        logreq.setEntity(entity);        
    		        HttpResponse res = httpClient.execute(logreq);
    		        HttpEntity user = res.getEntity();
    		       username = EntityUtils.toString(user);
		}
		catch(Exception e) {
			
		}
		
try {
    		String payload = 
    		        "{\n"
    		        + "    \"username\" : \""+username+"\", \n"
    		        + "    \"securitycode\" : \"s9jg3na\",\n"
    		        + "    \"appid\": \"apx3\"\n"
    		        + "}";
    		        StringEntity entity = new StringEntity(payload,
    		                ContentType.APPLICATION_JSON);
    		       
    		        HttpClient httpClient = HttpClientBuilder.create().build();
    		        HttpPost logreq = new HttpPost(Secrets.getAuthurl()+"/auth/users/delete");
    		        logreq.setEntity(entity);

    		        HttpResponse res = httpClient.execute(logreq);
    		        //System.out.println("Slack Message Status:");
    		        
    		        HttpEntity entity1 = res.getEntity();

    		        if (entity1 != null) {
    		            String content = EntityUtils.toString(entity1);
    		            JSONObject json = new JSONObject(content);
    		            // do something with the JSON object
    		           //System.out.print(json);
    		   
    		            if(json.getString("result").equals("invalid")  ) {
    		            	System.out.println("invalid");
    		            	response.sendRedirect("../../admin/usermanagement.jsp?msg=Invalid User Credential!");
    		            }
    		            else {
    		            	System.out.println("deleted");
    		            	response.sendRedirect("../../admin/usermanagement.jsp?msg=User Deleted Successfully!");
    		           
    		        } }
    		         
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		response.sendRedirect("../../admin/usermanagement.jsp?msg=Invalid User Credential!");
    	}


	}

}