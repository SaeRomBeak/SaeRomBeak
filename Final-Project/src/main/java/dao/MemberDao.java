package dao;

import java.util.List;

import vo.MemberVo;

public interface MemberDao {
	
	public List<MemberVo> selectList();		//회원목록
	public int member_count();				//오늘가입한 멤버
	public MemberVo selectOne(String m_id);	//한 아이디에 대한 정보 추출하기 
	public int insert(MemberVo vo);			//회원정보 추가하기 
	public MemberVo selectOne(int m_idx);	//idx에 대한 정보만 얻어오기  (수정할 정보담기)
	public int update(MemberVo vo);			//회원정보 수정하기
	public int delete(int m_idx);			//회원정보 삭제하기 
	public int image_update(MemberVo vo);	//프로필사진수정
}