package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.service.MemberService;
import kr.or.ddit.user.service.MemberServiceI;

@WebServlet("/member")
public class MemberController extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private MemberServiceI userService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String userid = req.getParameter("userid");
		logger.debug("userid : " + userid);
		
		MemberVo member = userService.selectUser(userid);
		
		req.setAttribute("member", member);
		logger.debug("userVo의 userid 값 : "+member.getUserid());
		
		req.getRequestDispatcher("/jsp/memberInfo.jsp").forward(req, resp);
//		req.getRequestDispatcher(req.getContextPath()+"/jsp/memberInfo.jsp").forward(req, resp);
		
	}
	
}
