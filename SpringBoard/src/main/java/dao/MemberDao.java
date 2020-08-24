package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;

public class MemberDao {

	
	SqlSession sqlSession;// SqlSessionTemplate(spring)
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//회원목록 조회==========================================================
	public List<MemberVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.member_list");
	}
	
	//m_idx에 해당되는 회원정보 1건 얻기===========================================
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.member_one_id",m_id);
	}

	//회원가입 하기==========================================================
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("member.member_insert",vo);
	}

	
	
	
}
