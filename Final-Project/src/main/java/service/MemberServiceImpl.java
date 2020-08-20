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
public class MemberServiceImpl implements MemberService{

   @Autowired
   MemberDao member_dao;
	
	@Override	//가입회원정보 읽어오기-------------------------
	public List<MemberVo> selectList() {
		// TODO Auto-generated method stub
		return member_dao.selectList();
	}
	
	@Override	//오늘가입한 멤버-----------------------------
	public int member_count() {
		// TODO Auto-generated method stub
		return member_dao.member_count();
	}
	
	@Override	 //한 아이디에 대한 정보 추출하기-------------------
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		return member_dao.selectOne(m_id);
	}
	
	@Override	//회원정보 추가하기 ----------------------------
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		return member_dao.insert(vo);
	}
	
	@Override //한 아이디에 대한 정보 추출하기 ----------------------
	public MemberVo selectOne(int m_idx) {
		// TODO Auto-generated method stub
		return member_dao.selectOne(m_idx);
	}
	
	@Override	//회원정보 수정하기----------------------------
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		return member_dao.update(vo);
	}
	
	@Override	//회원정보 삭제하기 ---------------------------
	public int delete(int m_idx) {
		// TODO Auto-generated method stub
		return member_dao.delete(m_idx);
	}

	@Override  //회원사진수정--------------------------------
	public int image_update(MemberVo vo) {
		// TODO Auto-generated method stub
		return member_dao.image_update(vo);
	}
}
