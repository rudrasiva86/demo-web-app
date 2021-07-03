package com.rudrasiva86;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Demo for annotation based servlet configuration
 * 
 * @author siva
 *
 */
@WebServlet("/square")
public class SquareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("inside square...");
//		int num3 = (int)request.getAttribute("num3");
//		int result = num3 * num3;
//		PrintWriter out = response.getWriter();
//		out.println("Square value is :" + result);
//	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("inside square...");
//		int num3 = Integer.parseInt(request.getParameter("num3"));
//		int result = num3 * num3;
//		PrintWriter out = response.getWriter();
//		out.println("Square value is :" + result);
//	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int num3 = (int)request.getSession().getAttribute("num3");
//		System.out.println("value in session : " + num3);
//		int result = num3 * num3;
//		PrintWriter out = response.getWriter();
//		out.println("Square value is :" + result);
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num3 = Stream.of(request.getCookies()).filter(c -> c.getName().equals("num3")).map(c -> c.getValue()).findFirst().get();
		System.out.println("cookie value : " + num3);
		int result = Integer.parseInt(num3) * Integer.parseInt(num3);
		PrintWriter out = response.getWriter();
		out.println("Square value is :" + result);
	}

}
