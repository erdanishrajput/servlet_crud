package com.controller;

import java.io.IOException;


import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.dao.UserDao;
import com.models.User;
@WebServlet("/ReadServlett")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao;
  
    public ReadServlet() {
        super();
        dao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false); 
	        if (session == null || session.getAttribute("user") == null) {
	            // Redirect to login page if no session or user is found
	            response.sendRedirect("login.html");
	            return;
	        }
		try {
			List<User> users = dao.getUsers();
			request.setAttribute("users", users);
			
			RequestDispatcher rd = request.getRequestDispatcher("data.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}