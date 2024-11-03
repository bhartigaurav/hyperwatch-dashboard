package com.aemcentral.hyperwatch.dashboard.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aemcentral.hyperwatch.dashboard.dbinteraction.AddData;
import com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB;
import com.aemcentral.hyperwatch.dashboard.javaops.Domain;
import com.aemcentral.hyperwatch.dashboard.javaops.Initialise;
import com.aemcentral.hyperwatch.dashboard.javaops.Ping;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;

/**
 * Servlet implementation class InitialiseVM
 */
public class InitialiseVM extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitialiseVM() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		/*
		 * Steps: + Ping client domain +If available send post +else send error
		 * 
		 * logging enabled
		 * 
		 * Remaining - database entries , VMMCode generation
		 */
		
		String servletresponse = "ERROR";

		// fetch from Form
		String domain = request.getParameter("domain");
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM Initialization started for "+domain, 1);
		System.out.print(Domain.check(domain));
		if(Domain.check(domain)==1) {
	
		//get other parameters
		String MachineEnvironment = request.getParameter("machenv");
		String VMlevel = request.getParameter("machlev");
		String type = request.getParameter("type");
		
		VMlevel = type+"-"+VMlevel;
		
		String WebApp = request.getParameter("webapp");
		
		// From Initialise Class
		String Secret = Initialise.generateRandomAlphanumeric(8);
		
		//generate
		String VMMCode = FetchDB.VMMcode();
		
		int result = Ping.client(domain);
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM Domain - "+domain+" Ping Status: ", 1);
		
		if (result == 200) {
			
			Logg.writetofile("Creating InitFile for - "+domain, 1);
			
			//File creation
			int initop = Initialise.SendPost(domain, VMMCode, Secret, MachineEnvironment, VMlevel, WebApp);

			if (initop == 0) {
				//Error
				Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM Errror while creating InitFile for - "+domain, 2);
				
			}

			else if (initop == 1) {
				//success
				servletresponse="InitFile created successfully";
				Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM "+servletresponse+" - "+domain, 1);
				
				//Master Database - vmmcode, domain, secret, MachineEnvironment
				AddData.mastertable(domain, VMMCode, Secret, MachineEnvironment, VMlevel, WebApp);
				//Make entry to master database with Vmmcode anf machine env
				
				//Create DB table with name vmmcode
				AddData.createtable(VMMCode);
			}

			else if (initop == 2) {
				//file already exists
				servletresponse="File already exists";
				Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM "+servletresponse+" - "+domain, 2);
			}
			
			else if (initop == 3) {
				//Exception
				servletresponse="Process occured an Exception";
				Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM Exception Handled - "+domain, 2);
			}
			
		}
}
		
		else {
			//result =0 Domain invalid
			servletresponse = "Domain invalid";
			Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets.InitialiseVM "+servletresponse+" - "+domain, 2);
		}
		
		//change to initialise.jsp with servlet response as a query parameter
		response.sendRedirect("../../vw/initialise.jsp?result="+servletresponse);
		//response.getWriter().append(servletresponse);
	}

}
