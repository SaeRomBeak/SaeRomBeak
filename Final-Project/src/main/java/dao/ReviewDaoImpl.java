package dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ReviewVo;

public class ReviewDaoImpl implements ReviewDao {

	SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}


	//관리자용 리뷰조회----------------------------------------------------
	public List<ReviewVo> selectList() {
		// TODO Auto-generated method stub
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
	
		list = sqlSession.selectList("review.review_list");
	
		return list;
	}
	
	//리뷰추가용---------------------------------------------------------
	public int insert(ReviewVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("review.review_insert",vo);
	}

	//카페idx에 해당하는 리뷰 얻어오기 ------------------------------------------
	public List<ReviewVo> selectList(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("review.review_c_idx",c_idx);
	}

	//내가쓴 리뷰들--------------------------------------------------------
	public List<ReviewVo> selectList_m(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("review.review_m_idx",m_idx);
	}
	
	//리뷰한건당 정보 얻어오기 -------------------------------------------------
	public ReviewVo selectOne(int r_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.review_r_idx",r_idx);
	}

	//리뷰삭제------------------------------------------------------------
	public int delete(int r_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("review.review_delete",r_idx);
	}

	//리뷰수정------------------------------------------------------------
	public int update(ReviewVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("review.review_update",vo);
	}

	//개인리뷰수구하기------------------------------------------------------
	public int review_count(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.review_count",m_idx);
	}

	//전체리뷰구하기--------------------------------------------------------
	public ReviewVo selectOne_full(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.full_review_su",c_idx);
	}

	//오늘 작성된 리뷰수 구하기-------------------------------------------------
	public int review_count() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.review_su");
	}

}