package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;

public class MemberDao {

	
	SqlSession sqlSession;// SqlSessionTemplate(spring)
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	//ȸ����� ��ȸ==========================================================
	public List<MemberVo> selectList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("member.member_list");
	}
	
	//m_idx�� �ش�Ǵ� ȸ������ 1�� ���===========================================
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.member_one_id",m_id);
	}

	//ȸ������ �ϱ�==========================================================
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("member.member_insert",vo);
	}

	
	
	
}
