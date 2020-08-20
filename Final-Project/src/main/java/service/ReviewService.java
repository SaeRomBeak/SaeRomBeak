package service;

import java.util.List;

import vo.ReviewVo;

public interface ReviewService {

	public List<ReviewVo> selectList();				//관리자용 조회
	public int insert(ReviewVo vo);					//추가용
	public List<ReviewVo> selectList(int c_idx);	//카페idx에 해당하는 리뷰 얻어오기 
	public List<ReviewVo> selectList_m(int m_idx);	//내가 쓴 리뷰리스트
	public ReviewVo selectOne(int r_idx);			//리뷰한건당 정보 얻어오기 
	public int delete(int r_idx);					//리뷰삭제
	public int update(ReviewVo vo);					//리뷰수정
	public int review_count(int m_idx);			//개인리뷰수구하기
	public ReviewVo selectOne_full(int c_idx);		//전체리뷰구하기
	public int review_count();						//오늘 작성된 리뷰수 구하기

}
