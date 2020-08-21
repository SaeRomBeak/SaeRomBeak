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
	
	
	@Override  //ī������ȸ-----------------------------
	public List<CafeVo> selectList() {
		// TODO Auto-generated method stub
		return cafe_dao.selectList();
	}
	
	@Override	//ī�װ���5����+����----------------------
	public List<CafeVo> cafe_select(Map map) {
		// TODO Auto-generated method stub
		return cafe_dao.cafe_select(map);
	}
	
	@Override	//�߰���--------------------------------
	public int insert(CafeVo vo) {
		// TODO Auto-generated method stub
		return cafe_dao.insert(vo);
	}
	
	@Override	//�����ϳ� �����ϱ�-------------------------
	public CafeVo selectOne(int c_idx) {
		// TODO Auto-generated method stub
		return cafe_dao.selectOne(c_idx);
	}
	
	@Override	//ī�����-------------------------------
	public int update(CafeVo vo) {
		// TODO Auto-generated method stub
		return cafe_dao.update(vo);
	}
	
	@Override	//ī�����--------------------------------
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		return cafe_dao.delete(c_idx);
	}
	
	@Override	//ī����ȸ��------------------------------
	public int updateCount(int c_idx) {
		// TODO Auto-generated method stub
		return cafe_dao.updateCount(c_idx);
	}
	
	@Override	//���õ����ī��----------------------------
	public int cafe_count() {
		// TODO Auto-generated method stub
		return cafe_dao.cafe_count();
	}

	@Override	//����¡ó��----------------------------
	public int selectRowTotal() {
		// TODO Auto-generated method stub
		return cafe_dao.selectRowTotal();	
	}

	@Override	//�̹�������---------------------------
	public int update_image(CafeVo vo) {
		// TODO Auto-generated method stub
		return cafe_dao.update_image(vo);	
	}

}
