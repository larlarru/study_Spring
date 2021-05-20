package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.MemberVo;

public interface MemberServiceI {
	
	List<MemberVo> selectAllUser();
	
	MemberVo selectUser(String userid);
	
	Map<String, Object> selectPagingUser(PageVo pageVo);
	//	List<UserVo> selectPagingUser(PageVo pageVo);
	
	// 사용자 정보 수정
	int modifyUser(MemberVo userVo);
	
	// 사용자 삭제
	int deleteUser(String userid);
	
	int registUser(MemberVo memberVo);
	
	int selectAllUserCnt();
	
	Map<String, Object> selectPagingSelectKeywordMember(PageVo pageVo);
	
	Map<String, Object> selectAllPagingKeywordMember(PageVo pageVo);
	
	int selectCheckUserId(String userid);
	
	int selectCheckAlias(String alias);
	
}
