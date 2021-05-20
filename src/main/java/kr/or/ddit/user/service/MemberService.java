package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.user.model.MemberVo;
import kr.or.ddit.user.respository.MemberDao;
import kr.or.ddit.user.respository.MemberDaoI;

@Service("memberService")
public class MemberService implements MemberServiceI{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Resource(name="memberDao")
	private MemberDaoI memberDao;
	
	public MemberService() {
		logger.debug("service!!!!!!!!!!!!!!");
	}
	
	public MemberService(MemberDaoI memberDao) {
		logger.debug("service의 memberdao");
		this.memberDao = memberDao;
	}
	
	@Override
	public List<MemberVo> selectAllUser() {
		return memberDao.selectAllUser();
	}

	@Override
	public MemberVo selectUser(String userid) {
		logger.debug("UserService에 selectUser부분");
		return memberDao.selectUser(userid);
	}

	@Override
	public Map<String, Object> selectPagingUser(PageVo pageVo) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		resultMap.put("pageVo", pageVo);
		resultMap.put("memberList", memberDao.selectPagingUser(pageVo));
		
		resultMap.put("pagination", 
				(int)Math.ceil( (double)memberDao.selectAllUserCnt() /pageVo.getPageSize() ) );
				
		return resultMap;
		
	}

	@Override
	public int modifyUser(MemberVo memberVo) {
		return memberDao.modifyUser(memberVo);
	}


	@Override
	public int deleteUser(String userid) {
		return memberDao.deleteUser(userid);
	}

	@Override
	public int registUser(MemberVo memberVo) {
		return memberDao.registUser(memberVo);
	}

	@Override
	public int selectAllUserCnt() {
		return memberDao.selectAllUserCnt();
	}

	@Override
	public Map<String, Object> selectPagingSelectKeywordMember(PageVo pageVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MemberVo> memberList = memberDao.selectPagingSelectKeywordMember(pageVo);
		int userCnt = memberDao.selectKeywordUserCnt(pageVo);
		
		logger.debug("selectPagingSelectKeywordMember의 page값들 : {}", pageVo.getPage(), pageVo.getPageSize());
		
		map.put("memberList", memberList);
		map.put("userCnt", userCnt);
		
		logger.debug("selectPagingSelectKeywordMember의 userCnt값 : {}", userCnt);
		
		
		map.put("pageVo", pageVo);
		
		map.put("pagination", 
				(int)Math.ceil( (double)memberDao.selectKeywordUserCnt(pageVo) /pageVo.getPageSize() ) );
		
		return map;
	}

	@Override
	public Map<String, Object> selectAllPagingKeywordMember(PageVo pageVo) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MemberVo> memberList = memberDao.selectAllPagingKeywordMember(pageVo);
		int userCnt = memberDao.selectKeywordUserCnt(pageVo);
		
		map.put("memberList", memberList);
		map.put("userCnt", userCnt);
		
		logger.debug("selectAllPagingKeywordMember의 userCnt값 : {}", userCnt);
		logger.debug("selectAllPagingKeywordMember의 page값들 : {}", pageVo.getPage(), pageVo.getPageSize());
		
		map.put("pageVo", pageVo);
		
		map.put("pagination", 
				(int)Math.ceil( (double)memberDao.selectKeywordUserCnt(pageVo) /pageVo.getPageSize() ) );
		
		
		return map;
	}

	@Override
	public int selectCheckUserId(String userid) {
		return memberDao.selectCheckUserId(userid);
	}

	@Override
	public int selectCheckAlias(String alias) {
		return memberDao.selectCheckAlias(alias);
	}

}
