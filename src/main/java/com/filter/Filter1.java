package com.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

import com.models.User;

@WebFilter("/SignupServlett")
public class Filter1 extends HttpFilter implements Filter {
       
  
    public Filter1() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		User user = new User(userId, name, address, email, password);
		
		if(isValidate(user)) {
			
			chain.doFilter(req, res);
		}
		else {
			RequestDispatcher rd=req.getRequestDispatcher("signup.html");
			rd.include(req, res);
		}
		
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public Boolean isValidate(User user) {
		if(user.getPassword() !=null && user.getPassword().length() >= 8) {
			return true;
			
		}else {
			return false;
		}
		
		
	}
}
