package service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CafeDao;
import dao.MemberDao;
import dao.ReviewDao;
import vo.CafeVo;
import vo.MemberVo;
import vo.ReviewVo;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewDao review_dao;

	@Override 	//리뷰 목록 조회------------------------------
	public List<ReviewVo> selectList() {
		// TODO Auto-generated method stub
		return review_dao.selectList();
	}

	@Override	//리뷰 등록---------------------------------
	public int insert(ReviewVo vo) {
		// TODO Auto-generated method stub
		return review_dao.insert(vo);
	}

	@Override	//idx 리뷰얻어오기----------------------------
	public List<ReviewVo> selectList(int c_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectList(c_idx);
	}

	@Override	//마이 페이지 내가쓴 리뷰들------------------------
	public List<ReviewVo> selectList_m(int m_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectList_m(m_idx);
	}

	@Override	//리뷰 한건 당 정보얻어오기------------------------
	public ReviewVo selectOne(int r_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectOne(r_idx);
	}

	@Override	//리뷰 삭제----------------------------------
	public int delete(int r_idx) {
		// TODO Auto-generated method stub
		return review_dao.delete(r_idx);
	}

	@Override 	//리뷰 수정----------------------------------
	public int update(ReviewVo vo) {
		// TODO Auto-generated method stub
		return review_dao.update(vo);
	}

	@Override	//마이 페이지 리뷰수 구하기------------------------
	public int review_count(int m_idx) {
		// TODO Auto-generated method stub
		return review_dao.review_count(m_idx);
	}

	@Override	//전체 리뷰수구하기-----------------------------
	public ReviewVo selectOne_full(int c_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectOne_full(c_idx);
	}

	@Override	//오늘 작성된 리뷰수 구하기-------------------------
	public int review_count() {
		// TODO Auto-generated method stub
		return review_dao.review_count();
	}
}
