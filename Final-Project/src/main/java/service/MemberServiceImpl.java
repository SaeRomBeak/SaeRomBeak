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
	
	@Override	//����ȸ������ �о����-------------------------
	public List<MemberVo> selectList() {
		// TODO Auto-generated method stub
		return member_dao.selectList();
	}
	
	@Override	//���ð����� ���-----------------------------
	public int member_count() {
		// TODO Auto-generated method stub
		return member_dao.member_count();
	}
	
	@Override	 //�� ���̵� ���� ���� �����ϱ�-------------------
	public MemberVo selectOne(String m_id) {
		// TODO Auto-generated method stub
		return member_dao.selectOne(m_id);
	}
	
	@Override	//ȸ������ �߰��ϱ� ----------------------------
	public int insert(MemberVo vo) {
		// TODO Auto-generated method stub
		return member_dao.insert(vo);
	}
	
	@Override //�� ���̵� ���� ���� �����ϱ� ----------------------
	public MemberVo selectOne(int m_idx) {
		// TODO Auto-generated method stub
		return member_dao.selectOne(m_idx);
	}
	
	@Override	//ȸ������ �����ϱ�----------------------------
	public int update(MemberVo vo) {
		// TODO Auto-generated method stub
		return member_dao.update(vo);
	}
	
	@Override	//ȸ������ �����ϱ� ---------------------------
	public int delete(int m_idx) {
		// TODO Auto-generated method stub
		return member_dao.delete(m_idx);
	}

	@Override  //ȸ����������--------------------------------
	public int image_update(MemberVo vo) {
		// TODO Auto-generated method stub
		return member_dao.image_update(vo);
	}
}
