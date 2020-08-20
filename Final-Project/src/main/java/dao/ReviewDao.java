package dao;

import java.util.List;

import vo.ReviewVo;

public interface ReviewDao {

	public List<ReviewVo> selectList();				//관리자 리뷰목록
	public int insert(ReviewVo vo);					//리뷰추가용
	public List<ReviewVo> selectList(int c_idx);	//카페idx에 해당하는 리뷰 얻어오기 
	public List<ReviewVo> selectList_m(int m_idx);	//마이페이지 내가쓴 리뷰들
	public ReviewVo selectOne(int r_idx);			//리뷰한건당 정보 얻어오기 
	public int delete(int r_idx);					//리뷰삭제
	public int update(ReviewVo vo);					//리뷰수정
	public int review_count(int m_idx);				//개인리뷰수구하기
	public ReviewVo selectOne_full(int c_idx);		//전체리뷰구하기
	public int review_count();						//오늘 작성된 리뷰수 구하기

}