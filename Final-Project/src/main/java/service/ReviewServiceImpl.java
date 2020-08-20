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

	@Override 	//���� ��� ��ȸ------------------------------
	public List<ReviewVo> selectList() {
		// TODO Auto-generated method stub
		return review_dao.selectList();
	}

	@Override	//���� ���---------------------------------
	public int insert(ReviewVo vo) {
		// TODO Auto-generated method stub
		return review_dao.insert(vo);
	}

	@Override	//idx ���������----------------------------
	public List<ReviewVo> selectList(int c_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectList(c_idx);
	}

	@Override	//���� ������ ������ �����------------------------
	public List<ReviewVo> selectList_m(int m_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectList_m(m_idx);
	}

	@Override	//���� �Ѱ� �� ����������------------------------
	public ReviewVo selectOne(int r_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectOne(r_idx);
	}

	@Override	//���� ����----------------------------------
	public int delete(int r_idx) {
		// TODO Auto-generated method stub
		return review_dao.delete(r_idx);
	}

	@Override 	//���� ����----------------------------------
	public int update(ReviewVo vo) {
		// TODO Auto-generated method stub
		return review_dao.update(vo);
	}

	@Override	//���� ������ ����� ���ϱ�------------------------
	public int review_count(int m_idx) {
		// TODO Auto-generated method stub
		return review_dao.review_count(m_idx);
	}

	@Override	//��ü ��������ϱ�-----------------------------
	public ReviewVo selectOne_full(int c_idx) {
		// TODO Auto-generated method stub
		return review_dao.selectOne_full(c_idx);
	}

	@Override	//���� �ۼ��� ����� ���ϱ�-------------------------
	public int review_count() {
		// TODO Auto-generated method stub
		return review_dao.review_count();
	}
}
