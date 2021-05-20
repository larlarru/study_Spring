package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.service.MemberServiceI;
import kr.or.ddit.user.service.MemberMapperServiceImpl;

@RequestMapping("member")
@Controller
public class MemberAllController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberAllController.class);
//	private MemberServiceI userService = new MemberService();
	
	public MemberAllController() {
		logger.debug("MemberAllController 시작");
	}
	
	//@Autowired
	@Resource(name="memberService")
	private MemberServiceI memberService;
	
	@Autowired
	private MemberMapperServiceImpl memberMapperService;
	
	@RequestMapping("view")
	public String view( ) {
		
		logger.debug("member의 view 집입");
		
		return "login";
	}
	
	@RequestMapping(path="process", method = RequestMethod.POST )
	public String process(MemberVo memberVo, HttpSession session, RedirectAttributes ra) throws Exception {
		
		logger.debug("memberVo : {}", memberVo);
		
//		MemberVo user = userService.selectUser(userid);
		
		String userid = memberVo.getUserid();
		
//		MemberVo dbUser = memberService.selectUser(userid);
		MemberVo dbUser = memberMapperService.selectUser(userid);
		logger.debug("memberMapperService의 dbUser 값 : {}", dbUser);
		
		if(dbUser != null && memberVo.getPass().equals(dbUser.getPass())) {
			
			session.setAttribute("S_USER", dbUser);
//			List<MemberVo> memberList = memberService.selectAllUser(); 			
//			model.addAttribute("memberList", memberList);
			
			return "memberMain";
		}
		else {
			
			// 내ㅜ적으로 session을 사용하여 속성을 저장
			// 리다이렉트 ㄹ처리가 완료되면 자동으로 session에서 제거
			ra.addFlashAttribute("msg", "잘못된 사용자 정보입니다.");
			
			// 일반 속성을 추가한 경우 : addAttribute
			// 리다이렉트 페이지의 파라미터로 전달된다.
			ra.addAttribute("userid", "brown");
			
			
			return "redirect:/member/view";
		}
	}
	
	// 페이징 처리 페이지
	@RequestMapping("pagingMember")
	public String pagingUser(@RequestParam( defaultValue = "1") int page, 
								@RequestParam( defaultValue = "5" )int pageSize,
								Model model) {

		
		PageVo pageVo = new PageVo();
		pageVo.setPage(page);
		pageVo.setPageSize(pageSize);
		
		
		
		model.addAllAttributes(memberService.selectPagingUser(pageVo));
		
		return "memberList";
	}
	
	// 검색 후 페이징 처리 페이지
	@RequestMapping("pagingSearchMember")
	public String pagingSearchMember(MemberVo memverVo, @RequestParam( defaultValue = "1") int page, 
			@RequestParam( defaultValue = "5" )int pageSize,
			Model model) {
		
		
		
		String userid = memverVo.getUserid();
		String usernm = memverVo.getUsernm();
		String alias = memverVo.getAlias();
		
		logger.debug("userid : "+userid);
		logger.debug("usernm : "+usernm);
		logger.debug("alias : "+alias);
		
		if(userid == null) userid = "";
		if(usernm == null) usernm = "";
		if(alias == null) alias = "";
		
		
		
		PageVo pageVo = new PageVo(page, pageSize, userid, usernm, alias);
		
		if(userid.equals("") && usernm.equals("") && alias.equals("")) {
			
			logger.debug("값들이 전부다 널들이다."+userid+", "+usernm+", "+alias);
//			 map = memberService.selectPagingUser(pageVo);
			 model.addAllAttributes(memberService.selectPagingUser(pageVo));
		}
		else if(!userid.equals("") && !usernm.equals("") && !alias.equals("")) {
			
			logger.debug("값들이 널들이 아니다."+userid+", "+usernm+", "+alias);
//			map = memberService.selectAllPagingKeywordMember(pageVo);
			model.addAllAttributes(memberService.selectAllPagingKeywordMember(pageVo));
		}
		else {
			
//			map = memberService.selectPagingSelectKeywordMember(pageVo);
			model.addAllAttributes(memberService.selectPagingSelectKeywordMember(pageVo));
		}
		
		
//		model.addAllAttributes(memberService.selectPagingUser(pageVo));
		
		return "memberList";
	}
	
	// 회원 상세정보

	
	
	
	@RequestMapping("memberInfo")
	public String memberInfo(MemberVo memberVo, Model model, RedirectAttributes ra) {
		
		logger.debug("memberVo : {}", memberVo);
		
//		MemberVo user = userService.selectUser(userid);
		
		String userid = memberVo.getUserid();
		
		MemberVo dbUser = memberService.selectUser(userid);
		
		model.addAttribute("member", dbUser);
			
		return "memberInfo";
	}
	
	@RequestMapping("profile")
	public void profile( HttpServletResponse resp, String userid, HttpServletRequest req ) {
		
		resp.setContentType("image");
		
		// userid 파라미터를 이용하여
		// userService 객체를 통해 사용자의 사진 파일 이름을 획득
		// 파일 입출력을 통해 사진을 읽어들여 resp객체의 outputStream으로 응답 생성
		
		MemberVo memberVo = memberService.selectUser(userid);
		
		String path = "";
		if(memberVo.getRealfilename()==null) {
			
			path = req.getServletContext().getRealPath("/image/unknown.png");
		} else {
			
			path = memberVo.getRealfilename();
		}
		
		logger.debug("path : {}",path);
		
		
		try {
			
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = resp.getOutputStream();
			
			byte[] buff = new byte[512];
			
			while(fis.read(buff) != -1) {
				
				sos.write(buff);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	// 회원등록 페이지 가기
	@RequestMapping("registView")
	public String registView( ) {
		
		logger.debug("member의 registView 집입");
		
		return "memberRegist";
	}
	
	@RequestMapping("registMember")
	public String registUser(@Valid MemberVo memberVo, BindingResult result, MultipartFile profile, Model model ) {
		
		String userid = memberVo.getUserid();
		
		if(result.hasErrors()) {
			
			logger.debug("result has error");
			
//			return "redirect:/user/registUserPage";	// 서블릿?경로
			return "member/memberRegist";	// <- jsp 겨올
			// jsp로 돌아간다
		}
		
		logger.debug("userVo {}, profile {}", memberVo, profile);
		
		
		try {
			
			profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			
		} catch ( IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		memberVo.setFilename(profile.getOriginalFilename());
		memberVo.setRealfilename("d:\\upload\\" + profile.getOriginalFilename());
		
		int registCnt = memberService.registUser(memberVo);
		
		
		if (registCnt == 1) {
			
			logger.debug("등록 성공");
				
//			return "memberMain";
			return "redirect:/member/memberInfo?userid="+userid;

		}
		else {
			logger.debug("등록 실패 ");
			return "memberMain";
//			return "member/memberRegist";
		}
		
	}
	
	// 회원수정 페이지 가기
	@RequestMapping("modifyView")
	public String modifyView(String userid, Model model) {
		
		logger.debug("userid :"+userid);
		
		model.addAttribute("member", memberService.selectUser(userid));

		return "memberModify";
	}
	
	@RequestMapping("modifyMember")
	public String modifyMember(MemberVo memberVo, MultipartFile profile) {
		
		logger.debug("userVo {}, profile {}", memberVo, profile);
		
		try {
			
			profile.transferTo(new File("d:\\upload\\" + profile.getOriginalFilename()));
			
		} catch ( IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		memberVo.setFilename(profile.getOriginalFilename());
		memberVo.setRealfilename("d:\\upload\\" + profile.getOriginalFilename());
		
		int modifyCnt = memberService.modifyUser(memberVo);
		
		
		if (modifyCnt == 1 ) {
			
			return "memberMain";
			
		}
		else return "memberModify";
		
	}
	
	@RequestMapping("deleteMember")
	public String deleteMember(String userid,  HttpSession session ) {
		
		int registCnt = memberService.deleteUser(userid);
		
		
		if (registCnt == 1 ) {
			
			return "memberMain";
		}
		else return "redirect:/member/memberInfo?userid="+userid;
		
	}
	
	// 중복검사 페이지 가기
	@RequestMapping("memberIdCheck")
	public String memberIdCheck(String userid, Model model) {

		logger.debug("userid :" + userid);
		
		int checkCnt = memberService.selectCheckUserId(userid);
		
		if(checkCnt == 1) {
			logger.debug("checkCnt {}",checkCnt);
			model.addAttribute("idCheck", "중복되었습니다");
			
			logger.debug("아이디 중복됨");
			return "memberRegist";
			
		}
		else {
			logger.debug("checkCnt {}",checkCnt);
			model.addAttribute("idCheck", "사용가능합니다");
			
			return "memberRegist";
		}
	}
	
	
	
	
	
	
	
	
}
