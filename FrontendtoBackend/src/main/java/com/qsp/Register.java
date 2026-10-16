package com.qsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Register implements Servlet {

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id"); //here i parameter we pass value of name attribute in input field
		//have to convert id into integer
		int id1 = Integer.parseInt(id);
		
		String name = req.getParameter("name");
		
		String email = req.getParameter("email");
		
		String mobno = req.getParameter("mobno");
		long mobno1 = Long.parseLong(mobno);
		
		String desc = req.getParameter("desc");
		
		String sal = req.getParameter("sal");
		double sal1 = Double.parseDouble(sal);
		
		System.out.println(id1);
		System.out.println(name);
		System.out.println(email);
		System.out.println(mobno1);
		System.out.println(desc);
		System.out.println(sal1);
		
		//To print data on web page
		PrintWriter writer = res.getWriter();
		writer.print("service method executed successfully");
		
		
		writer.print(id1);
		writer.println();
		
		writer.print(name);
		writer.println();
		
		writer.print(email);
		writer.println();
		
		writer.print(mobno);
		writer.println();
		
		writer.print(desc);
		writer.println();
		
		writer.print(sal1);
		writer.println();
		
		
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
