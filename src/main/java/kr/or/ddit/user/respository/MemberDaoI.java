package kr.or.ddit.user.respository;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.user.model.MemberVo;

public interface MemberDaoI {
	
	// 반환타입 메소드명();
	List<MemberVo> selectAllUser();
	
	MemberVo selectUser(String userid);
	
	List<MemberVo> selectPagingUser(PageVo pageVo);
	
	// 사용자 전체 수 조회
	int selectAllUserCnt();
	
	// 사용자 정보 수정
	int modifyUser(MemberVo userVo);
	
	int registUser(MemberVo memberVo);
	
	// 사용자 삭제
	int deleteUser(String userid);
	
	List<MemberVo> selectPagingSelectKeywordMember(PageVo pageVo);
	
	List<MemberVo> selectAllPagingKeywordMember(PageVo pageVo);
	
	// 검색된 사용자 수 조회
	int selectKeywordUserCnt(PageVo pageVo);
	
	int selectCheckUserId(String userid);
	
	int selectCheckAlias(String alias);
	
	
}
