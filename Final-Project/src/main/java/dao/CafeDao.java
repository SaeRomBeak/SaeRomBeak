package dao;

import java.util.List;
import java.util.Map;

import vo.CafeVo;

public interface CafeDao {

	public List<CafeVo> selectList();			//카페목록조회 
	public List<CafeVo> cafe_select(Map map);	//5개씩 뽑아오기+평점
	public int insert(CafeVo vo);				//추가용
	public CafeVo selectOne(int c_idx);			//정보하나 추출하기
	public int update(CafeVo vo);				//카페정보수정
	public int delete(int c_idx);				//카페정보삭제
	public int updateCount(int c_idx);			//카페조회수
	public int cafe_count();					//오늘 등록한 카페 수 
	public int selectRowTotal();				//페이징처리
	public int update_image(CafeVo vo);			//이미지수정
	
}