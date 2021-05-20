package kr.or.ddit.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.service.MemberService;
import kr.or.ddit.user.service.MemberServiceI;
import kr.or.ddit.util.FileUtil;

@MultipartConfig
@WebServlet("/memberRegist.do")
public class MemberRegist extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberRegist.class);
	private MemberServiceI userService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		
		 if(req.getParameter("userid") != null) {
	         req.setAttribute("idcheck", "중복된 아이디 입니다.");
	      }
		 if(req.getParameter("alias") != null) {
			 req.setAttribute("aliascheck", "중복된 별명 입니다.");
		 }
		 req.getRequestDispatcher("/jsp/memberRegist.jsp").forward(req, resp);
//		 req.getRequestDispatcher(req.getContextPath()+"/jsp/memberRegist.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터를 읽기 전에 실행
				// servlet doPost 메소드 마다 실행 필요 ==> Filter
				req.setCharacterEncoding("utf-8");
				
				String userid = req.getParameter("userid");
				String usernm = req.getParameter("usernm");
				String pass = req.getParameter("pass");
				
				logger.debug("userid : "+userid);
				logger.debug("usernm : "+usernm);
				logger.debug("pass : "+pass);
				
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
				Date reg_dt = new Date();
				
				
				String alias = req.getParameter("alias");
				String addr1 = req.getParameter("addr1");
				String addr2 = req.getParameter("addr2");
				String zipcode = req.getParameter("zipcode");
				logger.debug("alias : "+alias);
				logger.debug("addr1 : "+addr1);
				logger.debug("addr2 : "+addr2);
				logger.debug("zipcode : "+zipcode);
				
				Part profile = req.getPart("picture");
				logger.debug("등록전 profile값 : "+profile);
				
				String filename="";
				String realfilename = "";
				
				if(profile.getSize() > 0) {
					filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
					logger.debug("인코딩한 filename : "+filename);
					String fileExtension = FileUtil.getFileExtension(filename);
					realfilename = UUID.randomUUID().toString()+fileExtension;
					
					logger.debug("등록전 realfilename값 : "+realfilename);
					
					profile.write("d:\\upload\\"+realfilename);
//					profile.write("d:\\A_TeachingMaterial\\6.JspSpring\\workspace\\member\\src\\main\\webapp\\profile\\"+realfilename);
				}
				
				logger.debug("Insert에 uservo에 값넣기전 filename : " + filename);
				logger.debug("Insert에 uservo에 값넣기전 realfilename : " + realfilename);
				
				MemberVo membervo = new MemberVo(userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode, filename, realfilename);
				
				int resultCnt = 0;

				
				if ( userid != null) {
					
					resultCnt = userService.selectCheckUserId(userid);
				}
				else if ( alias != null ) {
					
					resultCnt = userService.selectCheckAlias(alias);
					
				}
				
				logger.debug("중복 체크 resultCnt 값 : "+resultCnt);
				
				int insertCnt = 0;
				if (resultCnt == 0) {
					insertCnt = userService.registUser(membervo);
				}
				
//				int insertCnt = userService.registUser(membervo);
				
				// 사용자 수정이 정상적으로 된 경우 ==> 해당 사용자의 상세조회 페이지로 이동
				if(insertCnt == 1) {
					resp.sendRedirect(req.getContextPath()+"/pagingMember.do");
				}
				// 사용자 수정이 비정상적으로 된 경우 ==> 해당 사용자의 정보 수정 페이지로 이동
				else {
//					resp.sendRedirect("/jsp/memberRegist.jsp");
					doGet(req, resp);
				}
		
	}
	
}
