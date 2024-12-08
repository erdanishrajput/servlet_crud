package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.models.User;

@WebServlet("/SignupServlett")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao dao;


    public SignUpServlet() {
        super();
        dao = new UserDao();
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userId = req.getParameter("userId"); // we will get here database value
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User(userId, name, address, email, password);
		try {
			dao.saveUser(user);
//			HttpSession session = req.getSession();
//			session.setAttribute("user", user);
			req.getRequestDispatcher("login.html").forward(req, res);

		}catch(SQLException e) {
			e.printStackTrace();
            RequestDispatcher rd = req.getRequestDispatcher("signup.html");
			rd.include(req, res);
			
		}
		
	}

	
}