package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbConnectivity.DbHandler;
import model.User;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search controller")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parameter = request.getParameter("empid");
		ArrayList<User> users= null;
		User user =null;
		int empid = 0;
		try{
			empid = Integer.parseInt(parameter);
		}catch(NumberFormatException e){
			response.setContentType("text/html");
			response.getWriter().write("invalid id, please enter integer<br> <a href=\"index.html\"> back</a>");
			return;
		}
		
		DbHandler db = new DbHandler("demo", "root", "root");
		if(empid==0){
			users = db.getAllUsers();
			System.out.println("users found: "+users.size());
			response.setContentType("text/html");
			request.setAttribute("myUsers", users);
			
			//request.setAttribute("outWriter", response.getOutputStream());
			RequestDispatcher rd = request.getRequestDispatcher("SearchResult.jsp"); 
			//System.out.println("dispather assigned");
			rd.forward(request, response);
		}
		else{
			user = db.searchUser(""+empid);
			users = new ArrayList<User>();
			users.add(user);
			System.out.println("user added: " +user);
			request.setAttribute("myUsers", users);
			RequestDispatcher rd = request.getRequestDispatcher("SearchResult.jsp"); 
			rd.forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
