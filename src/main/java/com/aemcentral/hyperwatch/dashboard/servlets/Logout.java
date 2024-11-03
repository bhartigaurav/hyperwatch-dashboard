package com.aemcentral.hyperwatch.dashboard.servlets;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session=request.getSession();

		String name=(String)session.getAttribute("username");
		//String email = (String) session.getAttribute("useremail");

		if(name==null){
			response.sendRedirect("../../vw/signin.jsp");

		}
		session.removeAttribute("startupMsg");
		session.invalidate();
		response.sendRedirect("../../vw/signin.jsp?msg=Logout Successful");

		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session=request.getSession();

		String name=(String)session.getAttribute("username");
		//String email = (String) session.getAttribute("useremail");

		if(name==null){
			response.sendRedirect("../../vw/signin.jsp");

		}

		session.invalidate();
		response.sendRedirect("../../vw/signin.jsp?msg=success");

		//doGet(request, response);
	}

}