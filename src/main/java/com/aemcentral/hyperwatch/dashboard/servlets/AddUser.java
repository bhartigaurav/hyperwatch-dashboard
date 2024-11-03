package com.aemcentral.hyperwatch.dashboard.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.aemcentral.hyperwatch.dashboard.encryption.AES;
import com.aemcentral.hyperwatch.dashboard.encryption.Secrets;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;


//import com.aemcentral.logsmithanalyser.postrequests.Email;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
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
		String email = request.getParameter("username");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String password = "";
		
		String encpassword = AES.encrypt(email, "aemcentral");
		
try {
    		
    		String payload = 
    		        "{\n"
    		        + "    \"username\" : \""+email+"\", \n"
    		        + "    \"securitycode\" : \"s9jg3na\",\n"
    		        + "    \"appid\": \"apx3\",\n"
    		        + "    \"name\" : \""+name+"\",\n"
    		        + "    \"type\" : \""+type+"\",\n"
    		        + "    \"password\" : \""+encpassword+"\"\n"
    		        + "    \n"
    		        + "}";
    		        StringEntity entity = new StringEntity(payload,
    		                ContentType.APPLICATION_JSON);
    		       
    		        HttpClient httpClient = HttpClientBuilder.create().build();
    		        HttpPost logreq = new HttpPost(Secrets.getAuthurl()+"/auth/users/create");
    		        logreq.setEntity(entity);

    		        HttpResponse res = httpClient.execute(logreq);
    		        //System.out.println("Slack Message Status:");
    		        
    		        HttpEntity entity1 = res.getEntity();

    		        if (entity1 != null) {
    		            String content = EntityUtils.toString(entity1);
    		            JSONObject json = new JSONObject(content);
    		            // do something with the JSON object
    		           //System.out.print(json);
    		            Logg.writetofile( "[Create User Servlet]"+json.toString(), 1);
    		            
    		            if(json.getString("name").equals("invalid")  ) {
    		            	System.out.println("invalid");
    		            	response.sendRedirect("../../admin/usermanagement.jsp?msg=Invalid User Credential!");
    		            }
    		            else {
    		            	System.out.println("created");
    		            	response.sendRedirect("../../admin/usermanagement.jsp?msg=User Created Successfully!");
    		           
    		        } 
    		            
    		        }
    		        //(String providedby, String email, String username, String password, String name
  //  Enable afterwords		//       
    		       // Email.adduser("IP Admin", email+"@adobe.com", email, email, name) ;
    	}
    	catch(Exception e) {
    		response.sendRedirect("../../admin/usermanagement.jsp?msg=Invalid User Credential!");
    	}

	}

}