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


	//카페목록조회 -----------------------------------------------------
	public List<CafeVo> selectList() {

		List<CafeVo> list = new ArrayList<CafeVo>();

		list = sqlSession.selectList("cafe.cafe_list");

		return list;
	}

	//카테고리별5개씩 뽑아오기 + 평점--------------------------------------
	public List<CafeVo> cafe_select(Map map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cafe.cafe_select",map);
	}
	
	//추가용--------------------------------------------------------
	public int insert(CafeVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("cafe.cafe_insert",vo);
	}

	//정보하나 추출하기-------------------------------------------------
	public CafeVo selectOne(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cafe.cafe_idx",c_idx);
	}


	//카페수정-------------------------------------------------------
	public int update(CafeVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("cafe.cafe_update",vo);
	}
	
	//카페삭제-------------------------------------------------------
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("cafe.cafe_delete",c_idx);
	}

	//카페조회수------------------------------------------------------
	public int updateCount(int c_idx) {
		// TODO Auto-generated method stub
		return  sqlSession.update("cafe.cafe_hit",c_idx);
	}

	//오늘등록한카페---------------------------------------------------
	@Override
	public int cafe_count() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cafe.cafe_su");	
	}
			
}