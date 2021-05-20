package kr.or.ddit.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.service.MemberService;
import kr.or.ddit.user.service.MemberServiceI;

@WebServlet("/pagingMember.do")
public class pagingMember extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(pagingMember.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		MemberServiceI memberService = new MemberService();
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("usernm");
		String alias = req.getParameter("alias");
		
		logger.debug("userid : "+userid);
		logger.debug("usernm : "+usernm);
		logger.debug("alias : "+alias);
		
		if(userid == null) userid = "";
		if(usernm == null) usernm = "";
		if(alias == null) alias = "";
		
		String pageParam = req.getParameter("page");
		logger.debug("pageParam : "+pageParam);
		String pageSizeParam = req.getParameter("pageSize");
		logger.debug("pageSize : "+pageSizeParam);
		
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		int pageSize = pageSizeParam == null ? 5 : Integer.parseInt(pageSizeParam);
		
		PageVo pageVo = new PageVo(page, pageSize, userid, usernm, alias);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(userid.equals("") && usernm.equals("") && alias.equals("")) {
			
			logger.debug("값들이 전부다 널들이다."+userid+", "+usernm+", "+alias);
			 map = memberService.selectPagingUser(pageVo);
		}
		else if(!userid.equals("") && !usernm.equals("") && !alias.equals("")) {
			
			logger.debug("값들이 널들이 아니다."+userid+", "+usernm+", "+alias);
			map = memberService.selectAllPagingKeywordMember(pageVo);
		}
		else {
			
			map = memberService.selectPagingSelectKeywordMember(pageVo);
		}
		
		
		List<MemberVo> memberList = (List<MemberVo>)map.get("memberList");
		
		int userCnt = (int)map.get("userCnt");
		int pagination = (int)Math.ceil((double) userCnt / pageSize); 
		
		req.setAttribute("pageVo", pageVo);
		req.setAttribute("memberList", memberList);
		req.setAttribute("pagination", pagination);
		
		
		logger.debug("pagination : "+pagination);
		logger.debug("pageVo의 page : "+pageVo.getPage());
		logger.debug("pageVo의 pageSize : "+pageVo.getPageSize());
		
		req.getRequestDispatcher("/jsp/memberList.jsp").forward(req, resp);
//		req.getRequestDispatcher(req.getContextPath()+"/jsp/memberList.jsp").forward(req, resp);
		logger.debug("위치는 페이징 값은 경로 : "+req.getContextPath()+"/jsp/memberList.jsp");
		
	}
	
}
