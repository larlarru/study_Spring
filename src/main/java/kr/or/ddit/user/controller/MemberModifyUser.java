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
@WebServlet("/memberModify.do")
public class MemberModifyUser extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberModifyUser.class);
	private MemberServiceI memberService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		
		MemberVo member = memberService.selectUser(userid);
		
		req.setAttribute("member", member);
		
		req.getRequestDispatcher("/jsp/memberModify.jsp").forward(req, resp);
//		req.getRequestDispatcher(req.getContextPath()+"/jsp/memberModify.jsp").forward(req, resp);
		
	}
	
	// 사용자 정보 수정 요청 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터를 읽기 전에 실행
		// servlet doPost 메소드 마다 실행 필요 ==> Filter
		req.setCharacterEncoding("utf-8");
		
		String userid = req.getParameter("userid");
		String usernm = req.getParameter("usernm");
		String pass = req.getParameter("pass");
		
		logger.debug("member 상세정보 수정전 값 확인");
		logger.debug("userid : "+userid);
		logger.debug("usernm : "+usernm);
		logger.debug("pass : "+pass);
		
		Date reg_dt = new Date();
		
		logger.debug("req.getParameter의 reg_dt : " + req.getParameter("reg_dt"));
		
		String alias = req.getParameter("alias");
		String addr1 = req.getParameter("addr1");
		String addr2 = req.getParameter("addr2");
		String zipcode = req.getParameter("zipcode");
		
		logger.debug("alias : "+alias);
		logger.debug("addr1 : "+addr1);
		logger.debug("addr2 : "+addr2);
		logger.debug("zipcode : "+zipcode);
		
		String filename="";
		String realfilename = "";
		
		Part profile = req.getPart("picture");
		if(profile.getSize() > 0) {
			filename = FileUtil.getFileName(profile.getHeader("Content-Disposition"));
			String fileExtension = FileUtil.getFileExtension(filename);
			
			logger.debug("등록의 write전 filename : "+filename);
			logger.debug("등록의 write전 fileExtension : "+fileExtension);
			
			// brown / brown.png
			realfilename = UUID.randomUUID().toString()+fileExtension;
			
			
			profile.write("d:\\upload\\"+realfilename);
//			profile.write("d:\\A_TeachingMaterial\\6.JspSpring\\workspace\\jsp\\src\\main\\webapp"+realfilename);
		}
		
		else {
			MemberVo uservo1 = memberService.selectUser(userid);
			filename = uservo1.getFilename();
			realfilename = uservo1.getRealfilename();
		}
		
		MemberVo uservo = new MemberVo(userid, usernm, pass, reg_dt, alias, addr1, addr2, zipcode, filename, realfilename);
		
		int updateCnt = memberService.modifyUser(uservo);
		logger.debug("updateCnt : " + updateCnt);
		// 사용자 수정이 정상적으로 된 경우 ==> 해당 사용자의 상세조회 페이지로 이동
		if(updateCnt == 1) {
			resp.sendRedirect(req.getContextPath()+"/member?userid="+userid);
		}
		// 사용자 수정이 비정상적으로 된 경우 ==> 해당 사용자의 정보 수정 페이지로 이동
		else {
			doGet(req,resp);
		}
		
	}
	
}
