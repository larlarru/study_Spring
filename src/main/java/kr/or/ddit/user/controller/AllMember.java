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

@WebServlet("/allMember.do")
public class AllMember extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(AllMember.class);
	
//	private UserServiceI userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberServiceI memberService = new MemberService();
		
		List<MemberVo> memberList = memberService.selectAllUser();
		
		if(memberList==null) logger.debug("memberList null");
		else logger.debug("memberList not null");
		
		req.setAttribute("memberList", memberList);
		
//		req.getRequestDispatcher(req.getContextPath()+"/jsp/allmember.jsp").forward(req, resp);
		req.getRequestDispatcher("/jsp/allmember.jsp").forward(req, resp);
		
		
	}
	
	
}
