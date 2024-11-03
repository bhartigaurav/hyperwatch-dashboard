package com.aemcentral.hyperwatch.dashboard.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aemcentral.hyperwatch.dashboard.dbinteraction.DataOps;
import com.aemcentral.hyperwatch.dashboard.dbinteraction.FetchDB;
import com.aemcentral.hyperwatch.dashboard.logging.Logg;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Counts
 */
public class Counts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Counts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int dev =FetchDB.countdev();
		int stage = FetchDB.countstage();
		int prod= FetchDB.countprod();
		int all=FetchDB.countall();
		
		int active = 0;
		ArrayList<String> VMMcodes = FetchDB.getvmmcodes();
		
		for(String x: VMMcodes) {
			active += DataOps.Fetchactive(x);
		}
		
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("total", all);
        jsonObject.addProperty("dev", dev);
        jsonObject.addProperty("stage", stage);
        jsonObject.addProperty("prod", prod);
        jsonObject.addProperty("active", active);
        
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonObject);
        
        System.out.println(jsonString);
     // Set response content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Write JSON object to response
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//doGet(request, response);
	}

}
