package com.rudra;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        urlPatterns = "/add", 
        initParams = {
                @WebInitParam(name = "name", value = "abc"),
                @WebInitParam(name = "lastname", value = "def")
        }
)
public class AddServlet extends HttpServlet {

	
////// service() method demo

//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("inside service method...");
//		
//		int num1 = Integer.parseInt(req.getParameter("num1"));
//		int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		int result = num1 + num2;
//		
//		PrintWriter out = resp.getWriter();
//		out.println("result is :" + result);
//	}	

	
////// post() method demo
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("inside post method...");
//		
//		int num1 = Integer.parseInt(req.getParameter("num1"));
//		int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		int result = num1 + num2;
//		
//		PrintWriter out = resp.getWriter();
//		out.println("result is :" + result);
//	}
	
////// RequestDispatcher Demo

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("inside post method...");
//		
//		int num1 = Integer.parseInt(req.getParameter("num1"));
//		int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		int result = num1 + num2;
//		
//		req.setAttribute("num3", result);
//		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/square");
//		requestDispatcher.forward(req, resp);
//	}
	
////// sendRedirect Demo - using url redirecting
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("inside get method...");
//		
//		int num1 = Integer.parseInt(req.getParameter("num1"));
//		int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		int result = num1 + num2;
//		
//		resp.sendRedirect("square?num3="+result); 
//	}
	
////// sendRedirect Demo - using session
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("inside get method...");
//		
//		int num1 = Integer.parseInt(req.getParameter("num1"));
//		int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		int result = num1 + num2;
//		
//		HttpSession session = req.getSession();
//		session.setAttribute("num3", result);
//		resp.sendRedirect("square"); 
//	}
	
////// sendRedirect Demo - using cookies
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("inside get method...");
//		
//		int num1 = Integer.parseInt(req.getParameter("num1"));
//		int num2 = Integer.parseInt(req.getParameter("num2"));
//		
//		int result = num1 + num2;
//		
//		Cookie cookie = new Cookie("num3", String.valueOf(result));
//		resp.addCookie(cookie);
//		
//		resp.sendRedirect("square"); 
//	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1 = Integer.parseInt(req.getParameter("num1"));
		int num2 = Integer.parseInt(req.getParameter("num2"));
		int result = num1 + num2;
		resp.getWriter().println("result is :" + result);
		
		ServletContext ctx = getServletContext();
		resp.getWriter().println("Context Param - Name: "+ ctx.getInitParameter("name"));
		
		ServletConfig config = getServletConfig();
		resp.getWriter().println("Config Param - Name: "+ config.getInitParameter("name"));
		
	}
	
}
