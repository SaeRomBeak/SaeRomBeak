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


	//�����ڿ� ������ȸ----------------------------------------------------
	public List<ReviewVo> selectList() {
		// TODO Auto-generated method stub
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
	
		list = sqlSession.selectList("review.review_list");
	
		return list;
	}
	
	//�����߰���---------------------------------------------------------
	public int insert(ReviewVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("review.review_insert",vo);
	}

	//ī��idx�� �ش��ϴ� ���� ������ ------------------------------------------
	public List<ReviewVo> selectList(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("review.review_c_idx",c_idx);
	}

	//������ �����--------------------------------------------------------
	public List<ReviewVo> selectList_m(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("review.review_m_idx",m_idx);
	}
	
	//�����ѰǴ� ���� ������ -------------------------------------------------
	public ReviewVo selectOne(int r_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.review_r_idx",r_idx);
	}

	//�������------------------------------------------------------------
	public int delete(int r_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("review.review_delete",r_idx);
	}

	//�������------------------------------------------------------------
	public int update(ReviewVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("review.review_update",vo);
	}

	//���θ�������ϱ�------------------------------------------------------
	public int review_count(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.review_count",m_idx);
	}

	//��ü���䱸�ϱ�--------------------------------------------------------
	public ReviewVo selectOne_full(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.full_review_su",c_idx);
	}

	//���� �ۼ��� ����� ���ϱ�-------------------------------------------------
	public int review_count() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("review.review_su");
	}

}