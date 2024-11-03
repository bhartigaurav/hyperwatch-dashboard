package com.aemcentral.hyperwatch.dashboard.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps;
import com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;

/**
 * Servlet implementation class Collectdata
 */
public class Collectdata extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Collectdata() {
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
		
		String MachineEnvironment = request.getParameter("machineEnv");
		ArrayList<String> VMMcodes = FetchDB.getallvmmcodes(MachineEnvironment);
		
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets [Collectdata] Data Collection Started for "+MachineEnvironment+" Envs!", 1);
		for(String x: VMMcodes) {
			DataOps.FetchData(x);
		}
		
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets [Collectdata] Data Collection Finished for "+MachineEnvironment+" Envs!", 1);
		
		response.getWriter().append("Done");
		
	}

}
