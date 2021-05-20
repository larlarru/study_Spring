package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.service.MemberService;
import kr.or.ddit.user.service.MemberServiceI;

@WebServlet("/deleteMember.do")
public class MemberDelete extends HttpServlet{
	

	private static final Logger logger = LoggerFactory.getLogger(MemberDelete.class);
	private MemberServiceI memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userid = req.getParameter("userid");
		logger.debug("삭제전 userid 값 : "+userid);
		
		int deleteCnt = 0;
		
		try {
			deleteCnt = memberService.deleteUser(userid);
		} catch (Exception e) {
			deleteCnt = -1;
		}
		
		if(deleteCnt == 1) {
			resp.sendRedirect(req.getContextPath()+"/pagingMember.do");
		}
		else {
			resp.sendRedirect(req.getContextPath()+"/member?userid="+userid);
		}
		
	}
	

}
