package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.MemberVo;

public interface MemberMapperService {
	
	List<MemberVo> selectAllUser() throws Exception;
	
	MemberVo selectUser(String userid) throws Exception;
	
}
