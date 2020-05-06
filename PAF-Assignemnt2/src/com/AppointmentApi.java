package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;;

/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/AppointmentAPI")
public class AppointmentApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	Appointment appointObj = new Appointment();
	
    public AppointmentApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = appointObj.insertAppointment(request.getParameter("name"), 
				request.getParameter("mobile"), 
				request.getParameter("email"), 
				request.getParameter("nic"), 
				request.getParameter("address"), 
				request.getParameter("date"), 
				request.getParameter("hospital"), 
				request.getParameter("doctor"), 
				request.getParameter("msg"));
		
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<String, String> param = getParasMap(request);
		
		String result = appointObj.updateAppointment(param.get("hidAppIDSave").toString(),
                param.get("name").toString().toString().replace("+", " "),    
                 param.get("mobile").toString(),       
                 param.get("email").toString().toString().replace("%40", "@"),       
                 param.get("nic").toString(),
                 param.get("address").toString().toString().replace("+", " "),
                 param.get("date").toString().toString().replace("+", " "),
                 param.get("hospital").toString().toString().replace("+", " "),
                 param.get("doctor").toString().toString().replace("+", " "),
                 param.get("msg").toString().toString().replace("+", " ") );
       
        response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<String, String> param = getParasMap(request);
		
		String result = appointObj.deleteAppointment(param.get("appID").toString());
		
		response.getWriter().write(result);
	}

}
