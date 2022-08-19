package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	//overriding httpservlet class doget which by default returns method not supported
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
{
	res.setStatus(200);
	res.getWriter().write("Hello World");
}	
}
	