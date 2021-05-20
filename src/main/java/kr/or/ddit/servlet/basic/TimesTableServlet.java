package kr.or.ddit.servlet.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class TimesTableServlet
 */
public class TimesTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(BasicServlet.class);
	
	
    public TimesTableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<html>");
		pw.println("	<head>");
		pw.println("		<title>TimesTableServlet</title>");
		pw.println("	<style>");
		pw.println("		table{");
		pw.println("			width : 100%;");
		pw.println("		}");
		pw.println("	</style>");
		pw.println("	</head>");
		pw.println("	<body>");
		pw.println("		<table border='1'>");
		
		for (int i = 1; i < 10; i++) {
			pw.println("			<tr>");
			for (int j = 2; j < 10; j++) {
			
				pw.println("				<td>"+ i + " * " + j + " = " + i*j + "</td>");
			}
			pw.println("			</tr>");
			
		}
		pw.println("		</table>");
		pw.println("	</body>");
		pw.println("</html>");
		
		
		pw.flush();
		pw.close();
		
		
		
	}

}
