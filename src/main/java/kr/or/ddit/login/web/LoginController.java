package kr.or.ddit.login.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.service.MemberService;
import kr.or.ddit.user.service.MemberServiceI;

/*
 * web.xml에 설정하는 servlet, servlet-mapping을 이노테잉션을 통해 설정하는 방법
 */
@WebServlet("/loginController")
public class LoginController extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private MemberServiceI userService = new MemberService();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userid = req.getParameter("userid");
		String pass = req.getParameter("pass");
		
		logger.debug("userid : "+userid);
		logger.debug("pass : "+pass);
		
		MemberVo user = userService.selectUser(userid);
		HttpSession session = req.getSession();
		// 로그인 성공 ==> service를 통해 데이터베이스에 저장된 값과 일치할 때
		// session에 데이터베이스에서 조회한 사용자 정보(userVo)를 저장
		if(user != null && pass.equals(user.getPass())) {
			session.setAttribute("S_USER", user);
			resp.sendRedirect(req.getContextPath()+"/pagingMember.do");
			logger.debug("로그인 성공");
			logger.debug(req.getContextPath()+"/pagingMember.do");
		}
		// 로그인 실패
		else {
//			req.getRequestDispatcher(req.getContextPath()+"/jsp/login.jsp").forward(req, resp);
			req.getRequestDispatcher(req.getContextPath()+"/jsp/login.jsp").forward(req, resp);
			logger.debug("로그인 실패");
		}
				
		
		
		
	}
}
