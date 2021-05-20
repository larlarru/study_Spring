package kr.or.ddit.servlet.scope;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/scope")
public class Scope extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher(req.getContextPath()+"/jsp/scope.jsp").forward(req, resp);
//		req.getRequestDispatcher(req.getContextPath()+"/scope.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		ServletContext application = req.getServletContext(); 
		
		String scope = req.getParameter("scope");
		
//		String reqP = req.getParameter("request");
//		String sessionP = req.getParameter("session");
//		String appP = req.getParameter("application");
		
		req.setAttribute("request", scope + "_request");
		session.setAttribute("session", scope + "_session");
		application.setAttribute("application", scope + "_application");
//		req.setAttribute("request", reqP + "_request");
//		session.setAttribute("session", sessionP + "_session");
//		application.setAttribute("application", appP + "_application");
		
		req.getRequestDispatcher(req.getContextPath()+"/jsp/scope.jsp").forward(req, resp);
		
	}

}
