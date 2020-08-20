package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.MemberVo;

public class MemberDaoImpl implements MemberDao {

	SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	
	
	//����ȸ������ �о����---------------------------------------------------
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();

		list = sqlSession.selectList("member.member_list");

		return list;
	}

	//���� �����ѻ���鱸�ϱ�--------------------------------------------------
	public int member_count() {
		return sqlSession.selectOne("member.member_su");
	}
	
	
    //�� ���̵� ���� ���� �����ϱ� ----------------------------------------------
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.member_id",m_id);
	}
	
	//ȸ������ �߰��ϱ� -----------------------------------------------------
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("member.member_insert",vo);
	}

	
	//idx�� ���� ������ ������-----------------------------------------------
	public MemberVo selectOne(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.member_idx",m_idx);
	}
	
	
	//ȸ������ �����ϱ�-----------------------------------------------------
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("member.member_update",vo);
	}


	//ȸ������ �����ϱ� -----------------------------------------------------
	public int delete(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("member.member_delete",m_idx);
	}

	
	
	@Override //ȸ���������� --------------------------------------------
	public int image_update(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("member.image_update",vo);
	}

}