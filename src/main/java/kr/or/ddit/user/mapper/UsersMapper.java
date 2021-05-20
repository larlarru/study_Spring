package kr.or.ddit.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.or.ddit.user.model.MemberVo;

@Mapper
public interface UsersMapper {
	
	MemberVo selectUser(String userid) throws Exception;
	
	List<MemberVo> selectAllUser() throws Exception;
	
}
