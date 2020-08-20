package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.CafeVo;

public class CafeDaoImpl implements CafeDao {

	SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	//ī������ȸ -----------------------------------------------------
	public List<CafeVo> selectList() {

		List<CafeVo> list = new ArrayList<CafeVo>();

		list = sqlSession.selectList("cafe.cafe_list");

		return list;
	}

	//ī�װ���5���� �̾ƿ��� + ����--------------------------------------
	public List<CafeVo> cafe_select(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cafe.cafe_select",map);
	}
	
	//�߰���--------------------------------------------------------
	public int insert(CafeVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("cafe.cafe_insert",vo);
	}

	//�����ϳ� �����ϱ�-------------------------------------------------
	public CafeVo selectOne(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cafe.cafe_idx",c_idx);
	}


	//ī�����-------------------------------------------------------
	public int update(CafeVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("cafe.cafe_update",vo);
	}
	
	//ī�����-------------------------------------------------------
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("cafe.cafe_delete",c_idx);
	}

	//ī����ȸ��------------------------------------------------------
	public int updateCount(int c_idx) {
		// TODO Auto-generated method stub
		return  sqlSession.update("cafe.cafe_hit",c_idx);
	}

	//���õ����ī��---------------------------------------------------
	@Override
	public int cafe_count() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cafe.cafe_su");	
	}
			
}