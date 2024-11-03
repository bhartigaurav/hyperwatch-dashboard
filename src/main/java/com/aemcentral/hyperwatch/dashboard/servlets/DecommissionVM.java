package com.aemcentral.hyperwatch.dashboard.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aemcentral.hyperwatch.dashboard.dbinteraction.DeleteFns;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;

/**
 * Servlet implementation class DecommissionVM
 */
public class DecommissionVM extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DecommissionVM() {
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
		
		//fetch VMMcode
		String VMMcode = request.getParameter("VMMcode");
		
		String servletresponse ="Decommissioned Successfully!";
		
		String resultrow = DeleteFns.mastertabledeleterow(VMMcode);
		
		String resulttable = DeleteFns.mastertabledeleterow(VMMcode);
		
		if(resultrow.equals("Row Deleted") && resulttable.equals("Table Deleted")) {
			servletresponse = VMMcode+" Decommissioned Successfully!";
		}
		else {
			servletresponse = "Decommissioning "+VMMcode+" Resulted into Error!";
		}
		
		Logg.writetofile("com.aemcentral.hyperwatch.dashboard.servlets [DecommissionVM] - "+servletresponse, 2);

		response.sendRedirect("../../../vw/initialise.jsp?result="+servletresponse);
	}

}
