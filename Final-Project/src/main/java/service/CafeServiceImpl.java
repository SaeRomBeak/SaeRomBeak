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

@Service
public class CafeServiceImpl implements CafeService{

	@Autowired
	CafeDao cafe_dao;
	
	
	@Override  //카페목록조회-----------------------------
	public List<CafeVo> selectList() {
		// TODO Auto-generated method stub
		return cafe_dao.selectList();
	}
	
	@Override	//카테고리별5개씩+평점----------------------
	public List<CafeVo> cafe_select(Map map) {
		// TODO Auto-generated method stub
		return cafe_dao.cafe_select(map);
	}
	
	@Override	//추가용--------------------------------
	public int insert(CafeVo vo) {
		// TODO Auto-generated method stub
		return cafe_dao.insert(vo);
	}
	
	@Override	//정보하나 추출하기-------------------------
	public CafeVo selectOne(int c_idx) {
		// TODO Auto-generated method stub
		return cafe_dao.selectOne(c_idx);
	}
	
	@Override	//카페수정-------------------------------
	public int update(CafeVo vo) {
		// TODO Auto-generated method stub
		return cafe_dao.update(vo);
	}
	
	@Override	//카페삭제--------------------------------
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		return cafe_dao.delete(c_idx);
	}
	
	@Override	//카페조회수------------------------------
	public int updateCount(int c_idx) {
		// TODO Auto-generated method stub
		return cafe_dao.updateCount(c_idx);
	}
	
	@Override	//오늘등록한카페----------------------------
	public int cafe_count() {
		// TODO Auto-generated method stub
		return cafe_dao.cafe_count();
	}

	@Override	//페이징처리----------------------------
	public int selectRowTotal() {
		// TODO Auto-generated method stub
		return cafe_dao.selectRowTotal();	
	}

	@Override	//이미지수정---------------------------
	public int update_image(CafeVo vo) {
		// TODO Auto-generated method stub
		return cafe_dao.update_image(vo);	
	}

}
