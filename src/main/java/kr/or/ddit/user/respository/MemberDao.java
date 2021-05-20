package kr.or.ddit.user.respository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.PageVo;
import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.user.model.MemberVo;

@Repository("memberDao")
public class MemberDao implements MemberDaoI{

	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate template;
	
	@Override
	public List<MemberVo> selectAllUser() {
		logger.debug("memberDao의 selectAllUser 진입");
		return template.selectList("members.selectAllUser");
	}

	// userid에 해당하는 사용자 한명의 정보 조회
	public MemberVo selectUser(String userid) {
		return template.selectOne("members.selectUser", userid);
		
	}

	@Override
	public List<MemberVo> selectPagingUser(PageVo pageVo) {
		
		logger.debug("member의 selectPagingUser 진입");
		
		return template.selectList("members.selectPagingUser", pageVo);
	}

	@Override
	public int selectAllUserCnt() {
		
		return template.selectOne("members.selectAllUserCnt");
	}

	@Override
	public int modifyUser(MemberVo userVo) {
		
		
		return template.update("members.modifyUser", userVo);
	}

	@Override
	public int deleteUser(String userid) {
		
		return template.delete("members.deleteUser", userid);
	}

	@Override
	public int registUser(MemberVo memberVo) {
		return template.insert("members.registUser", memberVo);
	}
	
	// 검색하는 페이징 검색
	@Override
	public List<MemberVo> selectPagingSelectKeywordMember(PageVo pageVo) {
		
		return template.selectList("members.selectPagingSelectKeywordMember", pageVo);
	}

	@Override
	public List<MemberVo> selectAllPagingKeywordMember(PageVo pageVo) {
		
		return template.selectList("members.selectAllPagingKeywordMember", pageVo);
	}

	@Override
	public int selectKeywordUserCnt(PageVo pageVo) {
		
		return template.selectOne("members.selectKeywordUserCnt", pageVo);
	}

	@Override
	public int selectCheckUserId(String userid) {
		
		return template.selectOne("members.selectCheckUserId", userid);
	}

	@Override
	public int selectCheckAlias(String alias) {
		
		return template.selectOne("members.selectCheckAlias", alias);
	}


}
