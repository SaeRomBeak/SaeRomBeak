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

	
	
	//가입회원정보 읽어오기---------------------------------------------------
	public List<MemberVo> selectList() {

		List<MemberVo> list = new ArrayList<MemberVo>();

		list = sqlSession.selectList("member.member_list");

		return list;
	}

	//오늘 가입한사람들구하기--------------------------------------------------
	public int member_count() {
		return sqlSession.selectOne("member.member_su");
	}
	
	
    //한 아이디에 대한 정보 추출하기 ----------------------------------------------
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.member_id",m_id);
	}
	
	//회원정보 추가하기 -----------------------------------------------------
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("member.member_insert",vo);
	}

	
	//idx에 대한 정보만 얻어오기-----------------------------------------------
	public MemberVo selectOne(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("member.member_idx",m_idx);
	}
	
	
	//회원정보 수정하기-----------------------------------------------------
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("member.member_update",vo);
	}


	//회원정보 삭제하기 -----------------------------------------------------
	public int delete(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("member.member_delete",m_idx);
	}

	
	
	@Override //회원사진수정 --------------------------------------------
	public int image_update(MemberVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("member.image_update",vo);
	}

}