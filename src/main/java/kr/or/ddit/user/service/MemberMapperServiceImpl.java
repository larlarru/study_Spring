package kr.or.ddit.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.user.mapper.UsersMapper;
import kr.or.ddit.user.model.MemberVo;

@Service
public class MemberMapperServiceImpl implements MemberMapperService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberMapperServiceImpl.class);
	
	public MemberMapperServiceImpl() {
		logger.debug("In MemberMapperServiceImpl()");
	}
	
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public List<MemberVo> selectAllUser() throws Exception {
		logger.debug("selectAllUser()");
		return usersMapper.selectAllUser();
	}

	@Override
	public MemberVo selectUser(String userid) throws Exception {
		logger.debug("selectUser() userid 값 : {}", userid);
		logger.debug("userMapper.selectUser(userid)의 값 : {}", usersMapper.selectUser(userid));
		return usersMapper.selectUser(userid);
	}
	
	
		
}
