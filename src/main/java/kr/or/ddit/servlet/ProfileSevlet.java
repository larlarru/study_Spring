package kr.or.ddit.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.service.MemberService;
import kr.or.ddit.user.service.MemberServiceI;

@WebServlet("/profile.do")
public class ProfileSevlet extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(ProfileSevlet.class);
	
	private MemberServiceI userService = new MemberService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("image");
		
		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
		String userid = req.getParameter("userid");
		
		MemberVo userVo = userService.selectUser(userid);
		
		String path = "";
		if(userVo.getRealfilename()==null) {
			path = getServletContext().getRealPath("/profile/unknown.png");
		} else {
			path = "d:\\upload\\"+userVo.getRealfilename();
		}
		
		logger.debug("path : {}",path);
		
		FileInputStream fis = new FileInputStream(path);
		ServletOutputStream sos = resp.getOutputStream();
		
		byte[] buff = new byte[512];
		
		while(fis.read(buff) != -1) {
			
			sos.write(buff);
		}
		
	}
	
}
